package us.codecraft.webmagic.samples;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Author: code4crafter@gmail.com
 * Date: 13-4-21
 * Time: 下午1:48
 */
public class SinaBlogProcesser implements PageProcessor {

    private Site site;

    @Override
    public void process(Page page) {
        page.addTargetRequests(page.getHtml().as().rs("(http://blog\\.sina\\.com\\.cn/s/blog_.*)").toStrings());
        page.putField("title", page.getHtml().x("//div[@class='articalTitle']/h2"));
        page.putField("content",page.getHtml().x("//div[@id='articlebody']//div[@class='articalContent']"));
        page.putField("id",page.getUrl().r("http://blog\\.sina\\.com\\.cn/s/blog_(\\w+)"));
        page.putField("date",page.getHtml().x("//div[@id='articlebody']//span[@class='time SG_txtc']").r("\\((.*)\\)"));
//        page.putField("tags",page.getHtml().xs("//td[@class='blog_tag']/h3/a"));
    }

    @Override
    public Site getSite() {
        if (site==null){
            site = Site.me().setDomain("blog.sina.com.cn").addStartUrl("http://blog.sina.com.cn/flashsword20").setSleepTime(3000).
                    setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
        }
        return site;
    }
}
