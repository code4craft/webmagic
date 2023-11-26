package us.codecraft.webmagic;

import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Json;
import us.codecraft.webmagic.selector.Selectable;
import us.codecraft.webmagic.utils.HttpConstant;
import us.codecraft.webmagic.utils.UrlUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Object storing extracted result and urls to fetch.<br>
 * Not thread safe.<br>
 * Main method：                                               <br>
 * {@link #getUrl()} get url of current page                   <br>
 * {@link #getHtml()}  get content of current page                 <br>
 * {@link #putField(String, Object)}  save extracted result            <br>
 * {@link #getResultItems()} get extract results to be used in {@link us.codecraft.webmagic.pipeline.Pipeline}<br>
 * {@link #addTargetRequests(Iterable)} {@link #addTargetRequest(String)} add urls to fetch                 <br>
 *
 * @author code4crafter@gmail.com <br>
 * @see us.codecraft.webmagic.downloader.Downloader
 * @see us.codecraft.webmagic.processor.PageProcessor
 * @since 0.1.0
 */
public class Page {

    private Request request;

    private ResultItems resultItems = new ResultItems();

    private Html html;

    private Json json;

    private String rawText;

    private Selectable url;

    private Map<String,List<String>> headers;

    private int statusCode = HttpConstant.StatusCode.CODE_200;

    private boolean downloadSuccess = true;

    private byte[] bytes;

    private List<Request> targetRequests = new ArrayList<>();

    private String charset;

    public Page() {
    }

    /**
     * Returns a {@link Page} with {@link #downloadSuccess} is {@code false}.
     *
     * @return the page.
     * @deprecated Use {@link #fail(Request)} instead.
     */
    @Deprecated
    public static Page fail() {
        return fail(null);
    }

    /**
     * Returns a {@link Page} with {@link #downloadSuccess} is {@code false},
     * and {@link #request} is specified.
     *
     * @return the page.
     * @since 0.10.0
     */
    public static Page fail(Request request){
        Page page = new Page();
        page.setRequest(request);
        page.setDownloadSuccess(false);
        return page;
    }

    public Page setSkip(boolean skip) {
        resultItems.setSkip(skip);
        return this;

    }

    /**
     * store extract results
     *
     * @param key key
     * @param field field
     */
    public void putField(String key, Object field) {
        resultItems.put(key, field);
    }

    /**
     * get html content of page
     *
     * @return html
     */
    public Html getHtml() {
        if (html == null) {
            html = new Html(rawText, request.getUrl());
        }
        return html;
    }

    /**
     * get json content of page
     *
     * @return json
     * @since 0.5.0
     */
    public Json getJson() {
        if (json == null) {
            json = new Json(rawText);
        }
        return json;
    }

    /**
     * @param html html
     * @deprecated since 0.4.0
     * The html is parse just when first time of calling {@link #getHtml()}, so use {@link #setRawText(String)} instead.
     */
    @Deprecated
	public void setHtml(Html html) {
        this.html = html;
    }

    public List<Request> getTargetRequests() {
        return targetRequests;
    }

    /**
     * add urls to fetch
     *
     * @param requests requests
     */
    public void addTargetRequests(Iterable<String> requests) {
    	addTargetRequests(requests, 0); // Default priority is 0
    }

    /**
     * add urls to fetch
     *
     * @param requests requests
     * @param priority priority
     */
    public void addTargetRequests(Iterable<String> requests, long priority) {
    	if(requests == null) {
    		return;
    	}
    	
        for (String req : requests) {
        	addRequestIfValid(req, priority);
        }
    }
    
    /**
     * Helper method to add a request if it's valid.
     *
     * @param url      URL to add
     * @param priority Priority for the URL
     */
    private void addRequestIfValid(String url, long priority) {
        if (StringUtils.isBlank(url) || url.equals("#") || url.startsWith("javascript:")) {
            return;
        }

        String canonicalizedUrl = UrlUtils.canonicalizeUrl(url, this.url.toString());
        Request req = new Request(canonicalizedUrl);
        if(priority > 0) {
            req.setPriority(priority);
        }
        targetRequests.add(req);
    }

    /**
     * add url to fetch
     *
     * @param requestString requestString
     */
    public void addTargetRequest(String requestString) {
        if (StringUtils.isBlank(requestString) || requestString.equals("#")) {
            return;
        }
        requestString = UrlUtils.canonicalizeUrl(requestString, url.toString());
        targetRequests.add(new Request(requestString));
    }

    /**
     * add requests to fetch
     *
     * @param request request
     */
    public void addTargetRequest(Request request) {
        targetRequests.add(request);
    }

    /**
     * get url of current page
     *
     * @return url of current page
     */
    public Selectable getUrl() {
        return url;
    }

    public void setUrl(Selectable url) {
        this.url = url;
    }

    /**
     * get request of current page
     *
     * @return request
     */
    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
        this.resultItems.setRequest(request);
    }

    public ResultItems getResultItems() {
        return resultItems;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getRawText() {
        return rawText;
    }

    public Page setRawText(String rawText) {
        this.rawText = rawText;
        return this;
    }

    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, List<String>> headers) {
        this.headers = headers;
    }

    public boolean isDownloadSuccess() {
        return downloadSuccess;
    }

    public void setDownloadSuccess(boolean downloadSuccess) {
        this.downloadSuccess = downloadSuccess;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    @Override
    public String toString() {
        return "Page{" +
                "request=" + request +
                ", resultItems=" + resultItems +
                ", html=" + html +
                ", json=" + json +
                ", rawText='" + rawText + '\'' +
                ", url=" + url +
                ", headers=" + headers +
                ", statusCode=" + statusCode +
                ", downloadSuccess=" + downloadSuccess +
                ", targetRequests=" + targetRequests +
                ", charset='" + charset + '\'' +
                ", bytes=" + Arrays.toString(bytes) +
                '}';
    }
}
