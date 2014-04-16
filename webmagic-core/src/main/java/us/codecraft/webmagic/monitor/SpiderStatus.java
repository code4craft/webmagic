package us.codecraft.webmagic.monitor;

import us.codecraft.webmagic.Spider;

import java.util.List;

/**
 * @author code4crafer@gmail.com
 * @since 0.5.0
 */
public class SpiderStatus implements SpiderStatusMBean{

    private final Spider spider;

    private final SpiderMonitor.MonitorSpiderListener monitorSpiderListener;

    public SpiderStatus(Spider spider, SpiderMonitor.MonitorSpiderListener monitorSpiderListener) {
        this.spider = spider;
        this.monitorSpiderListener = monitorSpiderListener;
    }

    public String getName() {
        return spider.getUUID();
    }

    public int getLeftPages() {
        if (spider.getScheduler() instanceof MonitorableScheduler) {
            return ((MonitorableScheduler) spider.getScheduler()).getLeftRequestsCount(spider);
        }
        return -1;
    }

    public int getTotalPages() {
        if (spider.getScheduler() instanceof MonitorableScheduler) {
            return ((MonitorableScheduler) spider.getScheduler()).getTotalRequestsCount(spider);
        }
        return -1;
    }

    public List<String> getErrorPages() {
        return monitorSpiderListener.getErrorUrls();
    }

    public void start() {
        spider.start();
    }

    public void stop() {
        spider.stop();
    }

}
