package us.codecraft.webmagic.samples;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @author yihua.huang@dianping.com <br>
 * @date: 13-7-26 <br>
 * Time: 上午7:31 <br>
 */
public class IteyeBlogProcessor implements PageProcessor {

    private Site site;

    @Override
    public void process(Page page) {
        page.addTargetRequests(page.getHtml().links().regex(".*yanghaoli\\.iteye\\.com/blog/\\d+").all());
        page.putField("title",page.getHtml().xpath("//title").toString());
        page.putField("content",page.getHtml().smartContent().toString());
    }

    @Override
    public Site getSite() {
        if (site == null) {
            site = Site.me().setDomain("yanghaoli.iteye.com").addStartUrl("http://yanghaoli.iteye.com/").
                    setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31")
            .setSleepTime(100).setRetryTimes(3);
        }
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new IteyeBlogProcessor()).thread(5).pipeline(new FilePipeline()).run();
    }
}
