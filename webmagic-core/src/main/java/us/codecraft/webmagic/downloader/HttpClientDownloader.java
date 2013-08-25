package us.codecraft.webmagic.downloader;

import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpResponse;
import org.apache.http.annotation.ThreadSafe;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.log4j.Logger;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.PlainText;
import us.codecraft.webmagic.utils.UrlUtils;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


/**
 * The http downloader based on HttpClient.
 *
 * @author code4crafter@gmail.com <br>
 * @since 0.1.0
 */
@ThreadSafe
public class HttpClientDownloader implements Downloader {

    private Logger logger = Logger.getLogger(getClass());

    private int poolSize = 1;

    /**
     * A simple method to download a url.
     *
     * @param url
     * @return html
     */
    public Html download(String url) {
        Page page = download(new Request(url), null);
        return (Html) page.getHtml();
    }

    /**
     * A simple method to download a url.
     *
     * @param url
     * @return html
     */
    public Html download(String url,String charset) {
        Page page = download(new Request(url), Site.me().setCharset(charset).toTask());
        return (Html) page.getHtml();
    }

    @Override
    public Page download(Request request, Task task) {
        Site site = null;
        if (task != null) {
            site = task.getSite();
        }
        int retryTimes = 0;
        Set<Integer> acceptStatCode;
        String charset = null;
        if (site != null) {
            retryTimes = site.getRetryTimes();
            acceptStatCode = site.getAcceptStatCode();
            charset = site.getCharset();
        } else {
            acceptStatCode = new HashSet<Integer>();
            acceptStatCode.add(200);
        }
        logger.info("downloading page " + request.getUrl());
        HttpClient httpClient = HttpClientPool.getInstance(poolSize).getClient(site);
        try {
            HttpGet httpGet = new HttpGet(request.getUrl());
            HttpResponse httpResponse = null;
            int tried = 0;
            boolean retry;
            do {
                try {
                    httpResponse = httpClient.execute(httpGet);
                    retry = false;
                } catch (IOException e) {
                    tried++;

                    if (tried > retryTimes) {
                        logger.warn("download page " + request.getUrl() + " error", e);
                        return null;
                    }
                    logger.info("download page " + request.getUrl() + " error, retry the " + tried + " time!");
                    retry = true;
                }
            } while (retry);
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (acceptStatCode.contains(statusCode)) {
                handleGzip(httpResponse);
                //charset
                if (charset == null) {
                    String value = httpResponse.getEntity().getContentType().getValue();
                    charset = UrlUtils.getCharset(value);
                }
                return handleResponse(request, charset, httpResponse, task);
            } else {
                logger.warn("code error " + statusCode + "\t" + request.getUrl());
            }
        } catch (Exception e) {
            logger.warn("download page " + request.getUrl() + " error", e);
        }
        return null;
    }

    protected Page handleResponse(Request request, String charset, HttpResponse httpResponse, Task task) throws IOException {
        String content = IOUtils.toString(httpResponse.getEntity().getContent(),
                charset);
        Page page = new Page();
        page.setHtml(new Html(UrlUtils.fixAllRelativeHrefs(content, request.getUrl())));
        page.setUrl(new PlainText(request.getUrl()));
        page.setRequest(request);
        return page;
    }

    @Override
    public void setThread(int thread) {
        poolSize = thread;
    }

    private void handleGzip(HttpResponse httpResponse) {
        Header ceheader = httpResponse.getEntity().getContentEncoding();
        if (ceheader != null) {
            HeaderElement[] codecs = ceheader.getElements();
            for (HeaderElement codec : codecs) {
                if (codec.getName().equalsIgnoreCase("gzip")) {
                    httpResponse.setEntity(
                            new GzipDecompressingEntity(httpResponse.getEntity()));
                }
            }
        }
    }
}
