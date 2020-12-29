package us.codecraft.webmagic.proxy;

import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.Task;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author yaoqiang
 * 可刷新的代理提供商抽象实现
 */
@Slf4j
public abstract class AbstractRefreshableProxyProvider implements RefreshableProxyProvider {

    private final LongAdder totalGet = new LongAdder();

    private final LongAdder canUse = new LongAdder();

    private final AtomicReference<FutureTask<Proxy>> usedProxyCache = new AtomicReference<>();

    private final PriorityBlockingQueue<ExpirableProxy> ipQueue = new PriorityBlockingQueue<>(1000, Comparator.comparing(ExpirableProxy::getExpireTime));

    private final int maxHostNum;

    public AbstractRefreshableProxyProvider(int maxHostNum) {
        this.maxHostNum = maxHostNum;
    }

    protected void doPut(ExpirableProxy expirableProxy) {
        synchronized (ipQueue) {
            if (ipQueue.size() <= maxHostNum) {
                ipQueue.put(expirableProxy);
            }
        }
    }

    @Override
    public void refreshProxy(Task task, Proxy proxy) {
        if (proxy != null) {
            FutureTask<Proxy> proxyFutureTask = usedProxyCache.get();
            Proxy currentProxy = getCurrentProxy(task);
            // 如果在出错到这里的过程中，usedProxyCache被更新过，proxy 就不可能相等，如果依然相等，说明没有更新过
            // 可能没有使用代理的情况
            if (proxy.equals(currentProxy)) {
                // 如果此时依然没有更新过，就设置为空
                usedProxyCache.compareAndSet(proxyFutureTask, null);
            }
        }
    }

    @Override
    public Proxy getCurrentProxy(Task task) {
        FutureTask<Proxy> cache = usedProxyCache.get();
        Proxy currentProxy = null;
        try {
            if (cache != null)
                currentProxy = cache.get(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            e.printStackTrace();
            log.error(e.getCause().getMessage(), e);
        } catch (TimeoutException e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return currentProxy;
    }


    private FutureTask<Proxy> buildCacheTask() {
        return new FutureTask<>(this::doGet);
    }


    /**
     * 特别注意，防止活锁，集cache中总是抛出异常，那么将无限循环，无限报错
     *
     * @param task 下载任务
     * @return 返回代理
     */
    @Override
    public Proxy getProxy(Task task) {
        while (!Thread.currentThread().isInterrupted()) {
            FutureTask<Proxy> cache = usedProxyCache.get();
            if (cache == null) {
                FutureTask<Proxy> futureTask = buildCacheTask();
                if (usedProxyCache.compareAndSet(null, futureTask)) {
                    cache = futureTask;
                    futureTask.run();
                } else {
                    // 交换失败，需要更新到最新数据
                    cache = usedProxyCache.get();
                }
            }
            try {
                if (cache != null) {

                    ExpirableProxy proxy = (ExpirableProxy) cache.get(5, TimeUnit.SECONDS);
                    if (!proxy.isExpire())
                        return proxy;
                }
                usedProxyCache.compareAndSet(cache, null);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error(e.getMessage(), e);
                usedProxyCache.compareAndSet(cache, null);
            } catch (ExecutionException e) {
                log.error(e.getMessage(), e);
                usedProxyCache.compareAndSet(cache, null);
            } catch (TimeoutException e) {
                log.error(e.getMessage(), e);
            }
        }
        return null;
    }

    private Proxy doGet() throws InterruptedException {
        ExpirableProxy proxy;
        do {
            proxy = ipQueue.take();
        } while (proxy.isExpire());
        log.info("切换到proxy：ip:{}，port:{}，ip可用率:{}", proxy.getHost(), proxy.getPort(), BigDecimal.valueOf(canUse.sum()).divide(BigDecimal.valueOf(totalGet.sum()), 2, RoundingMode.HALF_DOWN).doubleValue());
        return proxy;
    }


}
