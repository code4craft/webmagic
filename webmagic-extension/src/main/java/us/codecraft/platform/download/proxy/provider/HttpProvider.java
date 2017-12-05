package us.codecraft.platform.download.proxy.provider;

import us.codecraft.webmagic.proxy.Proxy;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 通信服务接口
 *
 * @author 王龙
 * @date 2017年8月15日 下午5:16:58
 */
public  interface HttpProvider {
    int tryTime=3;

    /**
     * 切换代理
     * @author : 王龙
     * @return http代理会返回ip端口帐号密码(当返回null请download执行清除代理设置)，adsl代理永远返回null
     * @author 刘太信
     * @date 2017年8月15日 下午6:09:17
     */
    Proxy change();

   /**
    * 获取切换状态
    * @author : 王龙
    * @Description ：获取
    * @params ：
    * @return ：
    * @Date ：2017/11/15 10:44
    */
    AtomicBoolean getChangeStatus();

    /**
     * 获取重入锁
     * @author : 王龙
     * @Description ：
     * @params ：
     * @return ：
     * @Date ：2017/11/15 10:47
     */
    ReentrantLock getReentrantLock();

    /**
     * 获取切换条件
     * @author : 王龙
     * @Description ：
     * @params ：
     * @return ：
     * @Date ：2017/11/15 10:47
     */
    Condition getChangeCondition();

    /**
     * 阻塞切换通信服务线程
     * @author : 王龙
     * @Description ：
     * @params ：
     * @return ：
     * @Date ：2017/11/15 10:53
     */
    default void await() {
        try {
            getReentrantLock().lock();
            if (getChangeStatus().get()) {
                try {
                    getChangeCondition().await();
                } catch (InterruptedException e) {
                }
            }
        }finally {
            getReentrantLock().unlock();
        }
    }

    /**
     * 唤醒使切换通信服务线程
     * @author : 王龙
     * @Description ：
     * @params ：
     * @return ：
     * @Date ：2017/11/15 10:53
     */
    default  void signalAll() {
        try {
            getReentrantLock().lock();
            getChangeStatus().set(false);
            getChangeCondition().signalAll();
        }finally {
            getReentrantLock().unlock();
        }
    }

}
