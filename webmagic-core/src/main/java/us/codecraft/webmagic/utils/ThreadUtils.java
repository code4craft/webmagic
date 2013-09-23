package us.codecraft.webmagic.utils;

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
		if (threadSize <= 1) {
			throw new IllegalArgumentException("ThreadSize must be greater than 1!");
		}
		return new ThreadPoolExecutor(threadSize - 1, threadSize - 1, 0L, TimeUnit.MILLISECONDS,
				new SynchronousQueue<Runnable>(), new ThreadPoolExecutor.CallerRunsPolicy());
	}
}
