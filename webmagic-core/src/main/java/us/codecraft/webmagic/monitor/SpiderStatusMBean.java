package us.codecraft.webmagic.monitor;

import java.util.List;

/**
 * @author code4crafer@gmail.com
 * @since 0.5.0
 */
public interface SpiderStatusMBean {

    public String getName();

    public int getLeftPages();

    public int getTotalPages();
    public List<String> getErrorPages();

    public void start();

    public void stop();

}
