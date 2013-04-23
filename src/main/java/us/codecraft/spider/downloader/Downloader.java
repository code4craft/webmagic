package us.codecraft.spider.downloader;

import us.codecraft.spider.Page;
import us.codecraft.spider.Request;
import us.codecraft.spider.Site;

/**
 * User: cairne
 * Date: 13-4-21
 * Time: 下午12:14
 */
public interface Downloader {

    public Page download(Request request,Site site);
}
