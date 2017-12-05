package us.codecraft.platform.download.selenium;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.platform.Callback;
import us.codecraft.platform.download.Pool;
import us.codecraft.platform.download.proxy.provider.HttpProvider;
import us.codecraft.platform.utils.RequestUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.AbstractDownloader;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.selector.PlainText;

import java.util.Map;

/**
 * Selenium解决ajax动态页面、iframe动态页面、302跳转页面下载，还原最终呈现给用户的界面进行下载
 * <p>
 * 注意：
 * </p>
 * <ol>
 * <li>代理请尽量使用adsl切换ip代理策略</li>
 * <li>http代理策略速度很慢，请尽量使用收费代理，否则超时严重</li>
 * <li>提供getResponseMap抽象方法，子类根据自己的功能实现获取页面响应</li>
 * </ol>
 *
 * @author 刘太信
 * @date 2017年8月10日 下午5:50:08
 */
public abstract class SeleniumDownloader<T> extends AbstractDownloader {
    private static Logger logger = LoggerFactory.getLogger(SeleniumDownloader.class);
    protected Pool pool;

    /**
     * @return ：
     * @author : 王龙
     * @Description ： 核心下载流程
     * @params ：
     * @Date ：2017/11/21 14:59
     */
    @Override
    public Page download(Request request, Task task) {
        logger.debug("开始下载指定url：【{}】", request.getUrl());
        long start = System.currentTimeMillis();
        Page page = Page.fail();
        T driver = null;
        try {
            driver = getPool().get();
            Map<String, Object> responseMap = getResponseMap(driver, request, task);
            page = handleResponse(request, responseMap);
        } finally {
            if (null != driver) {
                getPool().returnToPool(driver);
            }
        }
        logger.debug("task：【{}】的指定url【{}】页面download结束,耗时：{}ms", task.getUUID(), request.getUrl(), System.currentTimeMillis() - start);
        return page;
    }

    protected Page handleResponse(Request request, Map<String, Object> responseMap) {
        Page page = Page.fail();
        if (null != responseMap.get("content")) {
            String content = responseMap.get("content").toString();
            String statusCode = responseMap.get("statusCode").toString();
            logger.debug("将url:【{}】的content转换page对象", request.getUrl());
            page.setRawText(content);
            page.setStatusCode(Integer.valueOf(statusCode));
            page.setUrl(new PlainText(request.getUrl()));
            page.setRequest(request);
            page.setDownloadSuccess(true);
            logger.info("url:【{}】的page属性设置完成，其statusCode：【{}】", request.getUrl(), page.getStatusCode());
        }
        return page;
    }

    /**
     * 切换driver的实现
     *
     * @param proxy
     */
    public void setProxy(Proxy proxy) {
        //清空driverPool中现有的driver
        getPool().clear();
        // 配置caps
        getPool().setProxy(webmagicProxy2SeleniumProxy(proxy));
    }

    protected org.openqa.selenium.Proxy webmagicProxy2SeleniumProxy(Proxy proxy) {
        String hostAndPort = proxy.getHost() + ":" + proxy.getPort();
        org.openqa.selenium.Proxy seleniumProxy = new org.openqa.selenium.Proxy();
        seleniumProxy.setHttpProxy(hostAndPort).setSslProxy(hostAndPort);
        return seleniumProxy;
    }

    /**
     * webdriver池
     */
    public abstract void setWorkersPool();

    /**
     * webdriver池
     */
    public Pool<T> getPool() {
        if (null == pool) {
            synchronized (this) {
                if (null == pool) {
                    setWorkersPool();
                }
            }
        }
        return pool;
    }

    /**
     * 通过driver获取请求的Response并以Map的形式返回
     * map中必须包含content、statusCode键对应的值
     *
     * @return
     */
    public abstract Map<String, Object> getResponseMap(T worker, Request request, Task task);

    @Override
    public void setThread(int threadNum) {
        getPool().setPoolSize(threadNum);
    }


    /**
     * handleProxy
     *
     * @return ：
     * @author : 王龙
     * @Description ：启动通信服务,并初始化request重试次数
     * @params ：Page downloader后的返回值
     * HttpProvider 通信服务
     * @Date ：2017/11/15 14:46
     */
    public void handleProxy(Request request, HttpProvider provider, Callback<Proxy> callback) {
        if (provider.getChangeStatus().compareAndSet(false, true)) {
            if (pool.waitAllworkerReturn()) {
                logger.info("开始切换ip...");
                Proxy proxy = provider.change();
                provider.signalAll();
                callback.execute(proxy);
                RequestUtils.setCycleRetryTimes(request);
                pool.setIsWorkersReturning(false);
            }
        } else {
            logger.info("已有其他线程在切换ip，请等待...");
            provider.await();
        }
    }
}
