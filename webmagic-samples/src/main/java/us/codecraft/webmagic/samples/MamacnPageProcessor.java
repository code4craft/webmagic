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
import java.util.List;

/**
 * @author code4crafer@gmail.com
 */
public class MamacnPageProcessor implements PageProcessor {

    private Site site = Site.me().setDomain("www.mama.cn").setSleepTime(100);

    @Override
    public void process(Page page) {
        List<Selectable> nodes = page.getHtml().xpath("//ul[@id=ma-thumb-list]/li").nodes();
        StringBuilder accum = new StringBuilder();
        for (Selectable node : nodes) {
            accum.append("img:").append(node.xpath("//a/@href").get()).append("\n");
            accum.append("title:").append(node.xpath("//img/@alt").get()).append("\n");
        }
        page.putField("",accum.toString());
        if (accum.length() == 0) {
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
