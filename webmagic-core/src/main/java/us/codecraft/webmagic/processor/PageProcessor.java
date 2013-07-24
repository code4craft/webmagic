package us.codecraft.webmagic.processor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;

/**
 * 定制爬虫的核心接口。通过实现PageProcessor可以实现一个定制的爬虫。<br>
 *     extends the class to implements various spiders.<br>
 * @author code4crafter@gmail.com <br>
 * Date: 13-4-21
 * Time: 上午11:42
 */
public interface PageProcessor {

    /**
     * 定义如何处理页面，包括链接提取、内容抽取等。
     * @param page
     */
    public void process(Page page);

    /**
     * 定义任务一些配置信息，例如开始链接、抓取间隔、自定义cookie、自定义UA等。
     * @return site
     */
    public Site getSite();
}
