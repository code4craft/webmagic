package us.codecraft.webmagic.samples;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.RedisScheduler;

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
        final List<String> requests = page.getHtml().links().all();
        page.addTargetRequests(requests);

    }

    @Override
    public Site getSite() {
        if (site == null) {
            site = Site.me().setDomain("www.2345.com").setSleepTime(0)
                    .addStartUrl("http://www.2345.com/").addStartUrl("http://hao.360.cn/")
                    .addStartUrl("http://www.baidu.com/s?wd=%E7%BD%91%E7%AB%99%E5%AF%BC%E8%88%AA&rsv_spt=1&issp=1&rsv_bp=0&ie=utf-8&tn=80039098_oem_dg&rsv_n=2&rsv_sug3=6&rsv_sug4=698&rsv_sug=0&rsv_sug1=3")
                    .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
        }
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new GlobalProcessor()).thread(10)
                .scheduler(new RedisScheduler("localhost"))
                .pipeline(new FilePipeline("/data/webmagic/test/"))
                .runAsync();
        Spider.create(new GlobalProcessor()).thread(10)
                .scheduler(new RedisScheduler("localhost"))
                .pipeline(new FilePipeline("/data/webmagic/test/"))
                .run();
    }
}
