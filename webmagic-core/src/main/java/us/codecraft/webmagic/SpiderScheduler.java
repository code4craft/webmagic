package us.codecraft.webmagic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import us.codecraft.webmagic.scheduler.Scheduler;
import us.codecraft.webmagic.thread.CountableThreadPool;

public class SpiderScheduler {
    private Scheduler scheduler;
    private final ReentrantLock newUrlLock = new ReentrantLock();
    private final Condition newUrlCondition = newUrlLock.newCondition();

    public SpiderScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public Request poll(Spider spider) {
        return scheduler.poll(spider);
    }

    public void push(Request request, Spider spider) {
        scheduler.push(request, spider);
    }

    public boolean waitNewUrl(CountableThreadPool threadPool, long emptySleepTime) {
        newUrlLock.lock();
        try {
            if (threadPool.getThreadAlive() == 0) {
                return false;
            }
            newUrlCondition.await(emptySleepTime, TimeUnit.MILLISECONDS);
            return false;
        } catch (InterruptedException e) {
            return true;
        } finally {
            newUrlLock.unlock();
        }
    }

    public void signalNewUrl() {
        try {
            newUrlLock.lock();
            newUrlCondition.signalAll();
        } finally {
            newUrlLock.unlock();
        }
    }

}
