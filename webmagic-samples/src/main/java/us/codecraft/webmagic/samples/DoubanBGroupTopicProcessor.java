package us.codecraft.webmagic.samples;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.FileCacheQueueScheduler;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by jie on 16-4-20.
 */
public class DoubanBGroupTopicProcessor implements PageProcessor {
    private Site site = Site.me().setCycleRetryTimes(5).setRetryTimes(5).setSleepTime(5000).setTimeOut(3 * 60 * 1000)
            .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
            .addHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
            .setCharset("UTF-8");

    static private String pTitle = "//div[@id='content']/h1/text()";
    static private String pBody = "//div[@class='topic-content']/p/text()";
    static private String pTime = "//div[@class='topic-doc']/h3/span[@class='color-green']/text()";
    static private String pUserName = "//div[@class='topic-doc']/h3/span[@class='from']/a/text()";
    static private String pUserId = "//div[@class='topic-doc']/h3/span[@class='from']/a";
    static private String regUserid = "<a href=\"https://www\\.douban\\.com/people/([\\w\\d]+)/\"";

    static private List<String> userAgentList= Arrays.asList(
            "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:38.0) Gecko/20100101 Firefox/38.0",
            "Mozilla/5.0 (Windows NT 6.2; Win64; x64; rv:16.0) Gecko/16.0 Firefox/16.0",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_3) AppleWebKit/534.55.3 (KHTML, like Gecko) Version/5.1.3 Safari/534.53.10",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

    @Override
    public void process(Page page) {
        String srcUrl = page.getUrl().toString();
        //https://www.douban.com/group/topic/85595551/
        //https://www.douban.com/group/asshole/discussion?start=0
        page.addTargetRequests(page.getHtml().links().regex("(https://www\\.douban\\.com/group/topic/\\d+/)").all(),srcUrl);
        page.addTargetRequests(page.getHtml().links().regex("(https://www\\.douban\\.com/group/asshole/discussion\\?start=\\d+)").all(),srcUrl);
        boolean parseFlag = page.getUrl().regex("https://www\\.douban\\.com/group/topic/\\d+/").match();
        if (parseFlag) {
            String topicId = page.getUrl().regex("https://www\\.douban\\.com/group/topic/(\\d+)/").get();
            String title = page.getHtml().xpath(pTitle).get();
            String body = page.getHtml().xpath(pBody).get();
            String userName = page.getHtml().xpath(pUserName).get();
            String userId = page.getHtml().xpath(pUserId).regex(regUserid).get();
            String time = page.getHtml().xpath(pTime).get();
            page.putField("topicId", topicId);
            page.putField("userName", userName);
            page.putField("userId", userId);
            page.putField("time", time);
            page.putField("title", title);
            page.putField("body", body);
        } else {
            page.setSkip(true);
        }
    }

    @Override
    public Site getSite() {
        Random rand = new Random();
        this.site.setUserAgent(userAgentList.get(rand.nextInt(userAgentList.size())));
        return site;
    }

    public static void main(String[] args) {
        String startUrl = "https://www.douban.com/group/asshole/discussion?start=0";
        String localPath = "/tmp/webmagic";
        String cachePath = localPath + "/cache";
        Spider.create(new DoubanBGroupTopicProcessor())
                .addUrl(startUrl)
                .addPipeline(new FilePipeline(localPath))
                .setScheduler(new FileCacheQueueScheduler(cachePath))
                .thread(5)
                .run();
    }
}

