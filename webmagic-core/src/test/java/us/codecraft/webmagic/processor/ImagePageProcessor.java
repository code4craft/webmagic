package us.codecraft.webmagic.processor;

import org.jsoup.Jsoup;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.utils.UrlUtils;

public class ImagePageProcessor implements PageProcessor {

    private String urlPattern;

    private Site site;

    public ImagePageProcessor(String startUrl, String urlPattern) {
        this.site = Site.me().addStartUrl(startUrl).
                setDomain(UrlUtils.getDomain(startUrl));
        //compile "*" expression to regex
        this.urlPattern = "(" + urlPattern.replace(".", "\\.").replace("*", "[^\"'#]*") + ")";

    }

    @Override
    public void process(Page page) {
//        List<String> requests = page.getHtml().links().regex(urlPattern).all();
        //add urls to fetch
//        page.addTargetRequests(requests);
        //extract by XPath
        if (page.getRequest().getType() == Request.Type.TEXT) {
            page.putField("title", page.getHtml().xpath("//title"));
            page.putField("html", page.getHtml().toString());
            //extract by Readability
            page.putField("content", page.getHtml().smartContent());

            String url = Jsoup.parse(page.getRawText(), page.getRequest().getUrl()).select("a.pdtname img").first().absUrl("src");
            page.addTargetRequest(url, Request.Type.BYTES);
        }
        else {
//            page.putField("bytes", page.getRawBytes());
            page.putField("is", page.getInputStream());
        }
    }

    @Override
    public Site getSite() {
        //settings
        return site;
    }
}
