package us.codecraft.webmagic.proxy;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.commons.collections.CollectionUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Task;

/**
 * a ProxyProvider can remove invalid proxy and add newProxies dynamically <br><br>
 * New feature: <br><br>
 * 1. remove invalid proxy
 * 2. async add proxies when proxy is less than threshold
 *
 * @author evan
 */
public abstract class EffectiveProxyProvider implements ProxyProvider {

    public static final int DEFAULT_EXPAND_POOL_SIZE = 1;

    private final ConcurrentLinkedQueue<Proxy> validProxyQueue = new ConcurrentLinkedQueue<Proxy>();

    private final ExecutorService addProxyPool = Executors.newFixedThreadPool(1);;

    private final ReentrantLock addProxyLock = new ReentrantLock();

    private int expandPoolSize = DEFAULT_EXPAND_POOL_SIZE;

    private ProxyPageValidator proxyPageValidator;

    public EffectiveProxyProvider() {
    }

    public EffectiveProxyProvider(ProxyPageValidator proxyPageValidator) {
        validProxyQueue.addAll(addProxies());
        this.proxyPageValidator = proxyPageValidator;
    }

    public EffectiveProxyProvider(ProxyPageValidator proxyPageValidator, int expandPoolSize) {
        validProxyQueue.addAll(addProxies());
        this.proxyPageValidator = proxyPageValidator;
        this.expandPoolSize = expandPoolSize;
    }

    public EffectiveProxyProvider(ProxyPageValidator pageValidator, List<Proxy> proxies) {
        this.validProxyQueue.addAll(proxies);
        this.proxyPageValidator = pageValidator;
    }

    public EffectiveProxyProvider(ProxyPageValidator pageValidator, List<Proxy> proxies, int expandPoolSize) {
        this.validProxyQueue.addAll(proxies);
        this.proxyPageValidator = pageValidator;
        this.expandPoolSize = expandPoolSize;
    }

    @Override
    public Proxy getProxy(Task task) {

        //async addProxy and avoid invoke extra times
        if (validProxyQueue.size() <= expandPoolSize) {
            addProxyPool.submit(new Runnable() {
                @Override public void run() {
                    if (addProxyLock.tryLock()) {
                        try {
                            List<Proxy> newProxies = addProxies();
                            if (CollectionUtils.isNotEmpty(newProxies)) {
                                validProxyQueue.addAll(newProxies);
                            }
                        }finally {
                            addProxyLock.unlock();
                        }
                    }
                }
            });
        }

        Proxy proxy = validProxyQueue.poll();
        if (proxy == null) {
            return null;
        }
        //put tail realize loop
        validProxyQueue.offer(proxy);

        return proxy;
    }

    @Override public void returnProxy(Proxy proxy, Page page, Task task) {
        //remove it when proxy is invalid
        if (proxyPageValidator != null && !proxyPageValidator.proxyValid(proxy, page, task)) {
            validProxyQueue.remove(proxy);
        }
    }

    public abstract List<Proxy> addProxies();
}
