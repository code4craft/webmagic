package us.codecraft.webmagic.downloader;

import com.github.dreamhead.moco.HttpServer;
import com.github.dreamhead.moco.Runnable;
import com.github.dreamhead.moco.Runner;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.utils.HttpConstant;

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

    public static final String PAGE_ALWAYS_NOT_EXISTS = "http://localhost:13421/404";

    @Test
    public void testDownloader() {
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        Html html = httpClientDownloader.download("https://www.baidu.com/");
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
        Request request = new Request(PAGE_ALWAYS_NOT_EXISTS);
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
                CloseableHttpClient httpClient = new HttpClientGenerator().getClient(site, null);
                // encoding in http header Content-Type
                Request requestGBK = new Request(url);
                CloseableHttpResponse httpResponse = null;
                try {
                    httpResponse = httpClient.execute(downloader.getHttpUriRequest(requestGBK, site, null,null));
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

    @Test
    public void test_selectRequestMethod() throws Exception {
        HttpServer server = httpserver(12306);
        server.get(eq(query("q"), "webmagic")).response("get");
        server.post(eq(form("q"), "webmagic")).response("post");
        server.put(eq(form("q"), "webmagic")).response("put");
        server.delete(eq(query("q"), "webmagic")).response("delete");
        server.request(and(by(method("HEAD")),eq(query("q"), "webmagic"))).response(header("method","head"));
        server.request(and(by(method("TRACE")),eq(query("q"), "webmagic"))).response("trace");
        Runner.running(server, new Runnable() {
            @Override
            public void run() throws Exception {
                HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
                Request request = new Request();
                request.setUrl("http://127.0.0.1:12306/search");
                request.putParams("q", "webmagic");
                request.setMethod(HttpConstant.Method.GET);
                RequestBuilder requestBuilder = httpClientDownloader.selectRequestMethod(request).setUri(request.getUrl());
                assertThat(EntityUtils.toString(HttpClients.custom().build().execute(requestBuilder.build()).getEntity())).isEqualTo("get");
                request.setMethod(HttpConstant.Method.POST);
                requestBuilder = httpClientDownloader.selectRequestMethod(request).setUri(request.getUrl());
                assertThat(EntityUtils.toString(HttpClients.custom().build().execute(requestBuilder.build()).getEntity())).isEqualTo("post");
                request.setMethod(HttpConstant.Method.PUT);
                requestBuilder = httpClientDownloader.selectRequestMethod(request).setUri(request.getUrl());
                assertThat(EntityUtils.toString(HttpClients.custom().build().execute(requestBuilder.build()).getEntity())).isEqualTo("put");
                request.setMethod(HttpConstant.Method.DELETE);
                requestBuilder = httpClientDownloader.selectRequestMethod(request).setUri(request.getUrl());
                assertThat(EntityUtils.toString(HttpClients.custom().build().execute(requestBuilder.build()).getEntity())).isEqualTo("delete");
                request.setMethod(HttpConstant.Method.HEAD);
                requestBuilder = httpClientDownloader.selectRequestMethod(request).setUri(request.getUrl());
                assertThat(HttpClients.custom().build().execute(requestBuilder.build()).getFirstHeader("method").getValue()).isEqualTo("head");
                request.setMethod(HttpConstant.Method.TRACE);
                requestBuilder = httpClientDownloader.selectRequestMethod(request).setUri(request.getUrl());
                assertThat(EntityUtils.toString(HttpClients.custom().build().execute(requestBuilder.build()).getEntity())).isEqualTo("trace");
            }
        });
    }

    @Test
    public void test_download_when_task_is_null() throws Exception {
        HttpServer server = httpserver(12306);
        server.response("foo");
        Runner.running(server, new Runnable() {
            @Override
            public void run() throws Exception {
                final HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
                Request request = new Request();
                request.setUrl("http://127.0.0.1:12306/");
                Page page = httpClientDownloader.download(request, null);
                assertThat(page.getRawText()).isEqualTo("foo");
            }
        });
    }
}
