package us.codecraft.webmagic.downloader;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;

/**
 * Downloader是webmagic下载页面的接口。webmagic默认使用了HttpComponent作为下载器，一般情况，你无需自己实现这个接口。<br>
 *
 * @author code4crafter@gmail.com <br>
 *         Date: 13-4-21
 *         Time: 下午12:14
 */
public interface Downloader {

    /**
     * 下载页面，并保存信息到Page对象中。
     *
     * @param request
     * @param task
     * @return page
     */
    public Page download(Request request, Task task);

    /**
     * 设置线程数，多线程程序一般需要Downloader支持<br>
     * 如果不考虑多线程的可以不实现这个方法<br>
     *
     * @param thread 线程数量
     */
    public void setThread(int thread);
}
