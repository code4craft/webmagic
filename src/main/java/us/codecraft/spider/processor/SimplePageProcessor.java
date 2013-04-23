package us.codecraft.spider.processor;

import us.codecraft.spider.Page;
import us.codecraft.spider.Site;
import us.codecraft.spider.utils.UrlUtils;

import java.util.List;

/**
 * User: cairne
 * Date: 13-4-22
 * Time: 下午9:15
 */
public class SimplePageProcessor implements PageProcessor {

    private String urlPattern;

    private static final String UA = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31";

    private Site site;

    public SimplePageProcessor(String startUrl, String urlPattern) {
        this.site = Site.me().setStartUrl(startUrl).
                setDomain(UrlUtils.getDomain(startUrl)).setUserAgent(UA);
        this.urlPattern = "("+urlPattern.replace(".","\\.").replace("*","[^\"'#]*")+")";

    }

    @Override
    public void process(Page page) {
        List<String> requests = page.getHtml().as().rs(urlPattern).toStrings();
        page.addTargetRequests(requests);
        page.putField("title", page.getHtml().x("//title"));
        page.putField("content", page.getHtml().sc());
    }

    @Override
    public Site getSite() {
        return site;
    }
}
