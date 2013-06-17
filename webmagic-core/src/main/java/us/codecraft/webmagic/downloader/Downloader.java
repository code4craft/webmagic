package us.codecraft.webmagic.downloader;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;

/**
 * Downloader是webmagic抓取页面的核心接口。
 * Author: code4crafter@gmail.com
 * Date: 13-4-21
 * Time: 下午12:14
 */
public interface Downloader {

    /**
     *
     * @param request
     * @param site
     * @return
     */
    public Page download(Request request,Site site);
}
