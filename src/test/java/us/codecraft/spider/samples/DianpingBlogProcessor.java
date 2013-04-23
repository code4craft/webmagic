package us.codecraft.spider.samples;

import us.codecraft.spider.Site;
import us.codecraft.spider.Page;
import us.codecraft.spider.processor.PageProcessor;

import java.util.List;

/**
 * User: cairne
 * Date: 13-4-21
 * Time: 下午8:08
 */
public class DianpingBlogProcessor implements PageProcessor {
    @Override
    public void process(Page page) {
        //http://progressdaily.diandian.com/post/2013-01-24/40046867275
        List<String> requests = page.getHtml().rs("<a[^<>]*href=[\"']{1}(/shop/.*?)[\"']{1}").toStrings();
        page.addTargetRequests(requests);
        requests = page.getHtml().rs("<a[^<>]*href=[\"']{1}(/search/category/.*?)[\"']{1}").toStrings();
        page.addTargetRequests(requests);
        if (page.getUrl().toString().contains("shop")){
            page.putField("title", page.getHtml().x("//h1[@class='shop-title']"));
            page.putField("content", page.getHtml().sc());
        }
    }

    @Override
    public Site getSite() {
        return Site.me().setDomain("www.dianping.com").setStartUrl("http://www.dianping.com/").
                setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
    }
}
