package us.codecraft.webmagic.samples;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * @author code4crafter@gmail.com <br>
 * Date: 13-5-20
 * Time: 下午5:31
 */
public class MeicanProcessor implements PageProcessor {
    @Override
    public void process(Page page) {
        //http://progressdaily.diandian.com/post/2013-01-24/40046867275
        List<String> requests = page.getHtml().xpath("//a[@class=\"area_link flat_btn\"]/@href").all();
        if (requests.size() > 2) {
            requests = requests.subList(0, 2);
        }
        page.addTargetRequests(requests);
        page.addTargetRequests(page.getHtml().links().regex("(.*/restaurant/[^#]+)").all());
        page.putField("items", page.getHtml().xpath("//ul[@class=\"dishes menu_dishes\"]/li/span[@class=\"name\"]/text()"));
        page.putField("prices", page.getHtml().xpath("//ul[@class=\"dishes menu_dishes\"]/li/span[@class=\"price_outer\"]/span[@class=\"price\"]/text()"));
    }

    @Override
    public Site getSite() {
        return Site.me().setDomain("meican.com").setCharset("utf-8").
                setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
    }

    public static void main(String[] args) {
        Spider.create(new MeicanProcessor()).addUrl("http://www.meican.com/shanghai/districts").run();
    }
}
