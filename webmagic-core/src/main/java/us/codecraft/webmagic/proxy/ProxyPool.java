package us.codecraft.webmagic.proxy;

/**
 * Created by edwardsbean on 15-2-28.
 */
public interface ProxyPool {

    void returnProxy(Proxy proxy, int statusCode);

    Proxy getProxy();
    
    boolean isEnable();
}
