package us.codecraft.webmagic;

import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;
import us.codecraft.webmagic.utils.UrlUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Object storing extracted result and urls to fetch.<br>
 * Main method：                                               <br>
 * {@link #getUrl()} get url of current page                   <br>
 * {@link #getHtml()}  get content of current page                 <br>
 * {@link #putField(String, Object)}  save extracted result            <br>
 * {@link #getResultItems()} get extract results to be used in {@link us.codecraft.webmagic.pipeline.Pipeline}<br>
 * {@link #addTargetRequests(java.util.List)} {@link #addTargetRequest(String)} add urls to fetch                 <br>
 *
 * @author code4crafter@gmail.com <br>
 * @since 0.1.0
 * @see us.codecraft.webmagic.downloader.Downloader
 * @see us.codecraft.webmagic.processor.PageProcessor
 */
public class Page {

    private Request request;

    private ResultItems resultItems = new ResultItems();

    private Html html;

    private Selectable url;

    private List<Request> targetRequests = new ArrayList<Request>();

    public Page() {
    }

    public Page setSkip(boolean skip) {
        resultItems.setSkip(skip);
        return this;

    }

    /**
     * store extract results
     *
     * @param key
     * @param field
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
        return html;
    }

    public void setHtml(Html html) {
        this.html = html;
    }

    public List<Request> getTargetRequests() {
        return targetRequests;
    }

    /**
     * add urls to fetch
     *
     * @param requests
     */
    public void addTargetRequests(List<String> requests) {
        synchronized (targetRequests) {
            for (String s : requests) {
                if (StringUtils.isBlank(s) || s.equals("#") || s.startsWith("javascript:")) {
                    break;
                }
                s = UrlUtils.canonicalizeUrl(s, url.toString());
                targetRequests.add(new Request(s));
            }
        }
    }

    /**
     * add urls to fetch
     *
     * @param requests
     */
    public void addTargetRequests(List<String> requests,long priority) {
        synchronized (targetRequests) {
            for (String s : requests) {
                if (StringUtils.isBlank(s) || s.equals("#") || s.startsWith("javascript:")) {
                    break;
                }
                s = UrlUtils.canonicalizeUrl(s, url.toString());
                targetRequests.add(new Request(s).setPriority(priority));
            }
        }
    }

    /**
     * add url to fetch
     *
     * @param requestString
     */
    public void addTargetRequest(String requestString) {
        if (StringUtils.isBlank(requestString) || requestString.equals("#")) {
            return;
        }
        synchronized (targetRequests) {
            requestString = UrlUtils.canonicalizeUrl(requestString, url.toString());
            targetRequests.add(new Request(requestString));
        }
    }

    /**
     * add requests to fetch
     *
     * @param request
     */
    public void addTargetRequest(Request request) {
        synchronized (targetRequests) {
            targetRequests.add(request);
        }
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

    @Override
    public String toString() {
        return "Page{" +
                "request=" + request +
                ", resultItems=" + resultItems +
                ", html=" + html +
                ", url=" + url +
                ", targetRequests=" + targetRequests +
                '}';
    }
}
