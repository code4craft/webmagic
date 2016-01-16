package us.codecraft.webmagic.samples;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.PriorityScheduler;
import us.codecraft.webmagic.scheduler.FileCacheQueueScheduler;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Jie on 1/15/16.
 */
public class MafengwoPageProcessor implements PageProcessor {
    private Site site = Site.me().setCycleRetryTimes(5).setRetryTimes(5).setSleepTime(5000).setTimeOut(3 * 60 * 1000)
            .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
            .addHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
            .setCharset("UTF-8");

    static private String pTitle = "//h1[@class='headtext']/text()";
    static private String pTitle2 = "//h1/text()";
    static private String pBody = "//div[@class='va_con']/tidyText()";
    static private String pBody2 = "//div[@class='a_con_text']/tidyText()";
    static private String pUsername = "//div[@class='person']/strong/a[@class='per_name']/text()";
    static private String pUsername2 = "//a[@class='name']/text()";
    static private String pUserid = "//div[@class='person']/strong/a";
    static private String pUserid2 = "//div[@class='author_info']/div[@class='avatar_box']/div[@class='out_pic']/a";
    static private String pDatetime = "//div[@class='vc_time']/span[@class='time']/text()";
    static private String pDatetime2 = "//span[@class='date']/text()";
    static private String regUserid = "<a[^<>]*href=[\"'][http://www\\.mafengwo\\.cn]+/u/(\\d+)\\.html[\"']";
    static private List<String> userAgentList= Arrays.asList(
            "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:38.0) Gecko/20100101 Firefox/38.0",
            "Mozilla/5.0 (Windows NT 6.2; Win64; x64; rv:16.0) Gecko/16.0 Firefox/16.0",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_3) AppleWebKit/534.55.3 (KHTML, like Gecko) Version/5.1.3 Safari/534.53.10",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

    @Override
    public void process(Page page) {
        String srcUrl = page.getUrl().toString();
        page.addTargetRequests(page.getHtml().links().regex("(http://www\\.mafengwo\\.cn/i/\\d+\\.html)").all(),srcUrl);
        page.addTargetRequests(page.getHtml().links().regex("(http://www\\.mafengwo\\.cn/yj/.*\\.html)").all(),srcUrl);
        boolean parseFlag = page.getUrl().regex("http://www\\.mafengwo\\.cn/i/\\d+\\.html").match();
        if (parseFlag) {
            String username = page.getHtml().xpath(pUsername).get();
            if (username == null && page.getHtml().xpath(pUsername2).all().size()>0) {
                username = page.getHtml().xpath(pUsername2).all().get(0);
            }
            String userid = page.getHtml().xpath(pUserid).regex(regUserid).get();
            if (userid == null) {
                userid = page.getHtml().xpath(pUserid2).regex(regUserid).get();
            }
            String datetime = page.getHtml().xpath(pDatetime).get();
            if (datetime == null && page.getHtml().xpath(pDatetime2).all().size()>0) {
                datetime = page.getHtml().xpath(pDatetime2).all().get(0);
            }
            String title = page.getHtml().xpath(pTitle).get();
            if (title == null) {
                title = page.getHtml().xpath(pTitle2).get();
            }
            String body = page.getHtml().xpath(pBody).get();
            if (body == null) {
                body = page.getHtml().xpath(pBody2).get();
            }
            page.putField("username", username);
            page.putField("userid", userid);
            page.putField("datetime", datetime);
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
        Spider.create(new MafengwoPageProcessor())
                .addUrl("http://www.mafengwo.cn/yj/10099/2-0-1.html")
                .addPipeline(new FilePipeline("D:\\webmagic\\"))
                .setScheduler(new FileCacheQueueScheduler("D:\\webmagic\\cache\\"))
                .thread(5)
                .run();
    }
}
