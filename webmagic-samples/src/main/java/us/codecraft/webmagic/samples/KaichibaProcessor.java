package us.codecraft.webmagic.samples;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @author code4crafter@gmail.com <br>
 * Date: 13-5-20
 * Time: 下午5:31
 */
public class KaichibaProcessor implements PageProcessor {
    @Override
    public void process(Page page) {
        //http://progressdaily.diandian.com/post/2013-01-24/40046867275
        int i = Integer.valueOf(page.getUrl().regex("shop/(\\d+)").toString()) + 1;
        page.addTargetRequest("http://kaichiba.com/shop/" + i);
        page.putField("title",page.getHtml().xpath("//Title"));
        page.putField("items", page.getHtml().xpath("//li[@class=\"foodTitle\"]").replace("^\\s+", "").replace("\\s+$", "").replace("<span>.*?</span>", ""));
    }

    @Override
    public Site getSite() {
        return Site.me().setDomain("kaichiba.com").setCharset("utf-8").
                setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
    }

    public static void main(String[] args) {
        Spider.create(new KaichibaProcessor()).addUrl("http://kaichiba.com/shop/41725781").run();
    }
}
