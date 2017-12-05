package us.codecraft.platform.download.selenium.cdp4j;

import io.webfolder.cdp.event.network.LoadingFinished;
import io.webfolder.cdp.event.network.ResponseReceived;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.type.network.GetResponseBodyResult;
import io.webfolder.cdp.type.network.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.platform.Callback;
import us.codecraft.platform.download.DownloaderRule;
import us.codecraft.platform.download.SubDownloader;
import us.codecraft.platform.download.proxy.provider.HttpProvider;
import us.codecraft.platform.download.proxy.provider.ProxyHttpProvider;
import us.codecraft.platform.download.selenium.SeleniumDownloader;
import us.codecraft.platform.download.trigger.PageTrigger;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.proxy.Proxy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

import static io.webfolder.cdp.event.Events.NetworkLoadingFinished;
import static io.webfolder.cdp.event.Events.NetworkResponseReceived;

/**
 * 使用 Chrome DevTools Protocol for Java 作为js页面渲染工具，参考https://github.com/webfolderio/cdp4j
 *
 * @author : 王龙
 * @Description ：
 * @params ：
 * @return ：
 * @Date ：2017/11/23 17:26
 */
public class CdpSeleniumSubDownloader implements SubDownloader {
    private static Logger logger = LoggerFactory.getLogger(CdpSeleniumSubDownloader.class);

    private DownloaderRule rule;

    private HttpProvider httpProvider;

    private PageTrigger pageTrigger;

    private SeleniumDownloader<Session> downloader;

    private List<String> configList = new ArrayList<>();

    private CdpSeleniumSubDownloader() {
    }

    public CdpSeleniumSubDownloader(DownloaderRule rule) {
        this.rule = rule;
        this.httpProvider = rule.getHttpProvider();
        this.pageTrigger = rule.getPageTrigger();
        initSuper();
    }


    public CdpSeleniumSubDownloader(DownloaderRule rule, List<String> configList) {
        this.rule = rule;
        this.httpProvider = rule.getHttpProvider();
        this.pageTrigger = rule.getPageTrigger();
        this.configList = configList;
        initSuper();
    }

    /**
     * 子类需要在方法提内实现父类属性
     *
     * @return ：
     * @author : 王龙
     * @Description ：
     * @params ：
     * @Date ：2017/11/15 14:32
     */
    @Override
    public void initSuper() {
        downloader = new CdpSeleniumDownloader();
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

    @Override
    public void handleProxy(Request request, HttpProvider provider, Callback<Proxy> callback) {
        downloader.handleProxy(request, provider, callback);
    }

    class CdpSeleniumDownloader extends SeleniumDownloader<Session> {

        /**
         * webdriver池
         */
        @Override
        public void setWorkersPool() {
            pool = new SessionPool(configList);
        }

        /**
         * 通过driver获取请求的Response并以Map的形式返回
         * map中必须包含content、statusCode键对应的值
         *
         * @param
         * @param request
         * @param task
         * @return
         */
        @Override
        public Map<String, Object> getResponseMap(Session session, Request request, Task task) {
            Set<String> finished = new HashSet<>();
            ConcurrentHashMap<String, Object> resultMap = new ConcurrentHashMap<>();
            session.getCommand().getNetwork().enable();
            session.addEventListener((e, d) -> {
                if (NetworkLoadingFinished.equals(e)) {
                    LoadingFinished lf = (LoadingFinished) d;
                    if (finished.contains(lf.getRequestId())) {
                        GetResponseBodyResult rb = session.getCommand().getNetwork().getResponseBody(lf.getRequestId());
                        if (rb != null) {
                            String content = rb.getBody();
                            resultMap.put("content", content);
                        }
                    }
                }

                if (NetworkResponseReceived.equals(e)) {
                    ResponseReceived rr = (ResponseReceived) d;
                    Response response = rr.getResponse();
                    String responseUrl = response.getUrl();
                    String requestUrl = request.getUrl();
                    if ("text/html".equals(response.getMimeType()) && responseUrl.contains(requestUrl) && (responseUrl.length() <= requestUrl.length() + 1)) {
                        finished.add(rr.getRequestId());
                        logger.debug("----------------------------------------");
                        logger.debug("URL       : " + response.getUrl());
                        logger.debug("Status    : HTTP " + response.getStatus().intValue() + " " + response.getStatusText());
                        logger.debug("Mime Type : " + response.getMimeType());
                        logger.debug("IPAddress ：" + response.getRemoteIPAddress() + ":" + response.getRemotePort());
                        resultMap.put("statusCode", response.getStatus());
                    }
                }
            });

            session.navigate(request.getUrl());
            session.waitDocumentReady();
            return resultMap;
        }
    }

    @Override
    public void setThread(int threadNum) {
        downloader.setThread(rule.getThreadNum());
    }
}
