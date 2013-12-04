package us.codecraft.webmagic.downloader;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.selector.Html;

/**
 * Author: code4crafer@gmail.com
 * Date: 13-6-18
 * Time: 上午8:22
 */
public class HttpClientDownloaderTest {

    @Ignore
    @Test
    public void testCookie() {
        Site site = Site.me().setDomain("www.diandian.com").addCookie("t", "yct7q7e6v319wpg4cpxqduu5m77lcgix");
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        Page download = httpClientDownloader.download(new Request("http://www.diandian.com"), site.toTask());
        Assert.assertTrue(download.getHtml().toString().contains("flashsword30"));
    }

    @Test
    public void testDownloader() {
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        Html html = httpClientDownloader.download("http://www.oschina.net");
        Assert.assertTrue(!html.getText().isEmpty());
    }

}
