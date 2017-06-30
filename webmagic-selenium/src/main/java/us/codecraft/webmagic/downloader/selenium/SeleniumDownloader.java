package us.codecraft.webmagic.downloader.selenium;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.ProxyProvider;
import us.codecraft.webmagic.selector.PlainText;

import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 使用Selenium调用浏览器进行渲染。目前仅支持chrome。<br>
 * 需要下载Selenium driver支持。<br>
 *
 * @author code4crafter@gmail.com <br>
 *         Date: 13-7-26 <br>
 *         Time: 下午1:37 <br>
 */
public class SeleniumDownloader implements Downloader, Closeable {

    private final Map<String, WebDriver> webDriverMap = new HashMap<String, WebDriver>();
    private final WebDriverGenerator webDriverGenerator = new WebDriverGenerator();
    private boolean mAutoQuitDriver = false;
    private ProxyProvider proxyProvider;
    private Proxy proxy;

    private Logger logger = Logger.getLogger(getClass());
    private int sleepTime = 0;
    private int poolSize = 1;


    public SeleniumDownloader setProxyProvider(ProxyProvider proxyProvider) {
        this.proxyProvider = proxyProvider;
        return this;
    }

    public WebDriver getWebDriver(Proxy proxy) throws InterruptedException, IOException {
        String key = "webmagic";
        //因为不知道selenium能不能动态切换代理,所以每个代理对应一个webDriver
        if (proxy != null) {
            key = proxy.getHost() + "&" + proxy.getPort() + "&" + proxy.getUsername() + "&" + proxy.getPassword();
        }
        WebDriver driver = webDriverMap.get(key);
        if (driver == null) {
            synchronized (webDriverMap) {
                // add new WebDriver instance into pool
                try {
                    driver = webDriverGenerator.generateWebDriver(proxy);
                    webDriverMap.put(key, driver);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return driver;
    }

    /**
     * set sleep time to wait until load success
     *
     * @param sleepTime sleepTime
     * @return this
     */
    public SeleniumDownloader setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
        return this;
    }

    public void changeProxy(Task task) {
        if (proxy != null) {
            Proxy nextProxy = proxyProvider != null ? proxyProvider.getProxy(task) : null;
            if (!proxy.equals(nextProxy)) try {
                getWebDriver(proxy).quit();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Page download(Request request, Task task) {
        if (task == null) {
            throw new NullPointerException("task can not be null");
        }

        WebDriver webDriver = null;
        try {
            webDriver = getWebDriver(proxy);
        } catch (InterruptedException e) {
            logger.warn("interrupted", e);
            return null;
        } catch (IOException e) {
            logger.error("get WebDriver faild", e);
            return null;
        }
        logger.info("downloading page " + request.getUrl());

        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebDriver.Options manage = webDriver.manage();

        Site site = task.getSite();
        if (site.getCookies() != null) {
            for (Map.Entry<String, String> cookieEntry : site.getCookies()
                    .entrySet()) {
                Cookie cookie = new Cookie(cookieEntry.getKey(),
                        cookieEntry.getValue());
                manage.addCookie(cookie);
            }
        }
        Page page = Page.fail();
        webDriver.get(request.getUrl());

        /*
         * TODO You can add mouse event or other processes
		 *
		 * @author: bob.li.0718@gmail.com
	     */

        WebElement webElement = webDriver.findElement(By.xpath("/html"));
        String content = webElement.getAttribute("outerHTML");
        page.setRawText(content);
        page.setUrl(new PlainText(request.getUrl()));
        page.setRequest(request);
        page.setDownloadSuccess(true);
        if (mAutoQuitDriver) {
            webDriver.quit();
        }
        return page;
    }

    @Override
    public void setThread(int thread) {
        this.poolSize = thread;
    }

    @Override
    public void close() {
        for (WebDriver webDriver : webDriverMap.values()) {
            logger.info("Quit webDriver" + webDriver);
            webDriver.quit();
        }
    }
}
