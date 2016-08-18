package us.codecraft.webmagic.samples;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.PriorityScheduler;

/**
 * @author code4crafer@gmail.com
 */
public class AlexanderMcqueenGoodsProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(0);


    public static final String URL_LIST = "http://www\\.alexandermcqueen\\.cn/.*";

    public static final String URL_POST = "http://www\\.alexandermcqueen\\.cn/cn/\\w+/.*\\.html";

    @Override
    public void process(Page page) {
        if (page.getUrl().regex(URL_POST).match()) {
            page.putField("goodsName", page.getHtml().xpath("//div[@id='description']/h1/tidyText()"));
            if (page.getResultItems().get("goodsName") == null) {
                page.setSkip(true);
            }
            page.putField("currency", page.getHtml().xpath("//div[@id='description']//div[@class='itemBoxPrice']/span//span[@class='currency']/tidyText()"));
            page.putField("goodsPrice", page.getHtml().xpath("//div[@id='description']//div[@class='itemBoxPrice']/span//span[@class='priceValue']/tidyText()"));
            page.putField("description", page.getHtml()
                    .xpath("//div[@id='tabbedDescription']//div[@class='tabbedDescription']//ul[@id='tabs']//li[@id='tab_description']/div[@id='description_pane']/tidyText()"));
            page.putField("material", page.getHtml()
                    .xpath("//div[@id='tabbedDescription']" +
                            "//div[@class='tabbedDescription']" +
                            "//ul[@id='tabs']" +
                            "//li[@id='tab_description']" +
                            "//div[@class='productProperty']" +
                            "//div[@class='productPropertyRow']/span[2]/tidyText()"));
            page.putField("goodsCode", page.getHtml()
                    .xpath("//div[@id='tabbedDescription']" +
                            "//div[@class='tabbedDescription']" +
                            "//ul[@id='tabs']" +
                            "//li[@id='tab_description']" +
                            "//div[@class='productProperty']" +
                            "//div[@class='productPropertyRow']//span[@id='modelFabricColorContainer']/tidyText()"));
            page.putField("goodsSize", page.getHtml()
                    .xpath("//div[@id='sizesContainer']//div[@id='sizes']//ul[@class='SizeW']"));
            page.putField("goodsColors", page.getHtml()
                    .xpath("//div[@id='colors']/ul/html()"));
        } else {
            page.addTargetRequests(page.getHtml().links().regex(URL_POST).all(), 1000);
            page.addTargetRequests(page.getHtml().links().regex(URL_LIST).all(), 1);
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new AlexanderMcqueenGoodsProcessor()).setScheduler(new PriorityScheduler())
                .addUrl("http://www.alexandermcqueen.cn/sitemap.asp?tskay=E2F1A848").thread(5).run();
    }
}