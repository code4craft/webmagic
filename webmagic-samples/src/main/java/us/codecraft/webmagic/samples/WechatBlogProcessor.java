package us.codecraft.webmagic.samples;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.FileCacheQueueScheduler;
import us.codecraft.webmagic.selector.Selectable;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Jie on 7/17/16.
 */
public class WechatBlogProcessor implements PageProcessor {
    private Site site = Site.me().setCycleRetryTimes(5).setRetryTimes(5).setSleepTime(5000).setTimeOut(3 * 60 * 1000)
            .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
            .addHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
            .addHeader("Referer", "http://weixin.sogou.com/")
            .setCharset("UTF-8")
            .setDomain("weixin.sogou.com")
            //.setDomain("mp.weixin.qq.com")
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.155 Safari/537.36");
    private static List<String> userAgentList= Arrays.asList(
            "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:38.0) Gecko/20100101 Firefox/38.0",
            "Mozilla/5.0 (Windows NT 6.2; Win64; x64; rv:16.0) Gecko/16.0 Firefox/16.0",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_3) AppleWebKit/534.55.3 (KHTML, like Gecko) Version/5.1.3 Safari/534.53.10",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

    private static String pTitle = "//*[@id='activity-name']";
    private static String pAuthor = "//a[@id='post-user']";
    private static String pContent = "//*[@id='js_content']/text()";
    private String currentUrl;
    @Override
    public void process(Page page) {
        this.currentUrl = page.getUrl().toString();
        List<Selectable> links = page.getHtml().links().nodes();
        page.addTargetRequests(page.getHtml().links().regex("(http://mp\\.weixin\\.qq\\.com/profile\\?src=\\d+&timestamp=\\d+&ver=\\d+&signature=.+)").all());
        page.addTargetRequests(page.getHtml().links().regex("http://mp\\.weixin\\.qq\\.com/s\\?timestamp=\\d+&src=\\d+&ver=\\d+&signature=.+").all());
        page.addTargetRequests(page.getHtml().links().regex("http://mp\\.weixin\\.qq\\.com/s\\?src=\\d+&timestamp=\\d+&ver=\\d+&signature=.+").all());
        page.addTargetRequests(page.getHtml().links().regex("http://mp\\.weixin\\.qq\\.com/s\\?__biz=\\S+&mid=\\S+&idx=\\S+&sn=\\S*").all());
        boolean parseFlag = page.getUrl().regex("http://mp\\.weixin\\.qq\\.com/s\\?timestamp=\\d+&src=\\d+&ver=\\d+&signature=.+").match();
//        if (parseFlag) {
//            String title = page.getHtml().xpath(pTitle).get();
//            String author = page.getHtml().xpath(pAuthor).get();
//            String content = page.getHtml().xpath(pContent).get();
//
//            page.putField("title", title);
//            page.putField("author", author);
//            page.putField("content", content);
//        } else {
//            page.setSkip(true);
//        }
        page.putField("raw", page.getRawText());

    }

    @Override
    public Site getSite() {
        site.addHeader("Referer", currentUrl);
        return this.site;
    }

    public static void main(String[] args) {
        String query = "股票";
        if (args.length>0) {
            query = args[0];
        }
        String startUrl = "http://weixin.sogou.com/weixin?type=1&query="+query;
        String localPath = "/tmp/wechat";
        String cachePath = localPath + "/cache";
        Spider.create(new WechatBlogProcessor())
                .addUrl(startUrl)
                .addPipeline(new FilePipeline(localPath))
                .setScheduler(new FileCacheQueueScheduler(cachePath))
                .thread(1)
                .run();
    }
}
