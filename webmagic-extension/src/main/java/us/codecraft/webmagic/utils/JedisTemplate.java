package us.codecraft.webmagic.utils;

/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.exceptions.JedisDataException;
import redis.clients.jedis.exceptions.JedisException;
import redis.clients.util.Pool;

/**
 * JedisTemplate 提供了一个template方法，负责对Jedis连接的获取与归还。
 * JedisAction<T> 和 JedisActionNoResult两种回调接口，适用于有无返回值两种情况。
 * PipelineAction 与 PipelineActionResult两种接口，适合于pipeline中批量传输命令的情况。
 * 
 * 同时提供一些JedisOperation中定义的 最常用函数的封装, 如get/set/zadd等。
 */
public class JedisTemplate {

	private static Logger logger = LoggerFactory.getLogger(JedisTemplate.class);

	private Pool<Jedis> jedisPool;
	private long downAfterMilliseconds;
	private String hostAndPort;

	public JedisTemplate(Pool<Jedis> jedisPool) {
		this.jedisPool = jedisPool;
	}
	public JedisTemplate(Pool<Jedis> jedisPool,String hostAndPort ) {
		this.jedisPool = jedisPool;
		this.hostAndPort = hostAndPort;
	}
	public JedisTemplate(Pool<Jedis> jedisPool,long downAfterMilliseconds) {
		this.jedisPool = jedisPool;
		this.downAfterMilliseconds = downAfterMilliseconds;
	}

	/**
	 * Callback interface for template.
	 */
	public interface JedisAction<T> {
		T action(Jedis jedis);
	}

	/**
	 * Callback interface for template without result.
	 */
	public interface JedisActionNoResult {
		void action(Jedis jedis);
	}

	/**
	 * Callback interface for template.
	 */
	public interface PipelineAction {
		List<Object> action(Pipeline Pipeline);
	}

	/**
	 * Callback interface for template without result.
	 */
	public interface PipelineActionNoResult {
		void action(Pipeline Pipeline);
	}

	/**
	 * Execute with a call back action with result.
	 */
	public <T> T execute(JedisAction<T> jedisAction) throws JedisException {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = jedisPool.getResource();
			return jedisAction.action(jedis);
		} catch (JedisException e) {
			broken = handleJedisException(e);
			/**
			 * 若为JedisSentinelPool 等待一段时间 等重新切换主机 然后重新执行一遍 直到有可用的主机
			 * 若为JedisPool的话 会不停重试 直到恢复连接
			 */
			if(e instanceof JedisConnectionException){ 
				sleep(downAfterMilliseconds+1000);
				return execute(jedisAction);
			}else
				throw e;
		} finally {
			closeResource(jedis, broken);
		}
	}

	private void sleep(long sleepTime) {
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
//			e.printStackTrace();
			logger.error("", e);
		}
	}
	/**
	 * Execute with a call back action without result.
	 */
	public void execute(JedisActionNoResult jedisAction) throws JedisException {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = jedisPool.getResource();
			jedisAction.action(jedis);
		} catch (JedisException e) {
			broken = handleJedisException(e);
			if(e instanceof JedisConnectionException){ //等待一段时间 等重新切换主机 然后重新执行一遍
				sleep(downAfterMilliseconds+1000);
				execute(jedisAction);
			}else
				throw e;
		} finally {
			closeResource(jedis, broken);
		}
	}

	/**
	 * Execute with a call back action with result in pipeline.
	 */
	public List<Object> execute(PipelineAction pipelineAction) throws JedisException {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = jedisPool.getResource();
			Pipeline pipeline = jedis.pipelined();
			pipelineAction.action(pipeline);
			return pipeline.syncAndReturnAll();
		} catch (JedisException e) {
			broken = handleJedisException(e);
			throw e;
		} finally {
			closeResource(jedis, broken);
		}
	}

	/**
	 * Execute with a call back action without result in pipeline.
	 */
	public void execute(PipelineActionNoResult pipelineAction) throws JedisException {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = jedisPool.getResource();
			Pipeline pipeline = jedis.pipelined();
			pipelineAction.action(pipeline);
			pipeline.sync();
		} catch (JedisException e) {
			broken = handleJedisException(e);
			throw e;
		} finally {
			closeResource(jedis, broken);
		}
	}

	/**
	 * Return the internal JedisPool.
	 */
	public Pool getJedisSentinelPool() {
		return jedisPool;
	}

	/**
	 * Handle jedisException, write log and return whether the connection is broken.
	 */
	protected boolean handleJedisException(JedisException jedisException) {
		String address = jedisPool instanceof JedisSentinelPool ? ((JedisSentinelPool)jedisPool).getCurrentHostMaster().toString():this.hostAndPort;
		if (jedisException instanceof JedisConnectionException) {
			logger.error("Redis connection " + address + " lost.", jedisException);
		} else if (jedisException instanceof JedisDataException) {
			if ((jedisException.getMessage() != null) && (jedisException.getMessage().indexOf("READONLY") != -1)) {
				logger.error("Redis connection " + address + " are read-only slave.", jedisException);
			} else {
				// dataException, isBroken=false
				return false;
			}
		} else {
			logger.error("Jedis exception happen.", jedisException);
		}
		return true;
	}

	/**
	 * Return jedis connection to the pool, call different return methods depends on the conectionBroken status.
	 */
	protected void closeResource(Jedis jedis, boolean conectionBroken) {
		try {
			if (conectionBroken) {
				jedisPool.returnBrokenResource(jedis);
			} else {
				jedisPool.returnResource(jedis);
			}
		} catch (Exception e) {
			logger.error("return back jedis failed, will fore close the jedis.", e);
			JedisUtils.destroyJedis(jedis);
		}

	}

	// / Common Actions ///

	/**
	 * Remove the specified keys. If a given key does not exist no operation is
	 * performed for this key.
	 * 
	 * return false if one of the key is not exist.
	 */
	public Boolean del(final String... keys) {
		return execute(new JedisAction<Boolean>() {

			@Override
			public Boolean action(Jedis jedis) {
				return jedis.del(keys) == keys.length ? true : false;
			}
		});
	}

	public void flushDB() {
		execute(new JedisActionNoResult() {

			@Override
			public void action(Jedis jedis) {
				jedis.flushDB();
			}
		});
	}

	// / String Actions ///

	/**
	 * Get the value of the specified key. If the key does not exist null is
	 * returned. If the value stored at key is not a string an error is returned
	 * because GET can only handle string values.
	 */
	public String get(final String key) {
		return execute(new JedisAction<String>() {

			@Override
			public String action(Jedis jedis) {
				return jedis.get(key);
			}
		});
	}

	/**
	 * Get the value of the specified key as Long.If the key does not exist null is returned.
	 */
	public Long getAsLong(final String key) {
		String result = get(key);
		return result != null ? Long.valueOf(result) : null;
	}

	/**
	 * Get the value of the specified key as Integer.If the key does not exist null is returned.
	 */
	public Integer getAsInt(final String key) {
		String result = get(key);
		return result != null ? Integer.valueOf(result) : null;
	}

	/**
	 * Get the values of all the specified keys. If one or more keys dont exist
	 * or is not of type String, a 'nil' value is returned instead of the value
	 * of the specified key, but the operation never fails.
	 */
	public List<String> mget(final String... keys) {
		return execute(new JedisAction<List<String>>() {

			@Override
			public List<String> action(Jedis jedis) {
				return jedis.mget(keys);
			}
		});
	}

	/**
	 * Set the string value as value of the key.
	 * The string can't be longer than 1073741824 bytes (1 GB).
	 */
	public void set(final String key, final String value) {
		execute(new JedisActionNoResult() {

			@Override
			public void action(Jedis jedis) {
				jedis.set(key, value);
			}
		});
	}

	/**
	 * The command is exactly equivalent to the following group of commands: {@link #set(String, String) SET} +
	 * {@link #expire(String, int) EXPIRE}.
	 * The operation is atomic.
	 */
	public void setex(final String key, final String value, final int seconds) {
		execute(new JedisActionNoResult() {

			@Override
			public void action(Jedis jedis) {
				jedis.setex(key, seconds, value);
			}
		});
	}

	/**
	 * SETNX works exactly like {@link #setNX(String, String) SET} with the only
	 * difference that if the key already exists no operation is performed.
	 * SETNX actually means "SET if Not eXists".
	 * 
	 * return true if the key was set.
	 */
	public Boolean setnx(final String key, final String value) {
		return execute(new JedisAction<Boolean>() {

			@Override
			public Boolean action(Jedis jedis) {
				return jedis.setnx(key, value) == 1 ? true : false;
			}
		});
	}

	/**
	 * The command is exactly equivalent to the following group of commands: {@link #setex(String, String, int) SETEX} +
	 * {@link #sexnx(String, String) SETNX}.
	 * The operation is atomic.
	 */
	public Boolean setnxex(final String key, final String value, final int seconds) {
		return execute(new JedisAction<Boolean>() {

			@Override
			public Boolean action(Jedis jedis) {
				String result = jedis.set(key, value, "NX", "EX", seconds);
				return JedisUtils.isStatusOk(result);
			}
		});
	}

	/**
	 * GETSET is an atomic set this value and return the old value command. Set
	 * key to the string value and return the old value stored at key. The
	 * string can't be longer than 1073741824 bytes (1 GB).
	 */
	public String getSet(final String key, final String value) {
		return execute(new JedisAction<String>() {

			@Override
			public String action(Jedis jedis) {
				return jedis.getSet(key, value);
			}
		});
	}

	/**
	 * Increment the number stored at key by one. If the key does not exist or
	 * contains a value of a wrong type, set the key to the value of "0" before
	 * to perform the increment operation.
	 * <p>
	 * INCR commands are limited to 64 bit signed integers.
	 * <p>
	 * Note: this is actually a string operation, that is, in Redis there are not "integer" types. Simply the string
	 * stored at the key is parsed as a base 10 64 bit signed integer, incremented, and then converted back as a string.
	 * 
	 * @return Integer reply, this commands will reply with the new value of key
	 *         after the increment.
	 */
	public Long incr(final String key) {
		return execute(new JedisAction<Long>() {
			@Override
			public Long action(Jedis jedis) {
				return jedis.incr(key);
			}
		});
	}

	public Long incrBy(final String key, final long increment) {
		return execute(new JedisAction<Long>() {
			@Override
			public Long action(Jedis jedis) {
				return jedis.incrBy(key, increment);
			}
		});
	}

	public Double incrByFloat(final String key, final double increment) {
		return execute(new JedisAction<Double>() {
			@Override
			public Double action(Jedis jedis) {
				return jedis.incrByFloat(key, increment);
			}
		});
	}

	/**
	 * Decrement the number stored at key by one. If the key does not exist or
	 * contains a value of a wrong type, set the key to the value of "0" before
	 * to perform the decrement operation.
	 */
	public Long decr(final String key) {
		return execute(new JedisAction<Long>() {
			@Override
			public Long action(Jedis jedis) {
				return jedis.decr(key);
			}
		});
	}

	public Long decrBy(final String key, final long decrement) {
		return execute(new JedisAction<Long>() {
			@Override
			public Long action(Jedis jedis) {
				return jedis.decrBy(key, decrement);
			}
		});
	}

	// / Hash Actions ///
	/**
	 * If key holds a hash, retrieve the value associated to the specified
	 * field.
	 * <p>
	 * If the field is not found or the key does not exist, a special 'nil' value is returned.
	 */
	public String hget(final String key, final String fieldName) {
		return execute(new JedisAction<String>() {
			@Override
			public String action(Jedis jedis) {
				return jedis.hget(key, fieldName);
			}
		});
	}

	public List<String> hmget(final String key, final String... fieldsNames) {
		return execute(new JedisAction<List<String>>() {
			@Override
			public List<String> action(Jedis jedis) {
				return jedis.hmget(key, fieldsNames);
			}
		});
	}

	public Map<String, String> hgetAll(final String key) {
		return execute(new JedisAction<Map<String, String>>() {
			@Override
			public Map<String, String> action(Jedis jedis) {
				return jedis.hgetAll(key);
			}
		});
	}

	public void hset(final String key, final String fieldName, final String value) {
		execute(new JedisActionNoResult() {

			@Override
			public void action(Jedis jedis) {
				jedis.hset(key, fieldName, value);
			}
		});
	}

	public void hmset(final String key, final Map<String, String> map) {
		execute(new JedisActionNoResult() {

			@Override
			public void action(Jedis jedis) {
				jedis.hmset(key, map);
			}
		});

	}

	public Boolean hsetnx(final String key, final String fieldName, final String value) {
		return execute(new JedisAction<Boolean>() {

			@Override
			public Boolean action(Jedis jedis) {
				return jedis.hsetnx(key, fieldName, value) == 1 ? true : false;
			}
		});
	}

	public Long hincrBy(final String key, final String fieldName, final long increment) {
		return execute(new JedisAction<Long>() {
			@Override
			public Long action(Jedis jedis) {
				return jedis.hincrBy(key, fieldName, increment);
			}
		});
	}

	public Double hincrByFloat(final String key, final String fieldName, final double increment) {
		return execute(new JedisAction<Double>() {
			@Override
			public Double action(Jedis jedis) {
				return jedis.hincrByFloat(key, fieldName, increment);
			}
		});
	}

	public Long hdel(final String key, final String... fieldsNames) {
		return execute(new JedisAction<Long>() {
			@Override
			public Long action(Jedis jedis) {
				return jedis.hdel(key, fieldsNames);
			}
		});
	}

	public Boolean hexists(final String key, final String fieldName) {
		return execute(new JedisAction<Boolean>() {
			@Override
			public Boolean action(Jedis jedis) {
				return jedis.hexists(key, fieldName);
			}
		});
	}

	public Set<String> hkeys(final String key) {
		return execute(new JedisAction<Set<String>>() {
			@Override
			public Set<String> action(Jedis jedis) {
				return jedis.hkeys(key);
			}
		});
	}

	public Long hlen(final String key) {
		return execute(new JedisAction<Long>() {
			@Override
			public Long action(Jedis jedis) {
				return jedis.hlen(key);
			}
		});
	}

	// / List Actions ///

	public Long lpush(final String key, final String... values) {
		return execute(new JedisAction<Long>() {
			@Override
			public Long action(Jedis jedis) {
				return jedis.lpush(key, values);
			}
		});
	}

	public String rpop(final String key) {
		return execute(new JedisAction<String>() {

			@Override
			public String action(Jedis jedis) {
				return jedis.rpop(key);
			}
		});
	}

	public String brpop(final String key) {
		return execute(new JedisAction<String>() {

			@Override
			public String action(Jedis jedis) {
				List<String> nameValuePair = jedis.brpop(key);
				if (nameValuePair != null) {
					return nameValuePair.get(1);
				} else {
					return null;
				}
			}
		});
	}

	public String brpop(final int timeout, final String key) {
		return execute(new JedisAction<String>() {

			@Override
			public String action(Jedis jedis) {
				List<String> nameValuePair = jedis.brpop(timeout, key);
				if (nameValuePair != null) {
					return nameValuePair.get(1);
				} else {
					return null;
				}
			}
		});
	}

	/**
	 * Not support for sharding.
	 */
	public String rpoplpush(final String sourceKey, final String destinationKey) {
		return execute(new JedisAction<String>() {

			@Override
			public String action(Jedis jedis) {
				return jedis.rpoplpush(sourceKey, destinationKey);
			}
		});
	}

	/**
	 * Not support for sharding.
	 */
	public String brpoplpush(final String source, final String destination, final int timeout) {
		return execute(new JedisAction<String>() {

			@Override
			public String action(Jedis jedis) {
				return jedis.brpoplpush(source, destination, timeout);
			}
		});
	}

	public Long llen(final String key) {
		return execute(new JedisAction<Long>() {

			@Override
			public Long action(Jedis jedis) {
				return jedis.llen(key);
			}
		});
	}

	public String lindex(final String key, final long index) {
		return execute(new JedisAction<String>() {

			@Override
			public String action(Jedis jedis) {
				return jedis.lindex(key, index);
			}
		});
	}

	public List<String> lrange(final String key, final int start, final int end) {
		return execute(new JedisAction<List<String>>() {

			@Override
			public List<String> action(Jedis jedis) {
				return jedis.lrange(key, start, end);
			}
		});
	}

	public void ltrim(final String key, final int start, final int end) {
		execute(new JedisActionNoResult() {
			@Override
			public void action(Jedis jedis) {
				jedis.ltrim(key, start, end);
			}
		});
	}

	public void ltrimFromLeft(final String key, final int size) {
		execute(new JedisActionNoResult() {
			@Override
			public void action(Jedis jedis) {
				jedis.ltrim(key, 0, size - 1);
			}
		});
	}

	public Boolean lremFirst(final String key, final String value) {
		return execute(new JedisAction<Boolean>() {
			@Override
			public Boolean action(Jedis jedis) {
				Long count = jedis.lrem(key, 1, value);
				return (count == 1);
			}
		});
	}

	public Boolean lremAll(final String key, final String value) {
		return execute(new JedisAction<Boolean>() {
			@Override
			public Boolean action(Jedis jedis) {
				Long count = jedis.lrem(key, 0, value);
				return (count > 0);
			}
		});
	}

	// / Set Actions ///
	public Boolean sadd(final String key, final String member) {
		return execute(new JedisAction<Boolean>() {

			@Override
			public Boolean action(Jedis jedis) {
				return jedis.sadd(key, member) == 1 ? true : false;
			}
		});
	}

	public Set<String> smembers(final String key) {
		return execute(new JedisAction<Set<String>>() {

			@Override
			public Set<String> action(Jedis jedis) {
				return jedis.smembers(key);
			}
		});
	}

	// / Ordered Set Actions ///
	/**
	 * return true for add new element, false for only update the score.
	 */
	public Boolean zadd(final String key, final double score, final String member) {
		return execute(new JedisAction<Boolean>() {

			@Override
			public Boolean action(Jedis jedis) {
				return jedis.zadd(key, score, member) == 1 ? true : false;
			}
		});
	}

	public Double zscore(final String key, final String member) {
		return execute(new JedisAction<Double>() {

			@Override
			public Double action(Jedis jedis) {
				return jedis.zscore(key, member);
			}
		});
	}

	public Long zrank(final String key, final String member) {
		return execute(new JedisAction<Long>() {

			@Override
			public Long action(Jedis jedis) {
				return jedis.zrank(key, member);
			}
		});
	}

	public Long zrevrank(final String key, final String member) {
		return execute(new JedisAction<Long>() {

			@Override
			public Long action(Jedis jedis) {
				return jedis.zrevrank(key, member);
			}
		});
	}

	public Long zcount(final String key, final double min, final double max) {
		return execute(new JedisAction<Long>() {

			@Override
			public Long action(Jedis jedis) {
				return jedis.zcount(key, min, max);
			}
		});
	}

	public Set<String> zrange(final String key, final int start, final int end) {
		return execute(new JedisAction<Set<String>>() {

			@Override
			public Set<String> action(Jedis jedis) {
				return jedis.zrange(key, start, end);
			}
		});
	}

	public Set<Tuple> zrangeWithScores(final String key, final int start, final int end) {
		return execute(new JedisAction<Set<Tuple>>() {

			@Override
			public Set<Tuple> action(Jedis jedis) {
				return jedis.zrangeWithScores(key, start, end);
			}
		});
	}

	public Set<String> zrevrange(final String key, final int start, final int end) {
		return execute(new JedisAction<Set<String>>() {

			@Override
			public Set<String> action(Jedis jedis) {
				return jedis.zrevrange(key, start, end);
			}
		});
	}

	public Set<Tuple> zrevrangeWithScores(final String key, final int start, final int end) {
		return execute(new JedisAction<Set<Tuple>>() {

			@Override
			public Set<Tuple> action(Jedis jedis) {
				return jedis.zrevrangeWithScores(key, start, end);
			}
		});
	}

	public Set<String> zrangeByScore(final String key, final double min, final double max) {
		return execute(new JedisAction<Set<String>>() {

			@Override
			public Set<String> action(Jedis jedis) {
				return jedis.zrangeByScore(key, min, max);
			}
		});
	}

	public Set<Tuple> zrangeByScoreWithScores(final String key, final double min, final double max) {
		return execute(new JedisAction<Set<Tuple>>() {

			@Override
			public Set<Tuple> action(Jedis jedis) {
				return jedis.zrangeByScoreWithScores(key, min, max);
			}
		});
	}

	public Set<String> zrevrangeByScore(final String key, final double max, final double min) {
		return execute(new JedisAction<Set<String>>() {

			@Override
			public Set<String> action(Jedis jedis) {
				return jedis.zrevrangeByScore(key, max, min);
			}
		});
	}

	public Set<Tuple> zrevrangeByScoreWithScores(final String key, final double max, final double min) {
		return execute(new JedisAction<Set<Tuple>>() {

			@Override
			public Set<Tuple> action(Jedis jedis) {
				return jedis.zrevrangeByScoreWithScores(key, max, min);
			}
		});
	}

	public Boolean zrem(final String key, final String member) {
		return execute(new JedisAction<Boolean>() {

			@Override
			public Boolean action(Jedis jedis) {
				return jedis.zrem(key, member) == 1 ? true : false;
			}
		});
	}

	public Long zremByScore(final String key, final double start, final double end) {
		return execute(new JedisAction<Long>() {

			@Override
			public Long action(Jedis jedis) {
				return jedis.zremrangeByScore(key, start, end);
			}
		});
	}

	public Long zremByRank(final String key, final long start, final long end) {
		return execute(new JedisAction<Long>() {

			@Override
			public Long action(Jedis jedis) {
				return jedis.zremrangeByRank(key, start, end);
			}
		});
	}

	public Long zcard(final String key) {
		return execute(new JedisAction<Long>() {

			@Override
			public Long action(Jedis jedis) {
				return jedis.zcard(key);
			}
		});
	}
}
