package us.codecraft.webmagic.samples;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.FileDownloader;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.schedular.FileCacheQueueScheduler;

import java.util.List;

/**
 * Author yihua.huang@dianping.com
 * Date: 13-6-24
 * Time: 下午2:12
 */
public class GlobalProcessor implements PageProcessor {

    private Site site;

    @Override
    public void process(Page page) {
        final List<String> requests = page.getHtml().links().regex(".*book\\.douban\\.com.*").toStrings();
        page.addTargetRequests(requests);

    }

    @Override
    public Site getSite() {
        if (site==null){
            site = Site.me().setDomain("douban.com").addStartUrl("http://book.douban.com/").setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
        }
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new GlobalProcessor()).thread(10)
                .scheduler(new FileCacheQueueScheduler("/data/webmagic/github"))
                .downloader(new FileDownloader("/data/webmagic/douban", new HttpClientDownloader()))
                .pipeline(new FilePipeline("/data/webmagic/douban"))
                .run();
    }
}
