package us.codecraft.spider;

import us.codecraft.spider.Site;

import java.util.List;

/**
 * User: cairne
 * Date: 13-4-21
 * Time: 上午11:37
 */
public class Request {

    private String url;

    private Object[] extra;

    public Request(String url, Object... extra) {
        this.url = url;
        this.extra = extra;
    }

    public Object[] getExtra() {
        return extra;
    }

    public String getUrl() {
        return url;
    }

}
