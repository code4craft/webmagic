package us.codecraft.webmagic.proxy;

import org.apache.http.HttpHost;

/**
 * Created by edwardsbean on 15-2-28.
 */
public interface ProxyPool {

    void returnProxy(HttpHost host, int statusCode);

    Proxy getProxy();
    
    boolean isEnable();
}
