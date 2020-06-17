package us.codecraft.webmagic.proxy;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpHost;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author yxssfxwzy@sina.com May 30, 2014
 * 
 */
public class ProxyTest {

	private static List<String[]> httpProxyList = new ArrayList<String[]>();

	@BeforeClass
	public static void before() {
		// String[] source = { "0.0.0.1:0", "0.0.0.2:0", "0.0.0.3:0",
		// "0.0.0.4:0" };
		String[] source = { "::0.0.0.1:0", "::0.0.0.2:0", "::0.0.0.3:0", "::0.0.0.4:0" };
		for (String line : source) {
			httpProxyList.add(new String[] {line.split(":")[0], line.split(":")[1], line.split(":")[2], line.split(":")[3] });
		}
	}

	class Fetch extends Thread {
		HttpHost hp;

		public Fetch(HttpHost hp) {
			this.hp = hp;
		}

		@Override
		public void run() {
			try {
				System.out.println("fetch web page use proxy: " + hp.getHostName() + ":" + hp.getPort());
				sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Test
	public void testToString() {
		assertEquals("//127.0.0.1:8080", new Proxy("127.0.0.1", 8080).toString());
		assertEquals("http://127.0.0.1:8080", new Proxy("127.0.0.1", 8080, "http").toString());
		assertEquals("//username:password@127.0.0.1:8080", new Proxy("127.0.0.1", 8080, "username", "password").toString());
		assertEquals("//username@127.0.0.1:8080", new Proxy("127.0.0.1", 8080, "username", null).toString());
		assertEquals("//:password@127.0.0.1:8080", new Proxy("127.0.0.1", 8080, null, "password").toString());
	}

}
