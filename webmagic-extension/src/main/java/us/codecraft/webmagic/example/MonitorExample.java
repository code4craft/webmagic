package us.codecraft.webmagic.example;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.monitor.SpiderMonitor;
import us.codecraft.webmagic.processor.example.GithubRepoPageProcessor;
import us.codecraft.webmagic.processor.example.OschinaBlogPageProcessor;

/**
 * @author code4crafer@gmail.com
 * @since 0.5.0
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
        //Others will be registered without start a server.
        //You can also register a server by spiderMonitor.client(host,port).jmxStart().
        spiderMonitor.jmxStart();
        oschinaSpider.start();
        githubSpider.start();

    }
}
