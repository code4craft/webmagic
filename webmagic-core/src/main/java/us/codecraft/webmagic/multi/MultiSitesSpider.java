package us.codecraft.webmagic.multi;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.lf5.util.StreamUtils;

import crawlercommons.robots.BaseRobotRules;
import crawlercommons.robots.SimpleRobotRules;
import crawlercommons.robots.SimpleRobotRulesParser;
import crawlercommons.sitemaps.AbstractSiteMap;
import crawlercommons.sitemaps.SiteMap;
import crawlercommons.sitemaps.SiteMapIndex;
import crawlercommons.sitemaps.SiteMapParser;
import crawlercommons.sitemaps.SiteMapURL;
import crawlercommons.sitemaps.UnknownFormatException;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * A Spider that can handle multi sites at once. Reading user agents
 * 
 * @author pascal
 *
 */
public class MultiSitesSpider extends Spider {

	private Map<String, Long> domainLastAccess = Collections.synchronizedMap(new HashMap<String, Long>());

	private Map<String, BaseRobotRules> domainRobots = new HashMap<String, BaseRobotRules>();
	private Map<String, Date> domainRobotsExpiration = new HashMap<String, Date>();
	private Map<String, BaseRobotRules> defaultDomainRobots = new HashMap<String, BaseRobotRules>();
	private Map<String, Site> domainSites = new HashMap<String, Site>();
	private Set<String> siteMapsLoaded = new HashSet<String>();

	private String robotName;

	public MultiSitesSpider(String robotName, PageProcessor pageProcessor) {
		super(pageProcessor);
		this.robotName = robotName;
	}

	public void addRobotRules(String domain, BaseRobotRules rules) {
		this.defaultDomainRobots.put(domain, rules);
	}

	public void addSite(String domain, Site site) {
		this.domainSites.put(domain, site);
	}

	public static boolean canFollow(Request request) {
		Boolean extra = (Boolean) request.getExtra("follow");
		return extra == null || extra;
	}

	@Override
	protected void addRequest(Request request) {
		try {
			URL url = new URL(request.getUrl());
			if (isAllowedFromRobots(url) != null) {
				super.addRequest(request);
			}
		} catch (MalformedURLException e) {
			logger.error("Unable to parse URL =" + request.getUrl());
		}
	}

	protected void processRequest(Request request) {
		URL url;
		try {
			url = new URL(request.getUrl());
			site = getSite(url.getHost());
			BaseRobotRules robots = isAllowedFromRobots(url);
			if (robots == null) {
				// Returning null means this url is not allowed
				return;
			}
			int sleepTime = site.getSleepTime();
			if (robots.getCrawlDelay() != BaseRobotRules.UNSET_CRAWL_DELAY) {
				sleepTime = (int) robots.getCrawlDelay();
			}

			sleepRequest(sleepTime, url);
			if (canFollow(request)) {
				if ("SITEMAP".equals(request.getExtra("TYPE"))) {
					loadSiteMap(request);
					return;
				}
				processSiteMap(url, robots);
			}

			MultiSitesTask spiderTask = new MultiSitesTask(this, site);
			processRequestTask(request, spiderTask);
		} catch (MalformedURLException e) {
			onError(request);
			return;
		}
	}

	@Override
	protected void sleep(int time) {
		// Disable sleep as its handled by robots.txt
		return;
	}

	private synchronized Site getSite(String host) {
		Site res = domainSites.get(host);
		if (res == null) {
			res = getDefaultSite(host);
			domainSites.put(host, res);
		}
		return res;
	}

	/**
	 * May be overriden by subclasses to define default properties in Site
	 * 
	 * @param domain
	 * @return
	 */
	protected Site getDefaultSite(String domain) {
		return new Site().setDomain(domain);
	}

	/**
	 * 
	 * @param url
	 * @return the robots.txt rules if allowed or null if not allowed
	 */
	private BaseRobotRules isAllowedFromRobots(URL url) {

		BaseRobotRules robots;
		robots = this.defaultDomainRobots.get(url.getHost());
		if (robots != null && !robots.isAllowed(url.toString())) {
			System.err.println("Disabled from our own robots = " + url.toString());
			return null;
		}
		robots = getRobotsTxt(url);
		if (!robots.isAllowed(url.toString())) {
			System.err.println("Disabled from robots = " + url.toString());
			return null;
		}
		return robots;
	}

	private synchronized void processSiteMap(URL url, BaseRobotRules robots) {
		if (!siteMapsLoaded.contains(url.getHost())) {
			siteMapsLoaded.add(url.getHost());
			processSitemap(robots, url);
		}
	}

	private void loadSiteMap(Request request) {
		try {
			loadSiteMap(new URL(request.getUrl()));
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return;
		}
	}

	private void loadSiteMap(URL url) {
		logger.info("Loading sitemap url=" + url);
		try {
			SiteMapParser parser = new SiteMapParser();
			AbstractSiteMap absSiteMap = parser.parseSiteMap(url);
			handleAbstractSiteMap(absSiteMap);
			System.out.println("    Loaded sitemap url=" + url);
		} catch (UnknownFormatException e) {
			logger.warn("Bad sitemap url " + url, e);
			return;
		} catch (IOException e) {
			logger.warn("Bad sitemap url " + url, e);
			return;
		}
	}

	private void handleAbstractSiteMap(AbstractSiteMap absSiteMap) throws UnknownFormatException, IOException, MalformedURLException {
		if (absSiteMap.isIndex()) {
			SiteMapIndex siteMapIndex = (SiteMapIndex) absSiteMap;
			for (AbstractSiteMap subSiteMap : siteMapIndex.getSitemaps()) {
				handleAbstractSiteMap(subSiteMap);
			}
		} else {
			handleSiteMap((SiteMap) absSiteMap);
		}
	}

	private void handleSiteMap(SiteMap siteMap) throws UnknownFormatException, IOException, MalformedURLException {
		if (!siteMap.isProcessed()) {
			addRequestSiteMapToProcess(siteMap.getUrl());
			return;
		}
		Collection<SiteMapURL> res = siteMap.getSiteMapUrls();
		for (SiteMapURL siteMapURL : res) {
			double prio = siteMapURL.getPriority();
			URL siteMapUrl = siteMapURL.getUrl();
			Request siteMapRequest = new Request(siteMapUrl.toString()).setPriority((long) (1000 * prio));
			addRequest(siteMapRequest);
		}
	}

	private void processSitemap(BaseRobotRules robots, URL url) {
		List<String> siteMaps = null;
		if (robots != null) {
			siteMaps = robots.getSitemaps();
		}
		if (siteMaps == null || siteMaps.isEmpty()) {
			try {
				addRequestSiteMapToProcess(new URL(url, "/sitemap.xml"));
			} catch (MalformedURLException e) {
				e.printStackTrace();
				System.err.println("Can't add bad sitemap /sitemap.xml" + "url=" + url);
			}

		} else {
			for (String siteMap : siteMaps) {
				try {
					addRequestSiteMapToProcess(new URL(url, siteMap));
				} catch (MalformedURLException e) {
					e.printStackTrace();
					System.err.println("Can't add bad sitemap " + siteMap + "url=" + url);
				}
			}
		}

	}

	private void addRequestSiteMapToProcess(URL url) throws MalformedURLException {
		Map<String, Object> extras = new HashMap<String, Object>();
		extras.put("TYPE", "SITEMAP");
		Request request = new Request(url.toString()).setPriority(10000);
		request.setExtras(extras);
		addRequest(request);
	}

	private void sleepRequest(int retrySleepTime, URL url) {
		Long lastAccess = domainLastAccess.get(url.getHost());
		if (lastAccess != null) {
			long diff = System.currentTimeMillis() - lastAccess;
			if (diff < retrySleepTime) {
				logger.warn("Sleeping " + (retrySleepTime - diff) + "ms to not bother " + url.getHost());
				super.sleep((int) (retrySleepTime - diff));
			}
		}
		domainLastAccess.put(url.getHost(), System.currentTimeMillis());
	}

	private synchronized BaseRobotRules getRobotsTxt(URL url) {
		String host = url.getHost();
		Date now = new Date();
		Date expiration = domainRobotsExpiration.get(host);
		BaseRobotRules robots = domainRobots.get(host);
		if (!domainRobots.containsKey(host) || expiration == null || now.after(expiration)) {
			// We store the result (even if null)
			robots = fetchRobotsTxt(url);
			if (robots == null) {
				// Just generate an empty robots txt
				robots = new SimpleRobotRules();
			}
			domainRobots.put(host, robots);
			// And set expiration date to now+24h
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			domainRobotsExpiration.put(host, calendar.getTime());
		}
		return robots;
	}

	private BaseRobotRules fetchRobotsTxt(URL url) {
		try {
			URL robotsUrl = new URL(url, "/robots.txt");
			URLConnection robotsCNX = robotsUrl.openConnection();
			String contentType = robotsCNX.getContentType();
			byte[] bytes = null;
			InputStream is = null;
			try {
				is = robotsCNX.getInputStream();
				bytes = StreamUtils.getBytes(robotsCNX.getInputStream());

			} finally {
				if (is != null) {
					is.close();
				}
			}
			SimpleRobotRulesParser parser = new SimpleRobotRulesParser();
			return parser.parseContent(url.toString(), bytes, contentType, robotName);
		} catch (MalformedURLException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
	}

}
