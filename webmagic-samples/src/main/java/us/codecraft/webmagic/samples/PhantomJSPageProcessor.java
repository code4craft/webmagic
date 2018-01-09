package us.codecraft.webmagic.samples;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.PhantomJSDownloader;
import us.codecraft.webmagic.pipeline.CollectorPipeline;
import us.codecraft.webmagic.pipeline.ResultItemsCollectorPipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * Created by dolphineor on 2014-11-21.
 * <p>
 * 以淘宝为例, 搜索冬装的相关结果
 */
public class PhantomJSPageProcessor implements PageProcessor {

    private Site site = Site.me()
            .setDomain("s.taobao.com")
            .setCharset("GBK")
            .addHeader("Referer", "http://www.taobao.com/")
            .setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {
        if (page.getRawText() != null)
            page.putField("html", page.getRawText());
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) throws Exception {
        PhantomJSDownloader phantomDownloader = new PhantomJSDownloader().setRetryNum(3);

        CollectorPipeline<ResultItems> collectorPipeline = new ResultItemsCollectorPipeline();

        Spider.create(new PhantomJSPageProcessor())
                .addUrl("http://s.taobao.com/search?q=%B6%AC%D7%B0&sort=sale-desc") //%B6%AC%D7%B0为冬装的GBK编码
                .setDownloader(phantomDownloader)
                .addPipeline(collectorPipeline)
                .thread((Runtime.getRuntime().availableProcessors() - 1) << 1)
                .run();

        List<ResultItems> resultItemsList = collectorPipeline.getCollected();
        System.out.println(resultItemsList.get(0).get("html").toString());
    }

}
