package us.codecraft.webmagic.downloader;

import com.github.dreamhead.moco.*;
import com.github.dreamhead.moco.Runnable;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.Ignore;
import org.junit.Test;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.selector.Html;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static com.github.dreamhead.moco.Moco.*;
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
        assertTrue(!html.getFirstSourceText().isEmpty());
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
    public void testGetHtmlCharset() throws Exception {
        HttpServer server = httpserver(12306);
        server.get(by(uri("/header"))).response(header("Content-Type", "text/html; charset=gbk"));
        server.get(by(uri("/meta4"))).response(with(text("<html>\n" +
                "  <head>\n" +
                "    <meta charset='gbk'/>\n" +
                "  </head>\n" +
                "  <body></body>\n" +
                "</html>")),header("Content-Type",""));
        server.get(by(uri("/meta5"))).response(with(text("<html>\n" +
                "  <head>\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=gbk\" />\n" +
                "  </head>\n" +
                "  <body></body>\n" +
                "</html>")),header("Content-Type",""));
        Runner.running(server, new Runnable() {
            @Override
            public void run() {
                String charset = getCharsetByUrl("http://127.0.0.1:12306/header");
                assertEquals(charset, "gbk");
                charset = getCharsetByUrl("http://127.0.0.1:12306/meta4");
                assertEquals(charset, "gbk");
                charset = getCharsetByUrl("http://127.0.0.1:12306/meta5");
                assertEquals(charset, "gbk");
            }

            private String getCharsetByUrl(String url) {
                HttpClientDownloader downloader = new HttpClientDownloader();
                Site site = Site.me();
                CloseableHttpClient httpClient = new HttpClientGenerator().getClient(site);
                // encoding in http header Content-Type
                Request requestGBK = new Request(url);
                CloseableHttpResponse httpResponse = null;
                try {
                    httpResponse = httpClient.execute(downloader.getHttpUriRequest(requestGBK, site, null));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String charset = null;
                try {
                    byte[] contentBytes = IOUtils.toByteArray(httpResponse.getEntity().getContent());
                    charset = downloader.getHtmlCharset(httpResponse,contentBytes);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return charset;
            }
        });
    }
}
