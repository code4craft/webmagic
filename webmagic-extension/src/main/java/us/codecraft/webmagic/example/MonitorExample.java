package us.codecraft.webmagic.example;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.monitor.SpiderMonitor;
import us.codecraft.webmagic.processor.example.GithubRepoPageProcessor;
import us.codecraft.webmagic.processor.example.OschinaBlogPageProcessor;

/**
 * @author code4crafer@gmail.com
 */
public class MonitorExample {

    public static void main(String[] args) throws Exception {

        Spider oschinaSpider = Spider.create(new OschinaBlogPageProcessor())
                .addUrl("http://my.oschina.net/flashsword/blog").thread(2);
        Spider githubSpider = Spider.create(new GithubRepoPageProcessor())
                .addUrl("https://github.com/code4craft");

        SpiderMonitor spiderMonitor = new SpiderMonitor();
        spiderMonitor.register(oschinaSpider, githubSpider);
        //If you want to connect it from remote, use spiderMonitor.server().jmxStart();
        //ONLY ONE server can start for a machine.
        //Others will be registered
        spiderMonitor.server().server();
        spiderMonitor.jmxStart();
        oschinaSpider.start();
        githubSpider.start();

    }
}
