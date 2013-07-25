package us.codecraft.webmagic.samples;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * @author code4crafter@gmail.com <br>
 * Date: 13-4-21
 * Time: 下午8:08
 */
public class HuxiuProcessor implements PageProcessor {
    @Override
    public void process(Page page) {
        //http://progressdaily.diandian.com/post/2013-01-24/40046867275
        List<String> requests = page.getHtml().regex("<a[^<>\"']*href=[\"']{1}([/]{0,1}article[^<>#\"']*?)[\"']{1}").all();
        page.addTargetRequests(requests);
        page.putField("title",page.getHtml().xpath("//div[@class='neirong']//h1[@class='ph xs5']"));
        page.putField("content",page.getHtml().smartContent());
    }

    @Override
    public Site getSite() {
        return Site.me().setDomain("www.huxiu.com").addStartUrl("http://www.huxiu.com/").
                setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
    }
}
