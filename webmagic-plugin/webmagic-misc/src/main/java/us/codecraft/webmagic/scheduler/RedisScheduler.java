package us.codecraft.webmagic.scheduler;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.schedular.Scheduler;

/**
 * 使用redis管理url，构建一个分布式的爬虫。<br>
 *
 * @author code4crafter@gmail.com <br>
 * @date: 13-7-25 <br>
 * Time: 上午7:07 <br>
 */
public class RedisScheduler implements Scheduler {

    private JedisPool pool;

    private static final String QUEUE_PREFIX = "queue_";

    private static final String SET_PREFIX = "set_";

    public RedisScheduler(String host) {
        pool = new JedisPool(new JedisPoolConfig(), host);
    }

    @Override
    public synchronized void push(Request request, Task task) {
        Jedis jedis = pool.getResource();
        //使用SortedSet进行url去重
        if (jedis.zrank(SET_PREFIX + task.getUUID(), request.getUrl()) == null) {
            //使用List保存队列
            jedis.rpush(QUEUE_PREFIX + task.getUUID(), request.getUrl());
            jedis.zadd(SET_PREFIX + task.getUUID(), request.getPriority(), request.getUrl());
        }
        pool.returnResource(jedis);
    }

    @Override
    public synchronized Request poll(Task task) {
        Jedis jedis = pool.getResource();
        String url = jedis.lpop(QUEUE_PREFIX + task.getUUID());
        pool.returnResource(jedis);
        if (url==null){
            return null;
        }
        return new Request(url);
    }
}
