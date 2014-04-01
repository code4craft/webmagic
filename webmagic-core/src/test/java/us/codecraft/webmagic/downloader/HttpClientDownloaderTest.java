package us.codecraft.webmagic.downloader;

import org.junit.Ignore;
import org.junit.Test;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.selector.Html;

import java.io.UnsupportedEncodingException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * @author code4crafer@gmail.com
 */
public class HttpClientDownloaderTest {

    @Ignore
    @Test
    public void testCookie() {
        Site site = Site.me().setDomain("www.diandian.com").addCookie("t", "43ztv9srfszl99yxv2aumx3zr7el7ybb");
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        Page download = httpClientDownloader.download(new Request("http://www.diandian.com"), site.toTask());
        assertTrue(download.getHtml().toString().contains("flashsword30"));
    }

    @Test
    public void testDownloader() {
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        Html html = httpClientDownloader.download("http://www.oschina.net");
        assertTrue(!html.getText().isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDownloaderInIllegalUrl() throws UnsupportedEncodingException {
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        httpClientDownloader.download("http://www.oschina.net/>");
    }

    @Test
    public void testCycleTriedTimes() {
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        Task task = Site.me().setDomain("localhost").setCycleRetryTimes(5).toTask();
        Request request = new Request("http://localhost/404");
        Page page = httpClientDownloader.download(request, task);
        assertThat(page.getTargetRequests().size() > 0);
        assertThat((Integer) page.getTargetRequests().get(0).getExtra(Request.CYCLE_TRIED_TIMES)).isEqualTo(1);
        page = httpClientDownloader.download(page.getTargetRequests().get(0), task);
        assertThat((Integer) page.getTargetRequests().get(0).getExtra(Request.CYCLE_TRIED_TIMES)).isEqualTo(2);
    }

}
