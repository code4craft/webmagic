package us.codecraft.webmagic;

import us.codecraft.webmagic.utils.Experimental;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Object contains url to crawl.<br>
 * It contains some additional information.<br>
 *
 * @author code4crafter@gmail.com <br>
 * @since 0.1.0
 */
public class Request implements Serializable {

    private static final long serialVersionUID = 2062192774891352043L;

    public static final String CYCLE_TRIED_TIMES = "_cycle_tried_times";
    public static final String STATUS_CODE = "statusCode";
    public static final String PROXY = "proxy";

    private String url;

    private String method;

    /**
     * Store additional information in extras.
     */
    private Map<String, Object> extras;
    /**
     * POST/GET param set
     * */
    private Map<String,String> params=new HashMap<String, String>();

    /**
     * Priority of the request.<br>
     * The bigger will be processed earlier. <br>
     * @see us.codecraft.webmagic.scheduler.PriorityScheduler
     */
    private long priority;

    public Request() {
    }

    public Request(String url) {
        this.url = url;
    }

    public long getPriority() {
        return priority;
    }

    /**
     * Set the priority of request for sorting.<br>
     * Need a scheduler supporting priority.<br>
     * @see us.codecraft.webmagic.scheduler.PriorityScheduler
     *
     * @param priority priority
     * @return this
     */
    @Experimental
    public Request setPriority(long priority) {
        this.priority = priority;
        return this;
    }

    public Object getExtra(String key) {
        if (extras == null) {
            return null;
        }
        return extras.get(key);
    }

    public Request putExtra(String key, Object value) {
        if (extras == null) {
            extras = new HashMap<String, Object>();
        }
        extras.put(key, value);
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Map<String, Object> getExtras() {
        return extras;
    }

    public void setExtras(Map<String, Object> extras) {
        this.extras = extras;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * The http method of the request. Get for default.
     * @return httpMethod
     * @see us.codecraft.webmagic.utils.HttpConstant.Method
     * @since 0.5.0
     */
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Map<String, String> getParams() {
        return params;
    }
    /**
     * set params for request
     * <br>
     * DO NOT set this for request already has params, like 'https://github.com/search?q=webmagic'
     * @param params params
     * */
    public void setParams(Map<String, String> params) {
        this.params = params;
    }
    /**
     * set params for request
     * <br>
     * DO NOT set this for request already has params, like 'https://github.com/search?q=webmagic'
     * @param key key
     * @param value value
     * */
    public void putParams(String key,String value) {
        params.put(key,value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Request request = (Request) o;

        if (url != null ? !url.equals(request.url) : request.url != null) return false;
        if (method != null ? !method.equals(request.method) : request.method != null) return false;
        return params != null ? params.equals(request.params) : request.params == null;
    }

    @Override
    public int hashCode() {
        int result = url != null ? url.hashCode() : 0;
        result = 31 * result + (method != null ? method.hashCode() : 0);
        result = 31 * result + (params != null ? params.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Request{" +
                "url='" + url + '\'' +
                ", method='" + method + '\'' +
                ", extras=" + extras +
                ", params=" + params +
                ", priority=" + priority +
                '}';
    }
}
