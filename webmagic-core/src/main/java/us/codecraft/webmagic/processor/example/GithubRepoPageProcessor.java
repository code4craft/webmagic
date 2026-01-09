package us.codecraft.webmagic.processor.example;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * 这部分我们直接通过GithubRepoPageProcessor这个例子来介绍PageProcessor的编写方式。
 * 我将PageProcessor的定制分为三个部分，分别是爬虫的配置、页面元素的抽取和链接的发现。
 *
 * @author code4crafter@gmail.com <br>
 * @since 0.3.2
 */
public class GithubRepoPageProcessor implements PageProcessor {
    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);

    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    @Override
    public void process(Page page) {
        // 部分二：定义如何抽取页面信息，并保存下来
        page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
        page.putField("name", page.getHtml().xpath("//h1[@class='public']/strong/a/text()").toString());
        if (page.getResultItems().get("name")==null){
            //skip this page
            page.setSkip(true);
        }
        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));
        // 部分三：从页面发现后续的url地址来抓取
        /**
         * page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all()用于获取所有满足"(https:/ /github\.com/\w+/\w+)"
         * 这个正则表达式的链接，page.addTargetRequests()则将这些链接加入到待抓取的队列中去。
         */
        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/[\\w\\-]+/[\\w\\-]+)").all());
        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/[\\w\\-])").all());
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        //https://blog.csdn.net/bbc2005/article/details/80890829
        //https://www.cnblogs.com/sunny08/p/8038440.html
       // System.setProperty("javax.net.debug", "all"); //打印网络连接握手信息
        Spider.create(new GithubRepoPageProcessor()).addUrl("https://github.com/code4craft").thread(1).run();
    }
}
