package us.codecraft.platform.download.proxy.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.platform.download.proxy.source.HttpProxySource;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.utils.ProxyUtils;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 代理IP通信服务
 *
 * @author 王龙
 * @date 2017年8月15日 下午6:49:29
 */
public class ProxyHttpProvider implements HttpProvider {
    public static Logger logger = LoggerFactory.getLogger(ProxyHttpProvider.class);
    private ReentrantLock reentrantLock = new ReentrantLock();
    private Condition changgeCondition = reentrantLock.newCondition();
    private AtomicBoolean changeStatus = new AtomicBoolean(false);
    private HttpProxySource proxySource;
    private Proxy proxy;

    /**
     * 构造函数
     *
     * @param proxySource http数据源
     * @author 刘太信
     * @date 2017年8月15日 下午6:49:27
     */
    public ProxyHttpProvider(HttpProxySource proxySource) {
        this.proxySource = proxySource;
    }

    /**
     * 切换代理
     *
     * @return http代理会返回ip端口帐号密码(当返回null请download执行清除代理设置)，adsl代理永远返回null
     * @author : 王龙
     * @author 刘太信
     * @date 2017年8月15日 下午6:09:17
     */
    @Override
    public Proxy change() {
        int time = 0;
        while (++time < tryTime) {
            try {
                proxy = proxySource.get();
                if (null != proxy && ProxyUtils.validateProxy(proxy)) {
                    logger.info(String.format("代理切换成功，代理ip：%s，代理port:%d", proxy.getHost(), proxy.getPort()));
                    break;
                }
            } catch (Exception e) {
                logger.error(String.format("第 %d 切换代理异常", time), e);
            }
        }
        return proxy;
    }

    @Override
    public AtomicBoolean getChangeStatus() {
        return changeStatus;
    }

    @Override
    public ReentrantLock getReentrantLock() {
        return reentrantLock;
    }

    @Override
    public Condition getChangeCondition() {
        return changgeCondition;
    }


}
