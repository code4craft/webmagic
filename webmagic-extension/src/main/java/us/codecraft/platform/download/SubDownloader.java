package us.codecraft.platform.download;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.platform.Callback;
import us.codecraft.platform.download.proxy.provider.HttpProvider;
import us.codecraft.platform.download.trigger.PageTrigger;
import us.codecraft.platform.utils.RequestUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.proxy.Proxy;

/**
 * 多下载器接口
 *
 * @author 王龙
 * @description
 * @date 2017年8月10日 下午5:48:21
 */
public interface SubDownloader extends Downloader, PatternMatcher {
    Logger logger = LoggerFactory.getLogger(SubDownloader.class);

    /**
     * 子类需要在方法提内实现父类属性
     *
     * @return ：
     * @author : 王龙
     * @Description ：
     * @params ：
     * @Date ：2017/11/15 14:32
     */
    void initSuper();

    /**
     * 当使用ProxyHttpProvider时，切换代理的IP
     *
     * @return ：
     * @author : 王龙
     * @Description ：
     * @params ：
     * @Date ：2017/11/15 15:20
     */
    void setProxy(Proxy proxy);

    /**
     * 获取通行服务实现类
     *
     * @return ：
     * @author : 王龙
     * @Description ：
     * @Date ：2017/11/15 14:41
     */
    HttpProvider getHttpProvider();

    /**
     * 获取页面处理实现类
     *
     * @return ：
     * @author : 王龙
     * @Description ：
     * @Date ：2017/11/15 14:42
     */
    PageTrigger getPageTrigger();

    /**
     * 获取实际操作的downloader
     *
     * @return ：
     * @author : 王龙
     * @Description ：
     * @params ：
     * @Date ：2017/11/15 18:59
     */
    Downloader getDownloader();

    @Override
    default Page download(Request request, Task task) {
        logger.info("开始下载指定url：【{}】", request.getUrl());
        long start = System.currentTimeMillis();
        Page page = getDownloader().download(request, task);
        logger.info("task：【{}】的指定url【{}】页面download结束,耗时：{}ms", task.getUUID(), request.getUrl(), System.currentTimeMillis() - start);
        if (getPageTrigger().isNeedChangeProxy(page) && RequestUtils.getCycleRetryTimes(request) == 0) {
            logger.info("页面不符合要求，且循环次数已用完，开始切换proxy");
            handleProxy(page.getRequest(), getHttpProvider(), new Callback<Proxy>() {
                @Override
                public boolean execute(Proxy obj) {
                    setProxy(obj);
                    return false;
                }
            });
        }
        return page;
    }

    void handleProxy(Request request, HttpProvider provider, Callback<Proxy> callback);
}