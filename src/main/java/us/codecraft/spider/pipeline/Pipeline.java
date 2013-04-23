package us.codecraft.spider.pipeline;

import us.codecraft.spider.Page;
import us.codecraft.spider.Site;

/**
 * User: cairne
 * Date: 13-4-21
 * Time: 下午1:39
 */
public interface Pipeline {

    public void process(Page page,Site site);
}
