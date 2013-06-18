package us.codecraft.webmagic.processor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.utils.UrlUtils;

import java.util.List;

/**
 * @author code4crafter@gmail.com <br>
 * Date: 13-4-22
 * Time: 下午9:15
 */
public class SimplePageProcessor implements PageProcessor {

    private String urlPattern;

    private static final String UA = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31";

    private Site site;

    public SimplePageProcessor(String startUrl, String urlPattern) {
        this.site = Site.me().addStartUrl(startUrl).
                setDomain(UrlUtils.getDomain(startUrl)).setUserAgent(UA);
        this.urlPattern = "("+urlPattern.replace(".","\\.").replace("*","[^\"'#]*")+")";

    }

    @Override
    public void process(Page page) {
        List<String> requests = page.getHtml().as().rs(urlPattern).toStrings();
        //调用page.addTargetRequests()方法添加待抓取链接
        page.addTargetRequests(requests);
        //xpath方式抽取
        page.putField("title", page.getHtml().x("//title"));
        //sc表示使用Readability技术抽取正文
        page.putField("content", page.getHtml().sc());
    }

    @Override
    public Site getSite() {
        //定义抽取站点的相关参数
        return site;
    }
}
