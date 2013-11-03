package us.codecraft.webmagic.model.samples;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.PageModelPipeline;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.ExtractByUrl;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

/**
 * @author code4crafter@gmail.com <br>
 */
@TargetUrl("http://www.36kr.com/p/\\d+.html")
@HelpUrl("http://www.36kr.com/#/page/\\d+")
public class Kr36NewsModel {

    @ExtractBy("//h1[@class='entry-title sep10']")
    private String title;

    @ExtractBy("//div[@class='mainContent sep-10']/tidyText()")
    private String content;

    @ExtractByUrl
    private String url;

    public static void main(String[] args) {
        //Just for benchmark
        OOSpider.create(Site.me().addStartUrl("http://www.36kr.com/").setSleepTime(0), new PageModelPipeline() {
            @Override
            public void process(Object o, Task task) {

            }
        },Kr36NewsModel.class).thread(20).run();
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getUrl() {
        return url;
    }
}
