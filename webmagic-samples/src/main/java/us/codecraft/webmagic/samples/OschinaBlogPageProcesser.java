package us.codecraft.webmagic.samples;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * @author code4crafter@gmail.com <br>
 * Date: 13-4-21
 * Time: 下午1:48
 */
public class OschinaBlogPageProcesser implements PageProcessor {

    @Override
    public void process(Page page) {
        List<String> strings = page.getHtml().as().r("(http://my\\.oschina\\.net)").toStrings();
        page.addTargetRequests(strings);
        page.putField("title", page.getHtml().xs("//div[@class='BlogEntity']/div[@class='BlogTitle']/h1"));
        page.putField("content", page.getHtml().sc());
        page.putField("author", page.getUrl().r("my\\.oschina\\.net/(\\w+)/blog/\\d+"));
    }

    @Override
    public Site getSite() {
        return Site.me().setDomain("my.oschina.net").addStartUrl("http://www.oschina.net/").
                setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
    }
}
