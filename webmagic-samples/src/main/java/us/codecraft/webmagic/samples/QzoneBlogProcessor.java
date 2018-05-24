package us.codecraft.webmagic.samples;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * @author code4crafter@gmail.com <br>
 */
public class QzoneBlogProcessor implements PageProcessor {
    @Override
    public void process(Page page) {
        //http://progressdaily.diandian.com/post/2013-01-24/40046867275

        //http://b1.cnc.qzone.qq.com/cgi-bin/blognew/get_abs?hostUin=233017404&uin=233017404&blogType=0&statYear=2013&source=0&statYear=2013&g_tk=291639571&g_tk=291639571&reqInfo=7&pos=0&num=15&source=0&rand=0.46480297949165106
        // &cateName=&cateHex=&statYear=2013&reqInfo=7&pos=0&num=15&sortType=0&source=0&rand=0.46480297949165106&g_tk=291639571&verbose=1&ref=qzone
        List<String> requests = page.getHtml().regex("<a[^<>]*href=[\"']{1}(http://17dujingdian\\.com/post/[^#]*?)[\"']{1}").all();
        page.addTargetRequests(requests);
        page.putField("title",page.getHtml().xpath("//div[@id='content']//h2/a"));
        page.putField("content",page.getHtml().smartContent());
    }

    @Override
    public Site getSite() {
        return Site.me().setDomain("www.diandian.com").
                setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
    }
}
