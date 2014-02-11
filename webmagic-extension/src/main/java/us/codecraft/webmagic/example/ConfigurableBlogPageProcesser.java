package us.codecraft.webmagic.example;

import java.util.List;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.configurable.Inject;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @author code4crafter@gmail.com <br>
 */
public class ConfigurableBlogPageProcesser implements PageProcessor {

    private Site site = Site.me().setDomain("my.oschina.net");

    @Inject("linkRegex")
    private String linkRegex;

    @Inject("titleXpath")
    private String titleXpath;

    @Inject("contentXpath")
    private String contentXpath;

    @Inject("tagsXpath")
    private String tagsXpath;

    @Override
    public void process(Page page) {
        List<String> links = page.getHtml().links().regex(linkRegex).all();
        page.addTargetRequests(links);
        page.putField("title", page.getHtml().xpath(titleXpath).toString());
        if (page.getResultItems().get("title") == null) {
            //skip this page
            page.setSkip(true);
        }
        page.putField("content", page.getHtml().smartContent().toString());
        page.putField("tags", page.getHtml().xpath(tagsXpath).all());
    }

    @Override
    public Site getSite() {
        return site;

    }

    public static void main(String[] args) {
        Spider.create(new ConfigurableBlogPageProcesser()).addUrl("http://my.oschina.net/flashsword/blog").thread(2).run();
    }
}
