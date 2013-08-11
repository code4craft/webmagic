package us.codecraft.webmagic.scheduler;

import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.digest.DigestUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;

/**
 * 使用redis管理url，构建一个分布式的爬虫。<br>
 *
 * @author code4crafter@gmail.com <br>
 *         Date: 13-7-25 <br>
 *         Time: 上午7:07 <br>
 */
public class RedisScheduler implements Scheduler {

    private JedisPool pool;

    private static final String QUEUE_PREFIX = "queue_";

    private static final String SET_PREFIX = "set_";

    private static final String ITEM_PREFIX = "item_";

    public RedisScheduler(String host) {
        pool = new JedisPool(new JedisPoolConfig(), host);
    }

    @Override
    public synchronized void push(Request request, Task task) {
        Jedis jedis = pool.getResource();
        try {
            //使用Set进行url去重
            if (!jedis.sismember(SET_PREFIX + task.getUUID(), request.getUrl())) {
                //使用List保存队列
                jedis.rpush(QUEUE_PREFIX + task.getUUID(), request.getUrl());
                jedis.sadd(SET_PREFIX + task.getUUID(), request.getUrl());
                if (request.getExtras() != null) {
                    String field = DigestUtils.shaHex(request.getUrl());
                    String value = JSON.toJSONString(request);
                    jedis.hset((ITEM_PREFIX + task.getUUID()), field, value);
                }
            }
        } finally {
            pool.returnResource(jedis);
        }
    }

    @Override
    public synchronized Request poll(Task task) {
        Jedis jedis = pool.getResource();
        try {
            String url = jedis.lpop(QUEUE_PREFIX + task.getUUID());
            if (url == null) {
                return null;
            }
            String key = ITEM_PREFIX + task.getUUID();
            String field = DigestUtils.shaHex(url);
            byte[] bytes = jedis.hget(key.getBytes(),field.getBytes());
            if (bytes != null) {
                Request o = JSON.parseObject(new String(bytes), Request.class);
                return o;
            }
            Request request = new Request(url);
            return request;
        } finally {
            pool.returnResource(jedis);
        }
    }
}
