package us.codecraft.webmagic.samples;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * Author: code4crafter@gmail.com
 * Date: 13-4-21
 * Time: 下午1:48
 */
public class OschinaPageProcesser implements PageProcessor {

    @Override
    public void process(Page page) {
        List<String> strings = page.getHtml().rs("<a[^<>]*href=[\"']{1}(http://www\\.oschina\\.net/question/[\\w]+)[\"']{1}").toStrings();
        page.addTargetRequests(strings);
        page.putField("title", page.getHtml().x("//div[@class='QTitle']/h1/a"));
        page.putField("content", page.getHtml().xs("//div[@class='Question']//div[@class='Content']/div[@class='detail']"));
    }

    @Override
    public Site getSite() {
        return Site.me().setDomain("www.oschina.net").addStartUrl("http://www.oschina.net/").
                setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
    }
}
