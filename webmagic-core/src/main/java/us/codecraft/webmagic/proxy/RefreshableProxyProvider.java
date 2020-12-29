package us.codecraft.webmagic.proxy;

import us.codecraft.webmagic.Task;

/**
 * @author yaoqiang
 *
 * 可以手动刷新的代理供应商
 */
public interface RefreshableProxyProvider extends ProxyProvider{

    /**
     *  代理IP是珍贵资源，有可能代理提供者内部代理没有过期，就一直提供某个IP，但这个IP又不可以使用，所以提供一种方式通知提供者，这个代理该刷新了
     *
     * @param task  爬虫任务
     * @param proxy 需要对代理进行验证，如果确实持有的时错误代理，则刷新，否则，继续执行
     */
    void refreshProxy(Task task,Proxy proxy);


    /**
     *
     * 获取当前正在提供的代理
     *
     * @param task 工作中的爬虫任务
     * @return 获取当前正在使用的代理
     */
    Proxy getCurrentProxy(Task task);

}
