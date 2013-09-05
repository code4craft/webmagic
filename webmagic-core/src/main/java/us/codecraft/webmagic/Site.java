package us.codecraft.webmagic;

import us.codecraft.webmagic.utils.UrlUtils;

import java.util.*;

/**
 * Object contains setting for crawler.<br>
 *
 * @author code4crafter@gmail.com <br>
 * @since 0.1.0
 * @see us.codecraft.webmagic.processor.PageProcessor
 */
public class Site {

    private String domain;

    private String userAgent;

    private Map<String, String> cookies = new LinkedHashMap<String, String>();

    private String charset;

    /**
     * startUrls is the urls the crawler to start with.
     */
    private List<String> startUrls = new ArrayList<String>();

    private int sleepTime = 3000;

    private int retryTimes = 0;

    private int cycleRetryTimes = 0;

    private static final Set<Integer> DEFAULT_STATUS_CODE_SET = new HashSet<Integer>();

    private Set<Integer> acceptStatCode = DEFAULT_STATUS_CODE_SET;

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
        if (domain == null) {
            if (startUrls.size() > 0) {
                domain = UrlUtils.getDomain(startUrls.get(0));
            }
        }
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
     */
    public List<String> getStartUrls() {
        return startUrls;
    }

    /**
     * Add a url to start url.<br>
     *
     * @param startUrl
     * @return this
     */
    public Site addStartUrl(String startUrl) {
        this.startUrls.add(startUrl);
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
     * Get retry times when download fail immediately, 0 by default.<br>
     *
     * @return retry times when download fail
     */
    public int getRetryTimes() {
        return retryTimes;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Site site = (Site) o;

        if (acceptStatCode != null ? !acceptStatCode.equals(site.acceptStatCode) : site.acceptStatCode != null)
            return false;
        if (!domain.equals(site.domain)) return false;
        if (!startUrls.equals(site.startUrls)) return false;
        if (charset != null ? !charset.equals(site.charset) : site.charset != null) return false;
        if (userAgent != null ? !userAgent.equals(site.userAgent) : site.userAgent != null) return false;

        return true;
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
    public int hashCode() {
        int result = domain.hashCode();
        result = 31 * result + (startUrls != null ? startUrls.hashCode() : 0);
        result = 31 * result + (userAgent != null ? userAgent.hashCode() : 0);
        result = 31 * result + (charset != null ? charset.hashCode() : 0);
        result = 31 * result + (acceptStatCode != null ? acceptStatCode.hashCode() : 0);
        return result;
    }
}
