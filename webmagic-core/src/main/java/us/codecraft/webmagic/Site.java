package us.codecraft.webmagic;

import org.apache.http.HttpHost;
import us.codecraft.webmagic.utils.UrlUtils;

import java.util.*;

/**
 * Object contains setting for crawler.<br>
 *
 * @author code4crafter@gmail.com <br>
 * @see us.codecraft.webmagic.processor.PageProcessor
 * @since 0.1.0
 */
public class Site {

    private String domain;

    private String userAgent;

    private Map<String, String> cookies = new LinkedHashMap<String, String>();

    private String charset;

    /**
     * startUrls is the urls the crawler to start with.
     */
    private List<Request> startRequests = new ArrayList<Request>();

    private int sleepTime = 5000;

    private int retryTimes = 0;

    private int cycleRetryTimes = 0;

    private int timeOut = 5000;

    private static final Set<Integer> DEFAULT_STATUS_CODE_SET = new HashSet<Integer>();

    private Set<Integer> acceptStatCode = DEFAULT_STATUS_CODE_SET;

    private Map<String, String> headers = new HashMap<String, String>();

    private HttpHost httpProxy;

    private boolean useGzip = true;

    public static interface HeaderConst {

        public static final String REFERER = "Referer";
    }


    static {
        DEFAULT_STATUS_CODE_SET.add(200);
    }

    /**
     * new a Site
     *
     * @return new site
     */
    public static Site me() {
        return new Site();
    }

    /**
     * Add a cookie with domain {@link #getDomain()}
     *
     * @param name
     * @param value
     * @return this
     */
    public Site addCookie(String name, String value) {
        cookies.put(name, value);
        return this;
    }

    /**
     * set user agent
     *
     * @param userAgent userAgent
     * @return this
     */
    public Site setUserAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    /**
     * get cookies
     *
     * @return get cookies
     */
    public Map<String, String> getCookies() {
        return cookies;
    }

    /**
     * get user agent
     *
     * @return user agent
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * get domain
     *
     * @return get domain
     */
    public String getDomain() {
        return domain;
    }

    /**
     * set the domain of site.
     *
     * @param domain
     * @return this
     */
    public Site setDomain(String domain) {
        this.domain = domain;
        return this;
    }

    /**
     * Set charset of page manually.<br>
     * When charset is not set or set to null, it can be auto detected by Http header.
     *
     * @param charset
     * @return this
     */
    public Site setCharset(String charset) {
        this.charset = charset;
        return this;
    }

    /**
     * get charset set manually
     *
     * @return charset
     */
    public String getCharset() {
        return charset;
    }

    public int getTimeOut() {
        return timeOut;
    }

    /**
     * set timeout for downloader in ms
     *
     * @param timeOut
     */
    public Site setTimeOut(int timeOut) {
        this.timeOut = timeOut;
        return this;
    }

    /**
     * Set acceptStatCode.<br>
     * When status code of http response is in acceptStatCodes, it will be processed.<br>
     * {200} by default.<br>
     * It is not necessarily to be set.<br>
     *
     * @param acceptStatCode
     * @return this
     */
    public Site setAcceptStatCode(Set<Integer> acceptStatCode) {
        this.acceptStatCode = acceptStatCode;
        return this;
    }

    /**
     * get acceptStatCode
     *
     * @return acceptStatCode
     */
    public Set<Integer> getAcceptStatCode() {
        return acceptStatCode;
    }

    /**
     * get start urls
     *
     * @return start urls
     * @see #getStartRequests
     * @deprecated
     */
    @Deprecated
    public List<String> getStartUrls() {
        return UrlUtils.convertToUrls(startRequests);
    }

    public List<Request> getStartRequests() {
        return startRequests;
    }

    /**
     * Add a url to start url.<br>
     * Because urls are more a Spider's property than Site, move it to {@link Spider#addUrl(String...)}}
     *
     * @deprecated
     * @see Spider#addUrl(String...)
     * @param startUrl
     * @return this
     */
    public Site addStartUrl(String startUrl) {
        return addStartRequest(new Request(startUrl));
    }

    /**
     * Add a url to start url.<br>
     * Because urls are more a Spider's property than Site, move it to {@link Spider#addRequest(Request...)}}
     *
     * @deprecated
     * @see Spider#addRequest(Request...)
     * @param startUrl
     * @return this
     */
    public Site addStartRequest(Request startRequest) {
        this.startRequests.add(startRequest);
        if (domain == null && startRequest.getUrl() != null) {
            domain = UrlUtils.getDomain(startRequest.getUrl());
        }
        return this;
    }

    /**
     * Set the interval between the processing of two pages.<br>
     * Time unit is micro seconds.<br>
     *
     * @param sleepTime
     * @return this
     */
    public Site setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
        return this;
    }

    /**
     * Get the interval between the processing of two pages.<br>
     * Time unit is micro seconds.<br>
     *
     * @return the interval between the processing of two pages,
     */
    public int getSleepTime() {
        return sleepTime;
    }

    /**
     * Get retry times immediately when download fail, 0 by default.<br>
     *
     * @return retry times when download fail
     */
    public int getRetryTimes() {
        return retryTimes;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    /**
     * Put an Http header for downloader. <br/>
     * Use {@link #addCookie(String, String)} for cookie and {@link #setUserAgent(String)} for user-agent. <br/>
     *
     * @param key   key of http header, there are some keys constant in {@link HeaderConst}
     * @param value value of header
     * @return
     */
    public Site addHeader(String key, String value) {
        headers.put(key, value);
        return this;
    }

    /**
     * Set retry times when download fail, 0 by default.<br>
     *
     * @return this
     */
    public Site setRetryTimes(int retryTimes) {
        this.retryTimes = retryTimes;
        return this;
    }

    /**
     * When cycleRetryTimes is more than 0, it will add back to scheduler and try download again. <br>
     *
     * @return retry times when download fail
     */
    public int getCycleRetryTimes() {
        return cycleRetryTimes;
    }

    /**
     * Set cycleRetryTimes times when download fail, 0 by default. Only work in RedisScheduler. <br>
     *
     * @return this
     */
    public Site setCycleRetryTimes(int cycleRetryTimes) {
        this.cycleRetryTimes = cycleRetryTimes;
        return this;
    }

    public HttpHost getHttpProxy() {
        return httpProxy;
    }

    /**
     * set up httpProxy for this site
     * @param httpProxy
     * @return
     */
    public Site setHttpProxy(HttpHost httpProxy) {
        this.httpProxy = httpProxy;
        return this;
    }

    public boolean isUseGzip() {
        return useGzip;
    }

    /**
     * Whether use gzip. <br>
     * Default is true, you can set it to false to disable gzip.
     *
     * @param useGzip
     * @return
     */
    public Site setUseGzip(boolean useGzip) {
        this.useGzip = useGzip;
        return this;
    }

    public Task toTask() {
        return new Task() {
            @Override
            public String getUUID() {
                return Site.this.getDomain();
            }

            @Override
            public Site getSite() {
                return Site.this;
            }
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Site site = (Site) o;

        if (cycleRetryTimes != site.cycleRetryTimes) return false;
        if (retryTimes != site.retryTimes) return false;
        if (sleepTime != site.sleepTime) return false;
        if (timeOut != site.timeOut) return false;
        if (acceptStatCode != null ? !acceptStatCode.equals(site.acceptStatCode) : site.acceptStatCode != null)
            return false;
        if (charset != null ? !charset.equals(site.charset) : site.charset != null) return false;
        if (cookies != null ? !cookies.equals(site.cookies) : site.cookies != null) return false;
        if (domain != null ? !domain.equals(site.domain) : site.domain != null) return false;
        if (headers != null ? !headers.equals(site.headers) : site.headers != null) return false;
        if (startRequests != null ? !startRequests.equals(site.startRequests) : site.startRequests != null)
            return false;
        if (userAgent != null ? !userAgent.equals(site.userAgent) : site.userAgent != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = domain != null ? domain.hashCode() : 0;
        result = 31 * result + (userAgent != null ? userAgent.hashCode() : 0);
        result = 31 * result + (cookies != null ? cookies.hashCode() : 0);
        result = 31 * result + (charset != null ? charset.hashCode() : 0);
        result = 31 * result + (startRequests != null ? startRequests.hashCode() : 0);
        result = 31 * result + sleepTime;
        result = 31 * result + retryTimes;
        result = 31 * result + cycleRetryTimes;
        result = 31 * result + timeOut;
        result = 31 * result + (acceptStatCode != null ? acceptStatCode.hashCode() : 0);
        result = 31 * result + (headers != null ? headers.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Site{" +
                "domain='" + domain + '\'' +
                ", userAgent='" + userAgent + '\'' +
                ", cookies=" + cookies +
                ", charset='" + charset + '\'' +
                ", startRequests=" + startRequests +
                ", sleepTime=" + sleepTime +
                ", retryTimes=" + retryTimes +
                ", cycleRetryTimes=" + cycleRetryTimes +
                ", timeOut=" + timeOut +
                ", acceptStatCode=" + acceptStatCode +
                ", headers=" + headers +
                '}';
    }
}
