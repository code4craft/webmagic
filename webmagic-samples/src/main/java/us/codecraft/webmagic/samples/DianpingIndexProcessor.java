package us.codecraft.webmagic.samples;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author code4crafter@gmail.com <br>
 *         Date: 13-4-21 Time: 下午8:08
 */
public class DianpingIndexProcessor implements PageProcessor {
    @Override
    public void process(Page page) {
        if (page.getUrl().toString().equals("http://www.dianping.com/citylist")) {
            page.addTargetRequests(page.getHtml().links().regex("http://www\\.dianping\\.com/\\w+$").toStrings());
            return;
        }
        Pattern p = Pattern.compile("http://www\\.dianping\\.com/\\w+");
        Matcher matcher = p.matcher(page.getUrl().toString());
        if (matcher.matches()) {
            page.addTargetRequests(page.getHtml().xpath("//li[@class='term-list-item']//a/@href").regex("http://www\\.dianping\\.com/search/.*").toStrings());
        } else {
            p = Pattern.compile("http://www\\.dianping\\.com/search/.*");
            matcher = p.matcher(page.getUrl().toString());
            if (matcher.matches()) {
                String result = page.getHtml().regex("您要查看的内容不存在").toString();
                if (result != null) {
                    System.err.println("No!Url not exist!" + page.getUrl());
                }
            }
        }
    }

    @Override
    public Site getSite() {
        return Site.me().setDomain("www.dianping.com").addStartUrl("http://www.dianping.com/citylist")
                .setSleepTime(0).setUserAgent("I'm a performance tester created by yihua.huang");
    }

    public static void main(String[] args) {
        int sleepTime = 0;
        if (args.length > 0) {
            sleepTime = Integer.parseInt(args[0]);
        }
        DianpingIndexProcessor dianpingProcessor = new DianpingIndexProcessor();
        dianpingProcessor.getSite().setSleepTime(sleepTime);
        Spider.create(dianpingProcessor).thread(10).run();
    }
}
