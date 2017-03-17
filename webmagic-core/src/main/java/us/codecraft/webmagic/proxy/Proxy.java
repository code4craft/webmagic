package us.codecraft.webmagic.proxy;

import org.apache.http.HttpHost;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**    
 * >>>> Proxy lifecycle 
 
        +----------+     +-----+
        | last use |     | new |
        +-----+----+     +---+-+
              |  +------+   |
              +->| init |<--+
                 +--+---+
                    |
                    v
                +--------+
           +--->| borrow |
           |    +---+----+
           |        |+------------------+
           |        v
           |    +--------+
           |    | in use |  Respone Time
           |    +---+----+
           |        |+------------------+
           |        v
           |    +--------+
           |    | return |
           |    +---+----+
           |        |+-------------------+
           |        v
           |    +-------+   reuse interval
           |    | delay |   (delay time)
           |    +---+---+
           |        |+-------------------+
           |        v
           |    +------+
           |    | idle |    idle time
           |    +---+--+
           |        |+-------------------+
           +--------+
 */

/**
 * Object has these status of lifecycle above.<br>
 * 
 * @author yxssfxwzy@sina.com <br>
 * @since 0.5.1
 * @see SimpleProxyPool
 */

public class Proxy implements Delayed, Serializable {

	private static final long serialVersionUID = 228939737383625551L;
	public static final int ERROR_403 = 403;
	public static final int ERROR_404 = 404;
	public static final int ERROR_BANNED = 10000;// banned by website
	public static final int ERROR_Proxy = 10001;// the proxy itself failed
	public static final int SUCCESS = 200;

	private final HttpHost httpHost;
	private String user;
	private String password;
	

	private int reuseTimeInterval = 1500;// ms
	private Long canReuseTime = 0L;
	private Long lastBorrowTime = System.currentTimeMillis();
	private Long responseTime = 0L;

	private int failedNum = 0;
	private int successNum = 0;
	private int borrowNum = 0;

	private List<Integer> failedErrorType = new ArrayList<Integer>();

	public Proxy(HttpHost httpHost, String user, String password) {
		this.httpHost = httpHost;
		this.user = user;
		this.password = password;
		this.canReuseTime = System.nanoTime() + TimeUnit.NANOSECONDS.convert(reuseTimeInterval, TimeUnit.MILLISECONDS);
	}

	public Proxy(HttpHost httpHost,  int reuseInterval, String user, String password) {
		this.httpHost = httpHost;
		this.user = user;
		this.password = password;
		this.canReuseTime = System.nanoTime() + TimeUnit.NANOSECONDS.convert(reuseInterval, TimeUnit.MILLISECONDS);
	}

	public int getSuccessNum() {
		return successNum;
	}

	public void successNumIncrement(int increment) {
		this.successNum += increment;
	}

	public Long getLastUseTime() {
		return lastBorrowTime;
	}

	public void setLastBorrowTime(Long lastBorrowTime) {
		this.lastBorrowTime = lastBorrowTime;
	}

	public void recordResponse() {
		this.responseTime = (System.currentTimeMillis() - lastBorrowTime + responseTime) / 2;
		this.lastBorrowTime = System.currentTimeMillis();
	}

	public List<Integer> getFailedErrorType() {
		return failedErrorType;
	}

	public void setFailedErrorType(List<Integer> failedErrorType) {
		this.failedErrorType = failedErrorType;
	}

	public void fail(int failedErrorType) {
		this.failedNum++;
		this.failedErrorType.add(failedErrorType);
	}

	public void setFailedNum(int failedNum) {
		this.failedNum = failedNum;
	}

	public int getFailedNum() {
		return failedNum;
	}

	public String getFailedType() {
		String re = "";
		for (Integer i : this.failedErrorType) {
			re += i + " . ";
		}
		return re;
	}

	public HttpHost getHttpHost() {
		return httpHost;
	}

	public int getReuseTimeInterval() {
		return reuseTimeInterval;
	}

	public void setReuseTimeInterval(int reuseTimeInterval) {
		this.reuseTimeInterval = reuseTimeInterval;
		this.canReuseTime = System.nanoTime() + TimeUnit.NANOSECONDS.convert(reuseTimeInterval, TimeUnit.MILLISECONDS);

	}

	@Override
	public long getDelay(TimeUnit unit) {
		return unit.convert(canReuseTime - System.nanoTime(), TimeUnit.NANOSECONDS);
	}

	@Override
	public int compareTo(Delayed o) {
		Proxy that = (Proxy) o;
		return canReuseTime > that.canReuseTime ? 1 : (canReuseTime < that.canReuseTime ? -1 : 0);

	}

	@Override
	public String toString() {

		String re = String.format("host: %15s >> %5dms >> success: %-3.2f%% >> borrow: %d", httpHost.getAddress().getHostAddress(), responseTime,
				successNum * 100.0 / borrowNum, borrowNum);
		return re;

	}
	
	public String getUser()
	{
		return user;
		
	}
	public String getPassword()
	{
		return password;
		
	}

	public void borrowNumIncrement(int increment) {
		this.borrowNum += increment;
	}

	public int getBorrowNum() {
		return borrowNum;
	}
}
