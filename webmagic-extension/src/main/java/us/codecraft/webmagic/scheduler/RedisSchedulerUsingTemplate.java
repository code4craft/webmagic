package us.codecraft.webmagic.scheduler;

import org.apache.commons.codec.digest.DigestUtils;

import redis.clients.jedis.Jedis;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.scheduler.component.DuplicateRemover;
import us.codecraft.webmagic.utils.JedisTemplate;
import us.codecraft.webmagic.utils.JedisTemplate.JedisAction;
import us.codecraft.webmagic.utils.JedisTemplate.JedisActionNoResult;

import com.alibaba.fastjson.JSON;

/**
 * Use Redis as url scheduler for distributed crawlers.<br>
 *
 * @author code4crafter@gmail.com <br>
 * @since 0.2.0
 */
public class RedisSchedulerUsingTemplate extends DuplicateRemovedScheduler implements MonitorableScheduler, DuplicateRemover {


    private static final String QUEUE_PREFIX = "queue_";

    private static final String SET_PREFIX = "set_";

    private static final String ITEM_PREFIX = "item_";
    
    private JedisTemplate jedisTemplate;

    public RedisSchedulerUsingTemplate(JedisTemplate jedisTemplate) {
    	this.jedisTemplate = jedisTemplate;
    	 setDuplicateRemover(this);
    }


    @Override
    public void resetDuplicateCheck(Task task) {
    	jedisTemplate.del(getSetKey(task));
    }

    @Override
    public boolean isDuplicate(final Request request, final Task task) {
        return jedisTemplate.execute(new JedisAction<Boolean>(){
			@Override
			public Boolean action(Jedis jedis) {
				 boolean isDuplicate = jedis.sismember(getSetKey(task), request.getUrl());
		            if (!isDuplicate) {
		                jedis.sadd(getSetKey(task), request.getUrl());
		            }
		            return isDuplicate;
			}
        });

    }

    @Override
    protected void pushWhenNoDuplicate(final Request request, final Task task) {
        jedisTemplate.execute(new JedisActionNoResult(){
			@Override
			public void action(Jedis jedis) {
				 jedis.rpush(getQueueKey(task), request.getUrl());
		            if (request.getExtras() != null) {
		                String field = DigestUtils.shaHex(request.getUrl());
		                String value = JSON.toJSONString(request);
		                jedis.hset((ITEM_PREFIX + task.getUUID()), field, value);
		            }
			}
        });
    }

    @Override
    public synchronized Request poll(final Task task) {
        return jedisTemplate.execute(new JedisAction<Request>(){
			@Override
			public Request action(Jedis jedis) {
				String url = jedis.lpop(getQueueKey(task));
	            if (url == null) {
	                return null;
	            }
	            String key = ITEM_PREFIX + task.getUUID();
	            String field = DigestUtils.shaHex(url);
	            byte[] bytes = jedis.hget(key.getBytes(), field.getBytes());
	            if (bytes != null) {
	                Request o = JSON.parseObject(new String(bytes), Request.class);
	                return o;
	            }
	            Request request = new Request(url);
	            return request;
			}
        	
        });
    }

    protected String getSetKey(Task task) {
        return SET_PREFIX + task.getUUID();
    }

    protected String getQueueKey(Task task) {
        return QUEUE_PREFIX + task.getUUID();
    }

    @Override
    public int getLeftRequestsCount(final Task task) {
        return jedisTemplate.execute(new JedisAction<Integer>(){
			@Override
			public Integer action(Jedis jedis) {
				 Long size = jedis.llen(getQueueKey(task));
		         return size.intValue();
			}
        });
    }

    @Override
    public int getTotalRequestsCount(final Task task) {
        
        return jedisTemplate.execute(new JedisAction<Integer>(){
			@Override
			public Integer action(Jedis jedis) {
				 Long size = jedis.scard(getQueueKey(task));
		         return size.intValue();
			}
        });
    }
}
