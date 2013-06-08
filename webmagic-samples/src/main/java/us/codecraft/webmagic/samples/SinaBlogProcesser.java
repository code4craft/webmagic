package us.codecraft.webmagic.samples;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * User: cairne
 * Date: 13-4-21
 * Time: 下午1:48
 */
public class SinaBlogProcesser implements PageProcessor {

    @Override
    public void process(Page page) {
        page.addTargetRequests(page.getHtml().rs("<a[^<>]*href=[\"']{1}(http://blog\\.sina\\.com\\.cn/s/blog_.*?)[\"']{1}").toStrings());
        page.putField("title", page.getHtml().x("//div[@class='articalTitle']/h2"));
        page.putField("body",page.getHtml().sc());
        //x("//dd[@class='w133']")
        page.putField("date",page.getHtml().x("//div[@id='articlebody']//span[@class='time SG_txtc']").r("\\((.*)\\)"));
        page.putField("tags",page.getHtml().xs("//td[@class='blog_tag']/h3/a"));
    }

    @Override
    public Site getSite() {
        return Site.me().setDomain("blog.sina.com.cn").setStartUrl("http://blog.sina.com.cn/").
                setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
    }
}
