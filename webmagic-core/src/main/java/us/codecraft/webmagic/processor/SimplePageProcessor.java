package us.codecraft.webmagic.processor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.utils.UrlUtils;

import java.util.List;

/**
 * 非常简单的抽取器。链接抽取使用定义的通配符，并保存抽取整个内容到content字段。<br>
 * @author code4crafter@gmail.com <br>
 * Date: 13-4-22
 * Time: 下午9:15
 */
public class SimplePageProcessor implements PageProcessor {

    private String urlPattern;

    private Site site;

    public SimplePageProcessor(String startUrl, String urlPattern) {
        this.site = Site.me().addStartUrl(startUrl).
                setDomain(UrlUtils.getDomain(startUrl));
        //compile "*" expression to regex
        this.urlPattern = "("+urlPattern.replace(".","\\.").replace("*","[^\"'#]*")+")";

    }

    @Override
    public void process(Page page) {
        List<String> requests = page.getHtml().links().regex(urlPattern).all();
        //调用page.addTargetRequests()方法添加待抓取链接
        page.addTargetRequests(requests);
        //xpath方式抽取
        page.putField("title", page.getHtml().xpath("//title"));
        //sc表示使用Readability技术抽取正文
        page.putField("html", page.getHtml().toString());
        page.putField("content", page.getHtml().smartContent());
    }

    @Override
    public Site getSite() {
        //定义抽取站点的相关参数
        return site;
    }
}
