package us.codecraft.platform.download.selenium.webdriver.phantomjs;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.platform.Callback;
import us.codecraft.platform.download.DownloaderRule;
import us.codecraft.platform.download.SubDownloader;
import us.codecraft.platform.download.proxy.provider.HttpProvider;
import us.codecraft.platform.download.proxy.provider.ProxyHttpProvider;
import us.codecraft.platform.download.selenium.SeleniumDownloader;
import us.codecraft.platform.download.selenium.webdriver.tool.DesiredCapabilitiesUtilsl;
import us.codecraft.platform.download.selenium.webdriver.tool.WebDriverPool;
import us.codecraft.platform.download.trigger.PageTrigger;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.proxy.Proxy;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * PhantomJsSeleniumSubDownloader，基于SeleniumDownloader，SubDownloader
 * 实现自己的httpProvider和PageTrigger
 *
 * @author 王龙
 * @date 2017年8月18日 下午9:39:15
 */
public class PhantomJsSeleniumSubDownloader implements SubDownloader {

    private static Logger logger = LoggerFactory.getLogger(PhantomJsSeleniumSubDownloader.class);

    private DownloaderRule rule;

    private HttpProvider httpProvider;

    private PageTrigger pageTrigger;

    private SeleniumDownloader<PhantomJSDriver> downloader;

    public PhantomJsSeleniumSubDownloader(DownloaderRule rule) {
        this.rule = rule;
        this.httpProvider = rule.getHttpProvider();
        this.pageTrigger = rule.getPageTrigger();
        initSuper();
    }


    @Override
    public void initSuper() {
        downloader = new PhantomJsSeleniumDownloader();
        logger.info("初始化{}类", downloader.getClass().getName());
        //初始化HttpClientDownloader的属性
        if (httpProvider instanceof ProxyHttpProvider) {
            setProxy(httpProvider.change());
        }
    }

    /**
     * 当使用ProxyHttpProvider时，切换代理的IP
     *
     * @param proxy
     * @return ：
     * @author : 王龙
     * @Description ：
     * @params ：
     * @Date ：2017/11/15 15:20
     */
    @Override
    public void setProxy(Proxy proxy) {
        downloader.setProxy(proxy);
    }

    @Override
    public Pattern getPattern() {
        return rule.getPattern();
    }

    @Override
    public HttpProvider getHttpProvider() {
        return httpProvider;
    }

    @Override
    public PageTrigger getPageTrigger() {
        return pageTrigger;
    }

    @Override
    public Downloader getDownloader() {
        return downloader;
    }

    class PhantomJsSeleniumDownloader extends SeleniumDownloader<PhantomJSDriver> {
        /**
         * webdriver池
         */
        @Override
        public void setWorkersPool() {
            pool = new WebDriverPool<>(DesiredCapabilitiesUtilsl.getPhantomjsCaps(), PhantomJSDriver.class);
        }

        /**
         * 通过driver获取请求的Response并以Map的形式返回
         * 参考 https://www.cnblogs.com/lexfu/p/5288299.html
         *
         * @param driver
         * @param request
         * @param task
         * @return
         */
        @Override
        public Map<String, Object> getResponseMap(PhantomJSDriver driver, Request request, Task task) {
            String phantomScript =
                    "var url =  '" + request.getUrl() + "';" +
                            "var page = this; " +
                            "page.onResourceReceived = function(response) {" +
                            "if (response.stage !== \"end\" || response.url != url) " +
                            "return;" +
                            " page.statusCode = response;" +
                            "};";
            driver.executePhantomJS(phantomScript);
            driver.navigate().to(request.getUrl());
            Integer statusCode = Integer.valueOf("" + driver.executePhantomJS("var page = this; return page.statusCode.status;"));
            String content = String.valueOf("" + driver.executePhantomJS("var page = this; return page.content;"));
            Map<String, Object> result = new HashMap<>();
            result.put("statusCode", statusCode);
            result.put("content", content);
            return result;
        }
    }

    @Override
    public void setThread(int threadNum) {
        downloader.setThread(rule.getThreadNum());
    }

    @Override
    public void handleProxy(Request request, HttpProvider provider, Callback<Proxy> callback) {
        downloader.handleProxy(request,provider,callback);
    }
}

