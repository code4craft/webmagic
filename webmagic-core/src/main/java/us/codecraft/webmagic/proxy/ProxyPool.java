package us.codecraft.webmagic.proxy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Map.Entry;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;

import org.apache.http.HttpHost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import us.codecraft.webmagic.utils.FilePersistentBase;
import us.codecraft.webmagic.utils.ProxyUtils;

/**
 * Pooled Proxy Object
 * 
 * @author yxssfxwzy@sina.com <br>
 * @since 0.5.1
 * @see Proxy
 */
public class ProxyPool {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private BlockingQueue<Proxy> proxyQueue = new DelayQueue<Proxy>();
	private Map<String, Proxy> allProxy = new ConcurrentHashMap<String, Proxy>();

	private int reuseInterval = 1500;// ms
	private int reviveTime = 2 * 60 * 60 * 1000;// ms
	private int saveProxyInterval = 10 * 60 * 1000;// ms

	private boolean isEnable = false;
	private boolean validateWhenInit = false;
	// private boolean isUseLastProxy = true;
	private String proxyFilePath = "/data/webmagic/lastUse.proxy";

	private FilePersistentBase fBase = new FilePersistentBase();

	private Timer timer = new Timer(true);
	private TimerTask saveProxyTask = new TimerTask() {

		@Override
		public void run() {
			saveProxyList();
			logger.info(allProxyStatus());
		}
	};

	public ProxyPool() {
		this(null, true);
	}

	public ProxyPool(List<String[]> httpProxyList) {
		this(httpProxyList, true);
	}

	public ProxyPool(List<String[]> httpProxyList, boolean isUseLastProxy) {
		if (httpProxyList != null) {
			addProxy(httpProxyList.toArray(new String[httpProxyList.size()][]));
		}
		if (isUseLastProxy) {
			if (!new File(proxyFilePath).exists()) {
				setFilePath();
			}
			setFilePath();
			readProxyList();
			timer.schedule(saveProxyTask, 0, saveProxyInterval);
		}
	}

	private void setFilePath() {
		String tmpDir = System.getProperty("java.io.tmpdir");
		String path = tmpDir + "webmagic\\lastUse.proxy";
		if (tmpDir != null && new File(tmpDir).isDirectory()) {
			fBase.setPath(tmpDir + "webmagic");
			File f = fBase.getFile(path);
			if (!f.exists()) {
				try {
					f.createNewFile();

				} catch (IOException e) {
					logger.error("proxy file create error", e);
				}
			}

		} else {
			logger.error("java tmp dir not exists");
		}
		this.proxyFilePath = path;
	}

	private void saveProxyList() {
		if (allProxy.size() == 0) {
			return;
		}
		try {
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fBase.getFile(proxyFilePath)));
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
			ObjectInputStream is = new ObjectInputStream(new FileInputStream(fBase.getFile(proxyFilePath)));
			addProxy((Map<String, Proxy>) is.readObject());
			is.close();
		} catch (FileNotFoundException e) {
			logger.info("last use proxy file not found", e);
		} catch (IOException e) {
			// e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// e.printStackTrace();
		}
	}

	private void addProxy(Map<String, Proxy> httpProxyMap) {
		isEnable = true;
		for (Entry<String, Proxy> entry : httpProxyMap.entrySet()) {
			try {
				if (allProxy.containsKey(entry.getKey())) {
					continue;
				}
				if (!validateWhenInit || ProxyUtils.validateProxy(entry.getValue().getHttpHost())) {
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
				if (!validateWhenInit || ProxyUtils.validateProxy(item)) {
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
			// banned,try longer interval
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
			// p.fail(Proxy.ERROR_404);
			// p.setReuseTimeInterval(reuseInterval * p.getFailedNum());
			break;
		default:
			p.fail(statusCode);
			break;
		}
		if (p.getFailedNum() > 20) {
			p.setReuseTimeInterval(reviveTime);
			logger.error("remove proxy >>>> " + host + ">>>>" + p.getFailedType() + " >>>> remain proxy >>>> " + proxyQueue.size());
			return;
		}
		if (p.getFailedNum() > 0 && p.getFailedNum() % 5 == 0) {
			if (!ProxyUtils.validateProxy(host)) {
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

	public void enable(boolean isEnable) {
		this.isEnable = isEnable;
	}

	public boolean isEnable() {
		return isEnable;
	}

	public int getReviveTime() {
		return reviveTime;
	}

	public void setReviveTime(int reviveTime) {
		this.reviveTime = reviveTime;
	}

	public boolean isValidateWhenInit() {
		return validateWhenInit;
	}

	public void validateWhenInit(boolean validateWhenInit) {
		this.validateWhenInit = validateWhenInit;
	}

	public int getSaveProxyInterval() {
		return saveProxyInterval;
	}

	public void setSaveProxyInterval(int saveProxyInterval) {
		this.saveProxyInterval = saveProxyInterval;
	}

	public String getProxyFilePath() {
		return proxyFilePath;
	}

	public void setProxyFilePath(String proxyFilePath) {
		this.proxyFilePath = proxyFilePath;
	}

}
