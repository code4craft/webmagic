package us.codecraft.webmagic.proxy;

import org.apache.http.HttpHost;

/**
 * Created by edwardsbean on 15-2-28.
 */
public interface IProxyPool {
    public void returnProxy(HttpHost host, int statusCode);
    public HttpHost getProxy();
    public boolean isEnable();
}
