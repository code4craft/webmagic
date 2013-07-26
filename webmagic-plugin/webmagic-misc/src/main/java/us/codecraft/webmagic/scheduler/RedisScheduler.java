package us.codecraft.webmagic.scheduler;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.schedular.Scheduler;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用redis管理url，构建一个分布式的爬虫。<br>
 *
 * @author yihua.huang@dianping.com <br>
 * @date: 13-7-25 <br>
 * Time: 上午7:07 <br>
 */
public class RedisScheduler implements Scheduler {

    private JedisPool pool;

    private static final String QUEUE_PREFIX = "queue_";

    private static final String SET_PREFIX = "set_";

    private ReentrantLock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    public RedisScheduler(String host) {
        pool = new JedisPool(new JedisPoolConfig(), host);
    }

    @Override
    public synchronized void push(Request request, Task task) {
        Jedis jedis = pool.getResource();
        //使用SortedSet进行url去重
        if (jedis.zrank(SET_PREFIX + task.getUUID(), request.getUrl()) == null) {
            try {
                lock.lock();
                //使用List保存队列
                jedis.rpush(QUEUE_PREFIX + task.getUUID(), request.getUrl());
                jedis.zadd(SET_PREFIX + task.getUUID(), System.currentTimeMillis(), request.getUrl());
                condition.signal();
            } finally {
                lock.unlock();
            }
        }
        pool.returnResource(jedis);
    }

    @Override
    public synchronized Request poll(Task task) {
        Jedis jedis = pool.getResource();
        String url = jedis.lpop(QUEUE_PREFIX + task.getUUID());
        if (url == null) {
            try {
                lock.lock();
                while (url == null) {
                    try {
                        condition.await();
                        url = jedis.lpop(QUEUE_PREFIX + task.getUUID());
                    } catch (InterruptedException e) {
                    }
                }
            } finally {
                lock.unlock();
            }
        }
        pool.returnResource(jedis);
        return new Request(url);
    }
}
