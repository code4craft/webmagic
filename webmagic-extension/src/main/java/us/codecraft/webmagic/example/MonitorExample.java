package us.codecraft.webmagic.example;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.monitor.SpiderMonitor;
import us.codecraft.webmagic.processor.example.GithubRepoPageProcessor;
import us.codecraft.webmagic.processor.example.ZhihuPageProcessor;

/**
 * @author code4crafer@gmail.com
 * @since 0.5.0
 */
public class MonitorExample {

    public static void main(String[] args) throws Exception {

        Spider zhihuSpider = Spider.create(new ZhihuPageProcessor())
                .addUrl("http://my.oschina.net/flashsword/blog");
        Spider githubSpider = Spider.create(new GithubRepoPageProcessor())
                .addUrl("https://github.com/code4craft");

        SpiderMonitor.instance().register(zhihuSpider);
        SpiderMonitor.instance().register(githubSpider);
        zhihuSpider.start();
        githubSpider.start();
    }
}
