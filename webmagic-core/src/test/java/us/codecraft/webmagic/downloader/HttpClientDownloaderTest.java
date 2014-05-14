package us.codecraft.webmagic.downloader;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.Ignore;
import org.junit.Test;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.selector.Html;

import java.io.UnsupportedEncodingException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
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
        Html html = httpClientDownloader.download("https://github.com");
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

    @Test
    public void testGetHtmlCharset() {
        HttpClientDownloader downloader = new HttpClientDownloader();
        Site site = Site.me();
        CloseableHttpClient httpClient = new HttpClientGenerator().getClient(site);
        try {
            // 头部包含编码
            Request requestGBK = new Request("http://sports.163.com/14/0514/13/9S7986F300051CA1.html#p=9RGQDGGH0AI90005");
            CloseableHttpResponse httpResponse = httpClient.execute(downloader.getHttpUriRequest(requestGBK, site, null));
            String charset = downloader.getHtmlCharset(httpResponse);
            assertEquals(charset, "GBK");

            // meta包含编码
            Request requestUTF_8 = new Request("http://preshing.com/");
            httpResponse = httpClient.execute(downloader.getHttpUriRequest(requestUTF_8, site, null));
            charset = downloader.getHtmlCharset(httpResponse);
            assertEquals(charset, "utf-8");

//            Request request = new Request("http://sports.163.com/14/0514/13/9S7986F300051CA1.html#p=9RGQDGGH0AI90005");
//            httpResponse = httpClient.execute(downloader.getHttpUriRequest(request, site, null));
//            charset = downloader.getHtmlCharset(httpResponse);
//            assertEquals(charset, "GBK");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
