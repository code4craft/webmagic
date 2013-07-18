package us.codecraft.webmagic.downloader;

import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpResponse;
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


/**
 * @author code4crafter@gmail.com <br>
 *         Date: 13-4-21
 *         Time: 下午12:15
 */
public class HttpClientDownloader implements Downloader {

    private Logger logger = Logger.getLogger(getClass());

    @Override
    public Page download(Request request, Task task) {
        Site site = task.getSite();
        logger.info("downloading page " + request.getUrl());
        HttpClient httpClient = HttpClientPool.getInstance().getClient(site);
        String charset = site.getCharset();
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
                    if (tried > site.getRetryTimes()) {
                        logger.warn("download page " + request.getUrl() + " error", e);
                        return null;
                    }
                    logger.info("download page " + request.getUrl() + " error, retry the "+tried+" time!");
                    retry = true;
                }
            } while (retry);
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (site.getAcceptStatCode().contains(statusCode)) {
                //charset
                if (charset == null) {
                    String value = httpResponse.getEntity().getContentType().getValue();
                    charset = new PlainText(value).regex("charset=([^\\s]+)").toString();
                }
                //
                handleGzip(httpResponse);
                String content = IOUtils.toString(httpResponse.getEntity().getContent(),
                        charset);
                Page page = new Page();
                page.setHtml(new Html(UrlUtils.fixAllRelativeHrefs(content, request.getUrl())));
                page.setUrl(new PlainText(request.getUrl()));
                page.setRequest(request);
                return page;
            } else {
                logger.warn("code error " + statusCode + "\t" + request.getUrl());
            }
        } catch (Exception e) {
            logger.warn("download page " + request.getUrl() + " error", e);
        }
        return null;
    }

    private void handleGzip(HttpResponse httpResponse) {
        Header ceheader = httpResponse.getEntity().getContentEncoding();
        if (ceheader != null) {
            HeaderElement[] codecs = ceheader.getElements();
            for (int i = 0; i < codecs.length; i++) {
                if (codecs[i].getName().equalsIgnoreCase("gzip")) {
                    httpResponse.setEntity(
                            new GzipDecompressingEntity(httpResponse.getEntity()));
                }
            }
        }
    }
}
