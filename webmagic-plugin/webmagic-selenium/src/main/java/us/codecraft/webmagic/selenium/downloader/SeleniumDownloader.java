package us.codecraft.webmagic.selenium.downloader;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.Destroyable;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.PlainText;
import us.codecraft.webmagic.utils.UrlUtils;

import java.util.Map;

/**
 * 使用Selenium调用浏览器进行渲染。目前仅支持chrome。<br>
 * 需要下载Selenium driver支持。<br>
 *
 * @author yihua.huang@dianping.com <br>
 * @date: 13-7-26 <br>
 * Time: 下午1:37 <br>
 */
public class SeleniumDownloader implements Downloader, Destroyable {

    private WebDriverPool webDriverPool;

    private Logger logger = Logger.getLogger(getClass());

    private int sleepTime = 0;

    /**
     * 新建
     *
     * @param chromeDriverPath
     */
    public SeleniumDownloader(String chromeDriverPath) {
        System.getProperties().setProperty("webdriver.chrome.driver", chromeDriverPath);
        webDriverPool = new WebDriverPool();
    }

    public SeleniumDownloader(String chromeDriverPath, int poolSize) {
        System.getProperties().setProperty("webdriver.chrome.driver", chromeDriverPath);
        webDriverPool = new WebDriverPool(poolSize);
    }

    /**
     * set sleep time to wait until load success
     * @param sleepTime
     * @return this
     */
    public SeleniumDownloader setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
        return this;
    }

    @Override
    public Page download(Request request, Task task) {
        WebDriver webDriver;
        try {
            webDriver = webDriverPool.get();
        } catch (InterruptedException e) {
            logger.warn("interrupted", e);
            return null;
        }
        logger.info("downloading page " + request.getUrl());
        webDriver.get(request.getUrl());
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebDriver.Options manage = webDriver.manage();
        Site site = task.getSite();
        if (site.getCookies() != null) {
            for (Map.Entry<String, String> cookieEntry : site.getCookies().entrySet()) {
                Cookie cookie = new Cookie(cookieEntry.getKey(), cookieEntry.getValue());
                manage.addCookie(cookie);
            }
        }
        WebElement webElement = webDriver.findElement(By.xpath("/html"));
        String content = webElement.getAttribute("outerHTML");
        //
        Page page = new Page();
        page.setHtml(new Html(UrlUtils.fixAllRelativeHrefs(content, request.getUrl())));
        page.setUrl(new PlainText(request.getUrl()));
        page.setRequest(request);
        webDriverPool.returnToPool(webDriver);
        return page;
    }

    @Override
    public void destroy() {
        webDriverPool.closeAll();
    }
}
