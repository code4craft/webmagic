package us.codecraft.webmagic;

import java.util.HashSet;
import java.util.Set;

/**
 * User: cairne
 * Date: 13-4-21
 * Time: 下午12:13
 */
public class Site {

    private String domain;

    private String userAgent;

    private String cookie;

    private String encoding;

    private String startUrl;

    private int sleepTime = 3000;

    private static final Set<Integer> DEFAULT_STATUS_CODE_SET = new HashSet<Integer>();

    private Set<Integer> acceptStatCode = DEFAULT_STATUS_CODE_SET;

    static {
        DEFAULT_STATUS_CODE_SET.add(200);
    }

    public static Site me() {
        return new Site();
    }

    public Site setCookie(String cookie) {
        this.cookie = cookie;
        return this;
    }

    public Site setUserAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    public String getCookie() {
        return cookie;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public String getDomain() {
        return domain;
    }

    public Site setDomain(String domain) {
        this.domain = domain;
        return this;
    }

    public String getEncoding() {
        return encoding;
    }

    public Site setEncoding(String encoding) {
        this.encoding = encoding;
        return this;
    }

    public Set<Integer> getAcceptStatCode() {
        return acceptStatCode;
    }

    public Site setAcceptStatCode(Set<Integer> acceptStatCode) {
        this.acceptStatCode = acceptStatCode;
        return this;
    }

    public String getStartUrl() {
        return startUrl;
    }

    public Site setStartUrl(String startUrl) {
        this.startUrl = startUrl;
        return this;
    }

    public int getSleepTime() {
        return sleepTime;
    }

    public Site setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Site site = (Site) o;

        if (acceptStatCode != null ? !acceptStatCode.equals(site.acceptStatCode) : site.acceptStatCode != null)
            return false;
        if (cookie != null ? !cookie.equals(site.cookie) : site.cookie != null) return false;
        if (!domain.equals(site.domain)) return false;
        if (encoding != null ? !encoding.equals(site.encoding) : site.encoding != null) return false;
        if (userAgent != null ? !userAgent.equals(site.userAgent) : site.userAgent != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = domain.hashCode();
        result = 31 * result + (userAgent != null ? userAgent.hashCode() : 0);
        result = 31 * result + (cookie != null ? cookie.hashCode() : 0);
        result = 31 * result + (encoding != null ? encoding.hashCode() : 0);
        result = 31 * result + (acceptStatCode != null ? acceptStatCode.hashCode() : 0);
        return result;
    }
}
