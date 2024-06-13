package us.codecraft.webmagic.downloader.selenium;

import java.io.Closeable;
import java.io.IOException;
import java.util.Map;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.PlainText;

/**
 * 类似于SeleniumDownloader,但是只用ChromeDriver<br>
 * ChromeDriver下载地址：http://chromedriver.storage.googleapis.com/index.html
 * chrome浏览器版本与chromeDriver驱动包版本是要注意
 *
 * @author Stephen Cai
 * @date: 2017-12-03 18:31
 */
public class ChromeDriverDownloader implements Downloader, Closeable {

    private GenericObjectPool<ChromeDriver> pool;

    private ChromeOptions options;

    private Logger logger = Logger.getLogger(getClass());

    private int sleepTime = 0;

    private int poolSize = 1;

    private static final String DRIVER_PHANTOMJS = "phantomjs";

    /**
     * 新建
     *
     * @param chromeDriverPath chromeDriverPath
     */
    public ChromeDriverDownloader(String chromeDriverPath) {
        System.getProperties().setProperty("webdriver.chrome.driver",
            chromeDriverPath);
        options = new ChromeOptions();
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxTotal(5);
        poolConfig.setMaxIdle(5);
        poolConfig.setMinIdle(1);
        pool = new GenericObjectPool<ChromeDriver>(
            new BasePooledObjectFactory<ChromeDriver>() {
                @Override
                public ChromeDriver create() throws Exception {
                    return new ChromeDriver(options);
                }

                @Override
                public PooledObject<ChromeDriver> wrap(final ChromeDriver chromeDriver) {
                    return new DefaultPooledObject<ChromeDriver>(chromeDriver) {
                        @Override
                        public synchronized void invalidate() {
                            chromeDriver.quit();
                            super.invalidate();
                        }
                    };
                }
            }, poolConfig);
    }


    /**
     * set sleep time to wait until load success
     *
     * @param sleepTime sleepTime
     * @return this
     */
    public ChromeDriverDownloader setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
        return this;
    }

    @Override
    public Page download(Request request, Task task) {
        ChromeDriver chromeDriver;
        try {
            chromeDriver = pool.borrowObject();
        } catch (Exception e) {
            logger.error("get from pool error", e);
            return null;
        }
        logger.info("downloading page " + request.getUrl());
        chromeDriver.get(request.getUrl());
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebDriver.Options manage = chromeDriver.manage();
        Site site = task.getSite();
        if (site.getCookies() != null) {
            for (Map.Entry<String, String> cookieEntry : site.getCookies()
                .entrySet()) {
                Cookie cookie = new Cookie(cookieEntry.getKey(),
                    cookieEntry.getValue());
                manage.addCookie(cookie);
            }
        }

		/*
         * TODO You can add mouse event or other processes
		 * 
		 * @author: bob.li.0718@gmail.com
		 */

        WebElement webElement = chromeDriver.findElement(By.xpath("/html"));
        String content = webElement.getAttribute("outerHTML");
        Page page = new Page();
        page.setRawText(content);
        page.setHtml(new Html(content, request.getUrl()));
        page.setUrl(new PlainText(request.getUrl()));
        page.setRequest(request);
        pool.returnObject(chromeDriver);
        return page;
    }


    @Override
    public void setThread(int thread) {
        this.poolSize = thread;
    }

    @Override
    public void close() throws IOException {
        pool.close();
    }
}
