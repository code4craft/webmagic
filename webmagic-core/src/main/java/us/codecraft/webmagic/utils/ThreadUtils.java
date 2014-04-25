package us.codecraft.webmagic.utils;

import com.google.common.util.concurrent.MoreExecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author code4crafer@gmail.com
 * @since 0.1.0
 */
public class ThreadUtils {

    public static ExecutorService newFixedThreadPool(int threadSize) {
        if (threadSize <= 0) {
            throw new IllegalArgumentException("ThreadSize must be greater than 0!");
        }
        if (threadSize == 1) {
            return MoreExecutors.sameThreadExecutor();
        }
        return new ThreadPoolExecutor(threadSize - 1, threadSize - 1, 0L, TimeUnit.MILLISECONDS,
                new SynchronousQueue<Runnable>(), new ThreadPoolExecutor.CallerRunsPolicy());
    }
}
