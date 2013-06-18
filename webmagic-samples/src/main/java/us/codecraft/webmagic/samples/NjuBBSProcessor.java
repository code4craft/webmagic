package us.codecraft.webmagic.samples;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * @author code4crafter@gmail.com <br>
 * Date: 13-4-21
 * Time: 下午8:08
 */
public class NjuBBSProcessor implements PageProcessor {
    @Override
    public void process(Page page) {
        List<String> requests = page.getHtml().rs("<a[^<>]*href=(bbstcon\\?board=Pictures&file=[^>]*)").toStrings();
        page.addTargetRequests(requests);
        page.putField("title",page.getHtml().x("//div[@id='content']//h2/a"));
        page.putField("content",page.getHtml().sc());
    }

    @Override
    public Site getSite() {
        return Site.me().setDomain("bbs.nju.edu.cn").addStartUrl("http://bbs.nju.edu.cn/board?board=Pictures").
                setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
    }
}
