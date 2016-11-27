package us.codecraft.webmagic.proxy;

import org.apache.http.HttpHost;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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

	@Test
	public void testProxy() {
		SimpleProxyPool proxyPool = new SimpleProxyPool(httpProxyList,false);
		proxyPool.setReuseInterval(500);
		assertThat(proxyPool.getIdleNum()).isEqualTo(4);
		for (int i = 0; i < 2; i++) {
			List<Fetch> fetchList = new ArrayList<Fetch>();
			while (proxyPool.getIdleNum() != 0) {
				Proxy proxy = proxyPool.getProxy();
				HttpHost httphost = proxy.getHttpHost();
				// httphostList.add(httphost);
				System.out.println(httphost.getHostName() + ":" + httphost.getPort());
				Fetch tmp = new Fetch(httphost);
				tmp.start();
				fetchList.add(tmp);
			}
			for (Fetch fetch : fetchList) {
				proxyPool.returnProxy(fetch.hp, Proxy.SUCCESS);
			}
			System.out.println(proxyPool.allProxyStatus());

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

}
