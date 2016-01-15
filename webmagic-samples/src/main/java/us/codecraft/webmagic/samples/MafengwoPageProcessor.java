package us.codecraft.webmagic.samples;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.PriorityScheduler;
import us.codecraft.webmagic.selector.Selectable;

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
    static private String pContent = "//div[@class='va_con']";
    static private String pSubtitle = "//div[@class='article_title _j_anchorcnt']//h2//span[@class='_j_anchor']/text()";
    static private String pParagraph = "//p[@class='_j_note_content']/text()";
    static private String pParagraph2 = "//p/text()";

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
            Selectable titleObj = page.getHtml().xpath(pTitle);
            if (titleObj == null) {
                titleObj = page.getHtml().xpath(pTitle2);
            }
            //List<String> subTitles = page.getHtml().xpath(pSubtitle).all();
            List<String> paragraphs = page.getHtml().xpath(pParagraph).all();
            if (paragraphs == null || paragraphs.size() == 0) {
                paragraphs = page.getHtml().xpath(pParagraph2).all();
            }
            String content = "";
            for (String paragraph : paragraphs) {
                content += paragraph;
            }
            page.putField("title", titleObj.toString());
            page.putField("content", content);
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
        Spider.create(new MafengwoPageProcessor()).
                setScheduler(new PriorityScheduler()).
                addUrl("http://www.mafengwo.cn/yj/10099/2-0-1.html").
                addPipeline(new FilePipeline("/tmp/webmagic")).
                thread(5).
                run();
    }
}
