package us.codecraft.webmagic.samples;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * @author code4crafter@gmail.com <br>
 *         Date: 13-4-21
 *         Time: 下午8:08
 */
public class DianpingProcessor implements PageProcessor {

    private Site site;

    @Override
    public void process(Page page) {
        List<String> requests = page.getHtml().links().regex("http://info-search-web121361\\.alpha\\.dp:8080/search/.*").all();
        page.addTargetRequests(requests);
    }

    @Override
    public Site getSite() {
        if (site == null) {
            site = Site.me().setDomain("info-search-web361.alpha.dp:8080").addStartUrl("http://info11-search-web361.alpha.dp:8080/search/category/1/0").
                    setSleepTime(100).
                    setUserAgent("I'm a performance tester created by yihua.huang");
        }
        return site;
    }

    public static void main(String[] args) {
        int sleepTime = 0;
        if (args.length > 0) {
            sleepTime = Integer.parseInt(args[0]);
        }
        DianpingProcessor dianpingProcessor = new DianpingProcessor();
        dianpingProcessor.getSite().setSleepTime(sleepTime).setRetryTimes(10);
        Spider.create(dianpingProcessor).run();
    }
}
