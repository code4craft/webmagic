package us.codecraft.webmagic.downloader;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.selector.PlainText;
import us.codecraft.webmagic.utils.UrlUtils;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Default http response handler
 * Created by zhoubing on 2016/12/20.
 */
public class DefaultHttpResponseHandler implements HttpResponseHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Page handleResponse(Request request, String charset, HttpResponse httpResponse, Task task) throws IOException {
        Page page = new Page();
        if (request.getType() == Request.Type.BYTES) {
            byte[] bytes = getContent(httpResponse);
            page.setRawBytes(bytes);
        }
        else if (request.getType() == Request.Type.STREAM) {
            page.setInputStream(httpResponse.getEntity().getContent());
        }
        else {
            //default handle response as plain text
            String content = getContent(charset, httpResponse);
            page.setRawText(content);
        }
        page.setUrl(new PlainText(request.getUrl()));
        page.setRequest(request);
        page.setStatusCode(httpResponse.getStatusLine().getStatusCode());
        return page;
    }

    @Override
    public void close(Request request, String charset, HttpResponse httpResponse, Task task) throws IOException {
        if (request.getType() == Request.Type.STREAM) {
            //do not close response stream
            return;
        }

        if (httpResponse != null) {
            try {
                EntityUtils.consume(httpResponse.getEntity());
            } catch (IOException e) {
                logger.warn("close response fail", e);
            }
        }
    }

    protected byte[] getContent(HttpResponse httpResponse) throws IOException {
        return IOUtils.toByteArray(httpResponse.getEntity().getContent());
    }

    protected String getContent(String charset, HttpResponse httpResponse) throws IOException {
        if (charset == null) {
            byte[] contentBytes = IOUtils.toByteArray(httpResponse.getEntity().getContent());
            String htmlCharset = getHtmlCharset(httpResponse, contentBytes);
            if (htmlCharset != null) {
                return new String(contentBytes, htmlCharset);
            } else {
                logger.warn("Charset autodetect failed, use {} as charset. Please specify charset in Site.setCharset()", Charset.defaultCharset());
                return new String(contentBytes);
            }
        } else {
            return IOUtils.toString(httpResponse.getEntity().getContent(), charset);
        }
    }

    protected String getHtmlCharset(HttpResponse httpResponse, byte[] contentBytes) throws IOException {
        String charset;
        // charset
        // 1、encoding in http header Content-Type
        String value = httpResponse.getEntity().getContentType().getValue();
        charset = UrlUtils.getCharset(value);
        if (StringUtils.isNotBlank(charset)) {
            logger.debug("Auto get charset: {}", charset);
            return charset;
        }
        // use default charset to decode first time
        Charset defaultCharset = Charset.defaultCharset();
        String content = new String(contentBytes, defaultCharset.name());
        // 2、charset in meta
        if (StringUtils.isNotEmpty(content)) {
            Document document = Jsoup.parse(content);
            Elements links = document.select("meta");
            for (Element link : links) {
                // 2.1、html4.01 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
                String metaContent = link.attr("content");
                String metaCharset = link.attr("charset");
                if (metaContent.indexOf("charset") != -1) {
                    metaContent = metaContent.substring(metaContent.indexOf("charset"), metaContent.length());
                    charset = metaContent.split("=")[1];
                    break;
                }
                // 2.2、html5 <meta charset="UTF-8" />
                else if (StringUtils.isNotEmpty(metaCharset)) {
                    charset = metaCharset;
                    break;
                }
            }
        }
        logger.debug("Auto get charset: {}", charset);
        // 3、todo use tools as cpdetector for content decode
        return charset;
    }
}
