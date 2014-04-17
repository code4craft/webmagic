package us.codecraft.webmagic.monitor;

import java.util.List;

/**
 * @author code4crafer@gmail.com
 * @since 0.5.0
 */
public interface SpiderStatusMBean {

    public String getName();

    public String getStatus();

    public int getThread();

    public int getTotalPageCount();

    public int getLeftPageCount();

    public int getSuccessPageCount();

    public int getErrorPageCount();

    public List<String> getErrorPages();

    public void start();

    public void stop();
}
