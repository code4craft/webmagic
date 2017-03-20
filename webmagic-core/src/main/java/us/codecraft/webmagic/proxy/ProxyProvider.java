package us.codecraft.webmagic.proxy;

import us.codecraft.webmagic.Task;

/**
 * Created by edwardsbean on 15-2-28.
 */
public interface ProxyProvider {

    void returnProxy(Proxy proxy, boolean banned, Task task);

    Proxy getProxy(Task task);
    
}
