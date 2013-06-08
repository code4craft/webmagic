package us.codecraft.webmagic.schedular;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;

/**
 * User: cairne
 * Date: 13-4-21
 * Time: 下午1:12
 */
public interface Schedular {

    public void push(Request request,Site site);

    public Request poll(Site site);

}
