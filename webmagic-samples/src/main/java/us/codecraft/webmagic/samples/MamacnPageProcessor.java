package us.codecraft.webmagic.samples;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.samples.pipeline.OneFilePipeline;
import us.codecraft.webmagic.scheduler.FileCacheQueueScheduler;
import us.codecraft.webmagic.selector.Selectable;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

/**
 * @author code4crafer@gmail.com
 */
public class MamacnPageProcessor implements PageProcessor {

    private Site site = Site.me().setDomain("www.mama.cn").setSleepTime(100);

    @Override
    public void process(Page page) {
        Selectable images = page.getHtml().xpath("//ul[@id=ma-thumb-list]/li");
        page.putField("img", images.xpath("//div[@class=picList]/div[@class=pre]/div[@class=npic]//img/@src").get());
        page.putField("title", page.getHtml().xpath("//div[@class=picList]/div[@class=pre]/div[@class=npic]//img/@alt").get());
        page.putField("url", page.getUrl().toString());
        if (page.getResultItems().get("title") == null) {
            page.setSkip(true);
        }
        page.addTargetRequests(page.getHtml().links().regex("http://www\\.mama\\.cn/photo/.*\\.html").all());
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        Spider.create(new MamacnPageProcessor())
                .setScheduler(new FileCacheQueueScheduler("/data/webmagic/mamacn"))
                .addUrl("http://www.mama.cn/photo/t1-p1.html")
                .addPipeline(new OneFilePipeline("/data/webmagic/mamacn/data"))
                .thread(5)
                .run();
    }
}
