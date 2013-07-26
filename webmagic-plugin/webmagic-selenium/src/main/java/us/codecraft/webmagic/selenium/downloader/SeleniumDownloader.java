package us.codecraft.webmagic.selenium.downloader;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.PlainText;
import us.codecraft.webmagic.utils.UrlUtils;

/**
 * @author yihua.huang@dianping.com <br>
 * @date: 13-7-26 <br>
 * Time: 下午1:37 <br>
 */
public class SeleniumDownloader implements Downloader {

    private WebDriverPool webDriverPool;

    private Logger logger = Logger.getLogger(getClass());

    public SeleniumDownloader(String chromeDriverPath) {
        System.getProperties().setProperty("webdriver.chrome.driver", chromeDriverPath);
    }

    @Override
    public Page download(Request request, Task task) {
        WebDriver webDriver = null;
        try {
            webDriver = webDriverPool.get();
        } catch (InterruptedException e) {
            logger.warn("interrupted",e);
            return null;
        }
        webDriver.get(request.getUrl());
        WebElement webElement = webDriver.findElement(By.xpath("/html"));
        String content = webElement.getAttribute("outerHTML");
        Page page = new Page();
        page.setHtml(new Html(UrlUtils.fixAllRelativeHrefs(content, request.getUrl())));
        page.setUrl(new PlainText(request.getUrl()));
        page.setRequest(request);
        return page;
    }
}
