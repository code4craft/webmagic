package us.codecraft.webmagic.proxy;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Task;

/**
 * Created by evan on 2018/8/6.
 */
public interface ProxyPageValidator {

    boolean proxyValid(Proxy proxy, Page page, Task task);

}
