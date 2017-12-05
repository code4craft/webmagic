package us.codecraft.platform.download.http;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.platform.Callback;
import us.codecraft.platform.download.DownloaderRule;
import us.codecraft.platform.download.SubDownloader;
import us.codecraft.platform.download.proxy.provider.HttpProvider;
import us.codecraft.platform.download.proxy.provider.ProxyHttpProvider;
import us.codecraft.platform.download.trigger.PageTrigger;
import us.codecraft.platform.utils.RequestUtils;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

import java.util.regex.Pattern;

/**
 * HttpClientSubDownloader的多download共存类，基于httpClientdownload
 *
 * @author 王龙
 * @date 2017年8月10日 下午5:53:17
 */
public class HttpClientSubDownloader implements SubDownloader {
    private static Logger logger = LoggerFactory.getLogger(HttpClientSubDownloader.class);

    private HttpClientDownloader downloader = new HttpClientDownloader();

    private DownloaderRule rule;

    private HttpProvider httpProvider;

    private PageTrigger pageTrigger;

    public HttpClientSubDownloader(DownloaderRule rule) {
        this.rule = rule;
        this.httpProvider = rule.getHttpProvider();
        this.pageTrigger = rule.getPageTrigger();
        initSuper();
    }

    @Override
    public Downloader getDownloader() {
        return downloader;
    }

    @Override
    public void setProxy(Proxy proxy) {
        downloader.setProxyProvider(SimpleProxyProvider.from(proxy));
    }

    @Override
    public void initSuper() {
        //初始化HttpClientDownloader的属性
        if (httpProvider instanceof ProxyHttpProvider) {
            setProxy(httpProvider.change());
        }
        logger.info("初始化{}类", downloader.getClass().getName());
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
    public void setThread(int threadNum) {
        downloader.setThread(rule.getThreadNum());
    }

    /**
     * handleProxy
     * @return ：
     * @author : 王龙
     * @Description ：启动通行服务,并初始化重试次数
     * @params ：Page downloader后的返回值
     * HttpProvider 通信服务
     * @Date ：2017/11/15 14:46
     */
    @Override
    public void handleProxy(Request request, HttpProvider provider, Callback<Proxy> callback) {
        if (provider.getChangeStatus().compareAndSet(false, true)) {
            logger.info("开始切换网络通信");
            Proxy proxy = provider.change();
            provider.signalAll();
            callback.execute(proxy);
            RequestUtils.setCycleRetryTimes(request);
        } else {
            logger.info("已有其他线程在切换通信，请等待...");
            provider.await();
        }
    }
}
