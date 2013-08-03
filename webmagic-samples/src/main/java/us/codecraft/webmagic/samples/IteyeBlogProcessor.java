package us.codecraft.webmagic.samples;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @author code4crafter@gmail.com <br>
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
            setSleepTime(100).setRetryTimes(3);
        }
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new IteyeBlogProcessor()).thread(5).pipeline(new FilePipeline("/data/webmagic/")).run();
    }
}
