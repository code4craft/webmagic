package us.codecraft.webmagic.proxy;

import us.codecraft.webmagic.Task;

/**
 * Created by edwardsbean on 15-2-28.
 */
public interface ProxyPool {

    void returnProxy(Proxy proxy, int statusCode, Task task);

    Proxy getProxy(Task task);
    
}
