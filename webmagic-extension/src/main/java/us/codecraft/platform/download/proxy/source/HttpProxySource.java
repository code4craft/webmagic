package us.codecraft.platform.download.proxy.source;

import us.codecraft.webmagic.proxy.Proxy;

/**
 * 代理数据源接口
 *
 * @author 王龙
 * @date 2017年8月13日 下午2:46:22
 */
public interface HttpProxySource {
    /**
     * 释放一个代理
     *
     * @author 刘太信
     * @date 2017年8月13日 下午2:46:34
     * @return
     */
    Proxy get();
}