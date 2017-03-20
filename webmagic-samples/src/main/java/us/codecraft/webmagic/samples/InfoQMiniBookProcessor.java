package us.codecraft.webmagic.samples;

import org.apache.commons.collections.CollectionUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * @author code4crafter@gmail.com <br>
 */
public class InfoQMiniBookProcessor implements PageProcessor {

    private Site site;

    @Override
    public void process(Page page) {
        page.addTargetRequests(page.getHtml().links().regex("http://www\\.infoq\\.com/cn/minibooks/.*").all());
        List<String> all = page.getHtml().links().regex(".*\\.pdf").all();
        if (CollectionUtils.isNotEmpty(all)) {
            page.putField("pdf", all);
        } else {
            page.getResultItems().setSkip(true);
        }
    }

    @Override
    public Site getSite() {
        if (site == null) {
            site = Site.me().setDomain("www.infoq.com").addCookie("RegisteredUserCookie", "sDDDc8dIAgZSq67uJSXhtpQaHEi1XDOH").
                    setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
        }
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new InfoQMiniBookProcessor())
                .thread(5)
                .addUrl("http://www.infoq.com/cn/minibooks")
                .run();
    }
}
