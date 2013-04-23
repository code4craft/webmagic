package us.codecraft.spider.schedular;

import us.codecraft.spider.Request;
import us.codecraft.spider.Site;

/**
 * User: cairne
 * Date: 13-4-21
 * Time: 下午1:12
 */
public interface Schedular {

    public void push(Request request,Site site);

    public Request poll(Site site);

}
