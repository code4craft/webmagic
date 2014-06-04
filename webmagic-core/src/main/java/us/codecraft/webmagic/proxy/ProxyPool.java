package us.codecraft.webmagic.proxy;

import org.apache.http.HttpHost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;

/**
 * ClassName:ProxyPool
 * 
 * @see
 * @Function: TODO ADD FUNCTION
 * @author ch
 * @version Ver 1.0
 * @Date 2014-2-14 下午01:10:04
 */
public class ProxyPool {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private BlockingQueue<Proxy> proxyQueue = new DelayQueue<Proxy>();
	private Map<String, Proxy> allProxy = new ConcurrentHashMap<String, Proxy>();

	private int reuseInterval = 1500;// ms
	private int reviveTime = 2 * 60 * 60 * 1000;// ms

	private boolean isEnable = false;
	private boolean validateWhenInit = false;
	private String proxyFile = "data/lastUse.proxy";

	private Timer timer = new Timer(true);
	private TimerTask saveProxyTask = new TimerTask() {

		@Override
		public void run() {
			saveProxyList();
			logger.info(allProxyStatus());
		}
	};

	public ProxyPool() {

	}

	public ProxyPool(List<String[]> httpProxyList) {
		readProxyList();
		addProxy(httpProxyList.toArray(new String[httpProxyList.size()][]));
		timer.schedule(saveProxyTask, 10 * 60 * 1000L, 10 * 60 * 1000);
	}

	private void saveProxyList() {
		if (allProxy.size() == 0) {
			return;
		}
		try {
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(proxyFile));
			os.writeObject(prepareForSaving());
			os.close();
			logger.info("save proxy");
		} catch (FileNotFoundException e) {
			logger.error("proxy file not found", e);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Map<String, Proxy> prepareForSaving() {
		Map<String, Proxy> tmp = new HashMap<String, Proxy>();
		for (Entry<String, Proxy> e : allProxy.entrySet()) {
			Proxy p = e.getValue();
			p.setFailedNum(0);
			tmp.put(e.getKey(), p);
		}
		return tmp;
	}

	private void readProxyList() {
		try {
			ObjectInputStream is = new ObjectInputStream(new FileInputStream(proxyFile));
			addProxy((Map<String, Proxy>) is.readObject());
			is.close();
		} catch (FileNotFoundException e) {
			logger.error("proxy file not found", e);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void addProxy(Map<String, Proxy> httpProxyMap) {
		isEnable = true;
		for (Entry<String, Proxy> entry : httpProxyMap.entrySet()) {
			try {
				if (allProxy.containsKey(entry.getKey())) {
					continue;
				}
				if (!validateWhenInit || ProxyUtil.validateProxy(entry.getValue().getHttpHost())) {
					entry.getValue().setFailedNum(0);
					entry.getValue().setReuseTimeInterval(reuseInterval);
					proxyQueue.add(entry.getValue());
					allProxy.put(entry.getKey(), entry.getValue());
				}
			} catch (NumberFormatException e) {
				logger.error("HttpHost init error:", e);
			}
		}
		logger.info("proxy pool size>>>>" + allProxy.size());
	}

	public void addProxy(String[]... httpProxyList) {
		isEnable = true;
		for (String[] s : httpProxyList) {
			try {
				if (allProxy.containsKey(s[0])) {
					continue;
				}
				HttpHost item = new HttpHost(InetAddress.getByName(s[0]), Integer.valueOf(s[1]));
				if (!validateWhenInit || ProxyUtil.validateProxy(item)) {
					Proxy p = new Proxy(item, reuseInterval);
					proxyQueue.add(p);
					allProxy.put(s[0], p);
				}
			} catch (NumberFormatException e) {
				logger.error("HttpHost init error:", e);
			} catch (UnknownHostException e) {
				logger.error("HttpHost init error:", e);
			}
		}
		logger.info("proxy pool size>>>>" + allProxy.size());
	}

	public HttpHost getProxy() {
		Proxy proxy = null;
		try {
			Long time = System.currentTimeMillis();
			proxy = proxyQueue.take();
			double costTime = (System.currentTimeMillis() - time) / 1000.0;
			if (costTime > reuseInterval) {
				logger.info("get proxy time >>>> " + costTime);
			}
			Proxy p = allProxy.get(proxy.getHttpHost().getAddress().getHostAddress());
			p.setLastBorrowTime(System.currentTimeMillis());
			p.borrowNumIncrement(1);
		} catch (InterruptedException e) {
			logger.error("get proxy error", e);
		}
		if (proxy == null) {
			throw new NoSuchElementException();
		}
		return proxy.getHttpHost();
	}

	public void returnProxy(HttpHost host, int statusCode) {
		Proxy p = allProxy.get(host.getAddress().getHostAddress());
		if (p == null) {
			return;
		}
		switch (statusCode) {
		case Proxy.SUCCESS:
			p.setReuseTimeInterval(reuseInterval);
			p.setFailedNum(0);
			p.setFailedErrorType(new ArrayList<Integer>());
			p.recordResponse();
			p.successNumIncrement(1);
			break;
		case Proxy.ERROR_403:
			// banned,try larger interval
			p.fail(Proxy.ERROR_403);
			p.setReuseTimeInterval(reuseInterval * p.getFailedNum());
			logger.info(host + " >>>> reuseTimeInterval is >>>> " + p.getReuseTimeInterval() / 1000.0);
			break;
		case Proxy.ERROR_BANNED:
			p.fail(Proxy.ERROR_BANNED);
			p.setReuseTimeInterval(10 * 60 * 1000 * p.getFailedNum());
			logger.warn("this proxy is banned >>>> " + p.getHttpHost());
			logger.info(host + " >>>> reuseTimeInterval is >>>> " + p.getReuseTimeInterval() / 1000.0);
			break;
		case Proxy.ERROR_404:
			//p.fail(Proxy.ERROR_404);
			// p.setReuseTimeInterval(reuseInterval * p.getFailedNum());
			break;
		default:
			p.fail(statusCode);
			break;
		}
		if (p.getFailedNum() > 20) {
			// allProxy.remove(host.getAddress().getHostAddress());
			p.setReuseTimeInterval(reviveTime);
			logger.error("remove proxy >>>> " + host + ">>>>" + p.getFailedType() + " >>>> remain proxy >>>> " + proxyQueue.size());
			return;
		}
		if (p.getFailedNum()%5==0) {
			if (!ProxyUtil.validateProxy(host)) {
				// allProxy.remove(host.getAddress().getHostAddress());
				p.setReuseTimeInterval(reviveTime);
				logger.error("remove proxy >>>> " + host + ">>>>" + p.getFailedType() + " >>>> remain proxy >>>> " + proxyQueue.size());
				return;
			}
		}
		try {
			proxyQueue.put(p);
		} catch (InterruptedException e) {
			logger.warn("proxyQueue return proxy error", e);
		}
	}

	public String allProxyStatus() {
		String re = "all proxy info >>>> \n";
		for (Entry<String, Proxy> entry : allProxy.entrySet()) {
			re += entry.getValue().toString() + "\n";
		}
		return re;

	}

	public int getIdleNum() {
		return proxyQueue.size();
	}

	public int getReuseInterval() {
		return reuseInterval;
	}

	public void setReuseInterval(int reuseInterval) {
		this.reuseInterval = reuseInterval;
	}

	public static List<String[]> getProxyList() {
		List<String[]> proxyList = new ArrayList<String[]>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(new File("proxy.txt")));

			String line = "";
			while ((line = br.readLine()) != null) {
				proxyList.add(new String[] { line.split(":")[0], line.split(":")[1] });
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return proxyList;
	}

	public static void main(String[] args) throws IOException {
		ProxyPool proxyPool = new ProxyPool(getProxyList());
		proxyPool.setReuseInterval(10000);
		// proxyPool.saveProxyList();

		while (true) {
			List<HttpHost> httphostList = new ArrayList<HttpHost>();
			System.in.read();
			int i = 0;
			while (proxyPool.getIdleNum() > 2) {
				HttpHost httphost = proxyPool.getProxy();
				httphostList.add(httphost);
				// proxyPool.proxyPool.use(httphost);
				proxyPool.logger.info("borrow object>>>>" + i + ">>>>" + httphostList.get(i).toString());
				i++;
			}
			System.out.println(proxyPool.allProxyStatus());
			System.in.read();
			for (i = 0; i < httphostList.size(); i++) {
				proxyPool.returnProxy(httphostList.get(i), 200);
				proxyPool.logger.info("return object>>>>" + i + ">>>>" + httphostList.get(i).toString());
			}
			System.out.println(proxyPool.allProxyStatus());
			System.in.read();
		}

	}

	public void enable(boolean isEnable) {
		this.isEnable = isEnable;
	}

	public boolean isEnable() {
		return isEnable;
	}
}
