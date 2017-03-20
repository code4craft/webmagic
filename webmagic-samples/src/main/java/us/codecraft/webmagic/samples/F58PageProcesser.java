package us.codecraft.webmagic.samples;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.RedisScheduler;

import java.util.List;

/**
 * @author code4crafter@gmail.com <br>
 * Date: 13-4-21
 * Time: 下午1:48
 */
public class F58PageProcesser implements PageProcessor {

    @Override
    public void process(Page page) {
        List<String> strings = page.getHtml().links().regex(".*/yewu/.*").all();
        page.addTargetRequests(strings);
        page.putField("title",page.getHtml().regex("<title>(.*)</title>"));
        page.putField("body",page.getHtml().xpath("//dd"));
    }

    @Override
    public Site getSite() {
        return Site.me().setDomain("sh.58.com").setCycleRetryTimes(2);  //To change body of implemented methods use File | Settings | File Templates.
    }

    public static void main(String[] args) {
        Spider.create(new F58PageProcesser()).setScheduler(new RedisScheduler("localhost")).addUrl("http://sh1.51a8.com/").run();
    }
}
