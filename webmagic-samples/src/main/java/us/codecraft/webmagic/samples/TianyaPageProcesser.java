package us.codecraft.webmagic.samples;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * @author code4crafter@gmail.com <br>
 * Date: 13-4-21
 * Time: 下午1:48
 */
public class TianyaPageProcesser implements PageProcessor {

    @Override
    public void process(Page page) {
        List<String> strings = page.getHtml().regex("<a[^<>]*href=[\"']{1}(/post-free.*?\\.shtml)[\"']{1}").toStrings();
        page.addTargetRequests(strings);
        page.putField("title", page.getHtml().xpath("//div[@id='post_head']//span[@class='s_title']//b"));
        page.putField("body",page.getHtml().smartContent());
    }

    @Override
    public Site getSite() {
        return Site.me().setDomain("http://bbs.tianya.cn/").addStartUrl("http://bbs.tianya.cn/");  //To change body of implemented methods use File | Settings | File Templates.
    }
}
