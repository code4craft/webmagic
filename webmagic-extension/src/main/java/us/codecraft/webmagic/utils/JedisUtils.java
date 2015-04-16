package us.codecraft.webmagic.utils;


import redis.clients.jedis.Jedis;

public class JedisUtils {
	private static final String OK_CODE = "OK";
	private static final String OK_MULTI_CODE = "+OK";

	/**
	 * 判断 返回值是否ok.
	 */
	public static boolean isStatusOk(String status) {
		return (status != null) && (OK_CODE.equals(status) || OK_MULTI_CODE.equals(status));
	}

	/**
	 * 在Pool以外强行销毁Jedis.
	 */
	public static void destroyJedis(Jedis jedis) {
		if ((jedis != null) && jedis.isConnected()) {
			try {
				try {
					jedis.quit();
				} catch (Exception e) {
				}
				jedis.disconnect();
			} catch (Exception e) {
			}
		}
	}

	
}
