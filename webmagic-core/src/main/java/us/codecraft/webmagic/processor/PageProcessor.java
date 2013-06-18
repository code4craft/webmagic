package us.codecraft.webmagic.processor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;

/**
 * @author code4crafter@gmail.com <br>
 * Date: 13-4-21
 * Time: 上午11:42
 */
public interface PageProcessor {

    /**
     * extends the class to implements variaty spiders
     * @param page
     */
    public void process(Page page);

    /**
     * the site the processor for
     * @return site
     */
    public Site getSite();
}
