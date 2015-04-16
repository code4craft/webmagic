package us.codecraft.webmagic.scheduler;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.util.Pool;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;

/**
 * Use Redis as url scheduler for distributed crawlers.<br>
 * 针对sentinel redis做了优化
 * 若在执行redis命令过程中 主redis宕机 会休眠一段时间 等待切换到一个新主机上  
 * 然后再重新执行一遍该redis命令
 * @author code4crafter@gmail.com <br>
 * @since 0.2.0
 */
public class RedisOfSentinelScheduler extends RedisScheduler {
	private long downAfterMilliseconds;

	public RedisOfSentinelScheduler(Pool<Jedis> pool, long downAfterMilliseconds) {
		super(pool);
		this.downAfterMilliseconds = downAfterMilliseconds;
	}

	@Override
	public void resetDuplicateCheck(Task task) {
		try {
			super.resetDuplicateCheck(task);
		} catch (JedisConnectionException e) {
				logger.error("",e);
				waitForChangeMaster();
				resetDuplicateCheck(task); //重新再来一遍
		}
	}
	/**
	 * 等待切换master
	 */
	private void waitForChangeMaster() {
		logger.info("waiting for change master...");
		try {
			Thread.sleep(downAfterMilliseconds + 1000);
		} catch (InterruptedException e) {
			logger.error("",e);
		}
	}

	@Override
	public boolean isDuplicate(Request request, Task task) {
		try{
			return super.isDuplicate(request, task);
		}catch(JedisConnectionException e){
			logger.error("",e);
			waitForChangeMaster();
			return isDuplicate(request,task); //重新再来一遍
		}
	}

	@Override
	protected void pushWhenNoDuplicate(Request request, Task task) {
		try{
			super.pushWhenNoDuplicate(request, task);
		}catch(JedisConnectionException e){
			logger.error("",e);
			waitForChangeMaster();
			pushWhenNoDuplicate(request,task); //重新再来一遍
		}
	}

	@Override
	public synchronized Request poll(Task task) {
		try{
			return super.poll(task);
		}catch(JedisConnectionException e){
			logger.error("",e);
			waitForChangeMaster();
			return poll(task); //重新再来一遍
		}
	}

	@Override
	public int getLeftRequestsCount(Task task) {
		try{
			return super.getLeftRequestsCount(task);
		}catch(JedisConnectionException e){
			logger.error("",e);
			waitForChangeMaster();
			return getLeftRequestsCount(task); //重新再来一遍
		}
	}

	@Override
	public int getTotalRequestsCount(Task task) {
		try{
			return super.getTotalRequestsCount(task);
		}catch(JedisConnectionException e){
			logger.error("",e);
			waitForChangeMaster();
			return getTotalRequestsCount(task); //重新再来一遍
		}
	}

}
