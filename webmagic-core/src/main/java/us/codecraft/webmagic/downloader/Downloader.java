package us.codecraft.webmagic.downloader;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;

/**
 * User: cairne
 * Date: 13-4-21
 * Time: 下午12:14
 */
public interface Downloader {

    public Page download(Request request,Site site);
}
