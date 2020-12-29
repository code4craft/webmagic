package us.codecraft.webmagic.pipeline;

import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;

import java.io.Closeable;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;

/**
 * @author yaoqiang
 * 提供关闭时刷新能力
 * <p>
 * <p>
 * 不负责创建 {@link ExecutorService},如果需要异步执行，那么需要从外界传入，由外界自己管理 {@link ExecutorService}生命周期
 * @see ExecutorService
 */
@Slf4j
public abstract class CloseableCachePipeline implements CachePipeline<ResultItems>, Closeable {


    private final BlockingQueue<ResultItems> cache;

    private final ExecutorService executorService;

    public CloseableCachePipeline(int max, ExecutorService executorService) {
        this.cache = new ArrayBlockingQueue<>(max, false);
        this.executorService = executorService;
    }

    public CloseableCachePipeline(int max) {
        this(max, null);
    }

    /**
     * @param resultItems 接收到的信息
     * @param task 执行的任务
     */
    @Override
    public final void process(ResultItems resultItems, Task task) {
        try {
            cache.put(resultItems);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
            log.error(e.getMessage(), e);
        }
        if (cache.remainingCapacity() == 0) {
            // set 中的resultItem 使用权依然传递了出去，cache的使用全保留，考虑到后面也用不上 resultItem，所以发布出去问题也不大
            // temp的修改权限被发布，理由同上，想添加，删除都可以，反正以后也用不上了;
            Set<ResultItems> temp = new HashSet<>(cache);
            if (executorService != null && !executorService.isShutdown()) {
                executorService.execute(() -> process(temp, task));
            } else {
                process(temp, task);
            }
            cache.clear();
        }

    }

    protected abstract void process(Collection<ResultItems> resultItems, Task task);

    private synchronized void flush(Collection<ResultItems> resultItems) {
        process(resultItems, null);

    }

    /**
     * 结合源码，实现关闭时处理剩余的缓存，直接交由主线程处理
     *
     * @throws IOException 关闭可能出现异常，由上层处理
     */
    @Override
    public final void close() throws IOException {
        if (!cache.isEmpty()) {
            flush(cache);
            cache.clear();
        }
    }
}
