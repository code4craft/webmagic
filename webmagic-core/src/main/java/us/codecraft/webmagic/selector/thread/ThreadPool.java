package us.codecraft.webmagic.selector.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author code4crafer@gmail.com
 * @since 0.5.0
 */
public class ThreadPool {

    private int threadNum;

    private int threadAlive;

    private ReentrantLock reentrantLock = new ReentrantLock();

    private Condition condition = reentrantLock.newCondition();

    public ThreadPool(int threadNum) {
        this.threadNum = threadNum;
        this.executorService = Executors.newFixedThreadPool(threadNum);
    }

    public ThreadPool(int threadNum, ExecutorService executorService) {
        this.threadNum = threadNum;
        this.executorService = executorService;
    }

    public void setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public int getThreadAlive() {
        return threadAlive;
    }

    public int getThreadNum() {
        return threadNum;
    }

    private ExecutorService executorService;

    public void execute(Runnable runnable) {
        try {
            reentrantLock.lock();
            while (threadAlive >= threadNum) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                }
            }
            threadAlive++;
            executorService.execute(runnable);
        } finally {
            condition.notify();
            threadAlive--;
            reentrantLock.unlock();
        }
    }

    public boolean isShutdown() {
        return executorService.isShutdown();
    }

    public void shutdown() {
        executorService.shutdown();
    }


}
