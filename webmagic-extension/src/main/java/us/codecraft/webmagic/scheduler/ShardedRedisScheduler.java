package us.codecraft.webmagic.scheduler;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.apache.commons.codec.digest.DigestUtils;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.scheduler.component.DuplicateRemover;

import java.util.List;

/**
 * Use Redis cluster as url scheduler for distributed crawlers.<br>
 * Created by zhoubing on 2016/12/16.
 */
public class ShardedRedisScheduler extends DuplicateRemovedScheduler implements MonitorableScheduler, DuplicateRemover {

    protected ShardedJedisPool pool;

    private static final String QUEUE_PREFIX = "queue_";

    private static final String SET_PREFIX = "set_";

    private static final String ITEM_PREFIX = "item_";

    public ShardedRedisScheduler(JedisShardInfo shards) {
        this(Lists.asList(shards, new JedisShardInfo[0]));
    }

    public ShardedRedisScheduler(List<JedisShardInfo> shards) {
        this(new ShardedJedisPool(new JedisPoolConfig(), shards));
    }

    public ShardedRedisScheduler(ShardedJedisPool pool) {
        this.pool = pool;
        setDuplicateRemover(this);
    }

    @Override
    public void resetDuplicateCheck(Task task) {
        ShardedJedis jedis = pool.getResource();
        try {
            jedis.del(getSetKey(task));
        } finally {
            jedis.close();
        }
    }

    public Long allowDuplicateRequest(String url, Task task) {
        ShardedJedis jedis = pool.getResource();
        try {
            return jedis.srem(getSetKey(task), url);
        } finally {
            jedis.close();
        }
    }

    public Long allowDuplicateRequest(Request request, Task task) {
        return allowDuplicateRequest(request.getUrl(), task);
    }

    public void pushWithoutDuplicateCheck(Request request, Task task) {
        logger.debug("push to queue {}", request.getUrl());
        pushWhenNoDuplicate(request, task);
    }

    @Override
    public boolean isDuplicate(Request request, Task task) {
        ShardedJedis jedis = pool.getResource();
        try {
            boolean isDuplicate = jedis.sismember(getSetKey(task), request.getUrl());
            if (!isDuplicate) {
                jedis.sadd(getSetKey(task), request.getUrl());
            }
            return isDuplicate;
        } finally {
            jedis.close();
        }

    }

    @Override
    protected void pushWhenNoDuplicate(Request request, Task task) {
        ShardedJedis jedis = pool.getResource();
        try {
            //将url加入queueKey列表的结尾，如果该元素是第一个元素，那么会自动创建queueKey列表
            jedis.rpush(getQueueKey(task), request.getUrl());
            if (request.getExtras() != null) {
                String field = DigestUtils.shaHex(request.getUrl());
                String value = JSON.toJSONString(request);
                jedis.hset((ITEM_PREFIX + task.getUUID()), field, value);
            }
        } finally {
            jedis.close();
        }
    }

    @Override
    public synchronized Request poll(Task task) {
        ShardedJedis jedis = pool.getResource();
        try {
            String url = jedis.lpop(getQueueKey(task));
            if (url == null) {
                return null;
            }
            String key = ITEM_PREFIX + task.getUUID();
            String field = DigestUtils.shaHex(url);
            byte[] bytes = jedis.hget(key.getBytes(), field.getBytes());
            if (bytes != null) {
                return JSON.parseObject(new String(bytes), Request.class);
            }
            return new Request(url);
        } finally {
            jedis.close();
        }
    }

    protected String getSetKey(Task task) {
        return SET_PREFIX + task.getUUID();
    }

    protected String getQueueKey(Task task) {
        return QUEUE_PREFIX + task.getUUID();
    }

    protected String getItemKey(Task task)
    {
        return ITEM_PREFIX + task.getUUID();
    }

    @Override
    public int getLeftRequestsCount(Task task) {
        ShardedJedis jedis = pool.getResource();
        try {
            Long size = jedis.llen(getQueueKey(task));
            return size.intValue();
        } finally {
            jedis.close();
        }
    }

    @Override
    public int getTotalRequestsCount(Task task) {
        ShardedJedis jedis = pool.getResource();
        try {
            Long size = jedis.scard(getSetKey(task));
            return size.intValue();
        } finally {
            jedis.close();
        }
    }

}
