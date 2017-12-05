package us.codecraft.platform.download;

import org.openqa.selenium.Proxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author :王龙
 * @Description
 * @Data 2017/11/23 14:00
 * @Modified By：
 */
public abstract class Pool<T> {
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 默认池大小
     */
    protected int poolSize = 5;
    /**
     * 池容器,阻塞容器
     */
    protected BlockingQueue<T> workerQueue;
    /**
     * WebDriverPool对外提供的池是否回收driver的标识
     */
    protected AtomicBoolean isWorkersReturning = new AtomicBoolean(false);
    /**
     * 记录当前运行中的driver的数量
     */
    protected AtomicInteger workingCount = new AtomicInteger(0);

    /**
     * 锁
     */
    protected ReentrantLock reentrantLock = new ReentrantLock();
    /**
     * 从池中获取webdriver的条件
     */
    protected Condition workCondtion = reentrantLock.newCondition();


    public abstract void setPoolSize(int poolSize);

    public void setIsWorkersReturning(boolean flag) {
        isWorkersReturning.set(flag);
    }

    /**
     * 从池中获取一个webworker
     *
     * @return webworker
     * @throws InterruptedException
     * @author 刘太信
     * @date 2017年8月18日 下午9:07:24
     */
    public <T> T get() {
        if (workingCount.get() >= poolSize || isWorkersReturning.get()) {
            refreeAwait();
        }
        logger.info("开始从池中获取worker对象,目前池中包含worker数量：【{}】", workerQueue.size());
        T worker = (T) workerQueue.poll();
        if (null == worker) {
            worker = (T) createNewWorker();
            logger.info("worker:{}",worker.toString());
        }
        workingCount.incrementAndGet();
        logger.info("成功获取worker对象，池中剩worker的数量有 {} 个,当前运行中的worker的数量有 {} 个", workerQueue.size(), workingCount.get());
        return worker;
    }

    public abstract  T createNewWorker();

    /**
     * 等在所有worker回收
     *
     * @author 刘太信
     * @date 2017年8月18日 下午9:12:19
     */
    public boolean waitAllworkerReturn() {
        if (isWorkersReturning.compareAndSet(false, true)) {
            while (workingCount.get() > 0) {
                try {
                    logger.info("正在使用worker数量{}，等待worker回收", workingCount.get());
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    logger.error("回收workers时异常", e);
                }
            }
            return true;
        }
        return false;
    }

    /**
     * 释放webworker返回到池中
     *
     * @param webworker
     * @author 刘太信
     * @date 2017年8月18日 下午9:09:15
     */
    public void returnToPool(T webworker) {
        if (workerQueue.offer(webworker)) {
            if (workingCount.get() == poolSize) {
                refreeReady();
            }
            workingCount.decrementAndGet();
            logger.info("returnToPool:返回worker到对象成功，worker池中数量为【{}】,正在使用的数量为【{}】",workerQueue.size(),workingCount.get());
        } else {
            logger.info("returnToPool:返回worker到对象失败");
        }
    }


    /**
     * 关闭webworker池，释放所有资源
     *
     * @author 刘太信
     * @date 2017年8月18日 下午9:09:40
     */
    public void shutdown() {
        if (workerQueue.isEmpty()) {
            logger.error("worker Already closed!");
        } else {
            try {
                for (T worker : workerQueue) {
                    workingCount.decrementAndGet();
                    close(worker);
                }
                workerQueue.clear();
            } catch (Exception e) {
                logger.error("webworkerpool关闭失败", e);
            }
        }
    }

    /**
     * 关闭释放资源，不推荐手动调用，建议使用returnToPool
     *
     * @param webworker
     * @author 刘太信
     * @date 2017年8月18日 下午9:09:29
     */
    public abstract void close(T webworker);

    /**
     * 清空并释放workerPool中的worker
     *
     * @return ：
     * @author : 王龙
     * @Description ：
     * @params ：
     * @Date ：2017/11/23 10:50
     */
    public void clear() {
        if (waitAllworkerReturn()) {
            shutdown();
            refreeReady();
        } else {
            refreeAwait();
        }
    }

    /**
     * 当innerQueue重启过程时
     * 或者
     * 当workingCount>=poolSize时,
     * 阻塞获取worker线程
     */
    protected void refreeAwait() {
        try {
            logger.info("workerPool阻塞");
            reentrantLock.lock();
            workCondtion.await();
        } catch (InterruptedException e) {
            logger.error("workerPool阻塞异常", e);
        } finally {
            reentrantLock.unlock();
        }
    }

    /**
     * 释放获取worker的等待锁
     */
    protected void refreeReady() {
        try {
            reentrantLock.lock();
            workCondtion.signalAll();
            logger.info("workerPool释放阻塞锁");
        } finally {
            reentrantLock.unlock();
        }
    }

    public abstract void setProxy(Proxy proxy);
}
