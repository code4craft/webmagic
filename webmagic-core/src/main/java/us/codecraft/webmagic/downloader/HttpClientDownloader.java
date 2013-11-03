package us.codecraft.webmagic.downloader;

import com.google.common.collect.Sets;
import org.apache.http.HttpResponse;
import org.apache.http.annotation.ThreadSafe;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.PlainText;
import us.codecraft.webmagic.utils.UrlUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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

    private final Map<String, CloseableHttpClient> httpClients = new HashMap<String, CloseableHttpClient>();

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
    public Html download(String url, String charset) {
        Page page = download(new Request(url), Site.me().setCharset(charset).toTask());
        return (Html) page.getHtml();
    }

    private CloseableHttpClient getHttpClient(Site site) {
        if (site == null) {
            return new HttpClientPool(poolSize).getClient(null);
        }
        String domain = site.getDomain();
        CloseableHttpClient httpClient = httpClients.get(domain);
        if (httpClient == null) {
            synchronized (this) {
                if (httpClient == null) {
                    httpClient = new HttpClientPool(poolSize).getClient(site);
                    httpClients.put(domain, httpClient);
                }
            }
        }
        return httpClient;
    }

    @Override
    public Page download(Request request, Task task) {
        Site site = null;
        if (task != null) {
            site = task.getSite();
        }
        Set<Integer> acceptStatCode;
        String charset = null;
        Map<String, String> headers = null;
        if (site != null) {
            acceptStatCode = site.getAcceptStatCode();
            charset = site.getCharset();
            headers = site.getHeaders();
        } else {
            acceptStatCode = Sets.newHashSet(200);
        }
        logger.info("downloading page " + request.getUrl());
        RequestBuilder requestBuilder = RequestBuilder.get().setUri(request.getUrl());
        if (headers != null) {
            for (Map.Entry<String, String> headerEntry : headers.entrySet()) {
                requestBuilder.addHeader(headerEntry.getKey(), headerEntry.getValue());
            }
        }
        RequestConfig.Builder requestConfigBuilder = RequestConfig.custom()
                .setConnectionRequestTimeout(site.getTimeOut())
                .setConnectTimeout(site.getTimeOut());
        if (site.getHttpProxy()!=null){
            requestConfigBuilder.setProxy(site.getHttpProxy());
        }
        requestBuilder.setConfig(requestConfigBuilder.build());
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = getHttpClient(site).execute(requestBuilder.build());
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (acceptStatCode.contains(statusCode)) {
                //charset
                if (charset == null) {
                    String value = httpResponse.getEntity().getContentType().getValue();
                    charset = UrlUtils.getCharset(value);
                }
                return handleResponse(request, charset, httpResponse, task);
            } else {
                logger.warn("code error " + statusCode + "\t" + request.getUrl());
                return null;
            }
        } catch (IOException e) {
            logger.warn("download page " + request.getUrl() + " error", e);
            if (site.getCycleRetryTimes() > 0) {
                return addToCycleRetry(request, site);
            }
            return null;
        } finally {
            try {
                if (httpResponse != null) {
                    httpResponse.close();
                }
            } catch (IOException e) {
                logger.warn("close response fail", e);
            }
        }
    }

    private Page addToCycleRetry(Request request, Site site) {
        Page page = new Page();
        Object cycleTriedTimesObject = request.getExtra(Request.CYCLE_TRIED_TIMES);
        if (cycleTriedTimesObject == null) {
            page.addTargetRequest(request.setPriority(0).putExtra(Request.CYCLE_TRIED_TIMES, 1));
        } else {
            int cycleTriedTimes = (Integer) cycleTriedTimesObject;
            cycleTriedTimes++;
            if (cycleTriedTimes >= site.getCycleRetryTimes()) {
                return null;
            }
            page.addTargetRequest(request.setPriority(0).putExtra(Request.CYCLE_TRIED_TIMES, 1));
        }
        return page;
    }

    protected Page handleResponse(Request request, String charset, HttpResponse httpResponse, Task task) throws IOException {
        String content = EntityUtils.toString(httpResponse.getEntity(), charset);
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
}
