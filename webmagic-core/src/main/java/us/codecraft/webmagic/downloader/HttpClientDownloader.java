package us.codecraft.webmagic.downloader;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.log4j.Logger;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.PlainText;
import us.codecraft.webmagic.utils.UrlUtils;


/**
 * @author code4crafter@gmail.com <br>
 * Date: 13-4-21
 * Time: 下午12:15
 */
public class HttpClientDownloader implements Downloader {

    private Logger logger = Logger.getLogger(getClass());

    @Override
    public Page download(Request request, Site site) {
        logger.info("downloading page " + request.getUrl());
        HttpClient httpClient = HttpClientPool.getInstance().getClient(site);
        try {
            HttpGet httpGet = new HttpGet(request.getUrl());
            HttpResponse httpResponse = httpClient.execute(httpGet);
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (site.getAcceptStatCode().contains(statusCode)) {
                if (site.getEncoding() == null){
                    String value = httpResponse.getEntity().getContentType().getValue();
                    site.setEncoding(new PlainText(value).regex("charset=([^\\s]+)").toString());
                }
                String content = IOUtils.toString(httpResponse.getEntity().getContent(),
                         site.getEncoding());
                Page page = new Page();
                page.setHtml(new Html(UrlUtils.fixAllRelativeHrefs(content, request.getUrl())));
                page.setUrl(new PlainText(request.getUrl()));
                page.setRequest(request);
                return page;
            } else {
                logger.warn("code error " + statusCode);
            }
        } catch (Exception e) {
            logger.warn("download page " + request.getUrl() + " error", e);
        }
        return null;
    }
}
