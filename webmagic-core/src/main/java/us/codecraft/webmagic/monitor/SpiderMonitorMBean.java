package us.codecraft.webmagic.monitor;

import java.util.List;

/**
 * @author code4crafer@gmail.com
 */
public interface SpiderMonitorMBean {

    public List<SpiderStatus> getSpiders();

    public SpiderStatus getSpider();

}
