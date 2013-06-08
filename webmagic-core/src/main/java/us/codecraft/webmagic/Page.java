package us.codecraft.webmagic;

import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.selector.Selectable;
import us.codecraft.webmagic.utils.UrlUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * User: cairne
 * Date: 13-4-21
 * Time: 上午11:22
 */
public class Page {

    private Request request;

    private Map<String, Selectable> fields = new ConcurrentHashMap<String, Selectable>();

    private Selectable html;

    private Selectable url;

    private List<Request> targetRequests = new ArrayList<Request>();

    public void process() {
        fields.put("title", html.x("").r(""));
    }

    public Page() {
    }

    public Map<String, Selectable> getFields() {
        return fields;
    }

    public void putField(String key, Selectable field) {
        fields.put(key, field);
    }

    public Selectable getHtml() {
        return html;
    }

    public void setHtml(Selectable html) {
        this.html = html;
    }

    public List<Request> getTargetRequests() {
        return targetRequests;
    }

    public void addTargetRequests(List<String> requests) {
        synchronized (targetRequests) {
            for (String s : requests) {
                if (StringUtils.isBlank(s) || s.equals("#") || s.startsWith("javascript:")) {
                    break;
                }
                s = UrlUtils.fixRelativeUrl(s, url.toString());
                targetRequests.add(new Request(s));
            }
        }
    }

    public void addTargetRequests(String requestString) {
        if (StringUtils.isBlank(requestString) || requestString.equals("#")) {
            return;
        }
        synchronized (targetRequests) {
            requestString = UrlUtils.fixRelativeUrl(requestString, url.toString());
            targetRequests.add(new Request(requestString));
        }
    }

    public Selectable getUrl() {
        return url;
    }

    public void setUrl(Selectable url) {
        this.url = url;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }
}
