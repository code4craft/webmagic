package us.codecraft.webmagic.pipeline;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;

/**
 * User: cairne
 * Date: 13-4-21
 * Time: 下午1:39
 */
public interface Pipeline {

    public void process(Page page,Site site);
}
