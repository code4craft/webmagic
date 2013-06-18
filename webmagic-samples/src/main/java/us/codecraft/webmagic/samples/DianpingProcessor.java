package us.codecraft.webmagic.samples;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * Author: code4crafter@gmail.com
 * Date: 13-4-21
 * Time: 下午8:08
 */
public class DianpingProcessor implements PageProcessor {
    @Override
    public void process(Page page) {
        List<String> requests = page.getHtml().as().rs(".*shop.*").toStrings();
        page.addTargetRequests(requests);
        requests = page.getHtml().rs(".*search/category/.*").toStrings();
        page.addTargetRequests(requests);
        if (page.getUrl().toString().contains("shop")) {
            page.putField("title", page.getHtml().x("//h1[@class='shop-title']"));
            page.putField("content", page.getHtml().sc());
        }
    }

    @Override
    public Site getSite() {
        return Site.me().setDomain("www.dianping.com").addStartUrl("http://www.dianping.com/").
                setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
    }

    public static void main(String[] args) {
        DianpingProcessor dianpingProcessor = new DianpingProcessor();
        Spider.me().processor(dianpingProcessor).startUrl("http://www.dianping.com/shanghai/food").run();
    }
}
