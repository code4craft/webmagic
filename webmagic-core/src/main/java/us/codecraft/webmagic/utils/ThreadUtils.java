package us.codecraft.webmagic.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author code4crafer@gmail.com
 * @since 0.1.0
 */
public class ThreadUtils {

    public static ExecutorService newFixedThreadPool(int threadSize) {
        return new ThreadPoolExecutor(threadSize, threadSize, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1) {

                    private static final long serialVersionUID = -9028058603126367678L;

                    @Override
                    public boolean offer(Runnable e) {
                        try {
                            put(e);
                            return true;
                        } catch (InterruptedException ie) {
                            Thread.currentThread().interrupt();
                        }
                        return false;
                    }
                });
    }
}
