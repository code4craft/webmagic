package us.codecraft.webmagic.example;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.AfterExtractor;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.Formatter;
import us.codecraft.webmagic.model.annotation.TargetUrl;
import us.codecraft.webmagic.pipeline.JsonFilePageModelPipeline;

import java.util.List;

/**
 * @author code4crafter@gmail.com <br>
 */
@TargetUrl("http://my.oschina.net/flashsword/blog/\\d+")
public class OschinaBlog implements AfterExtractor{

    @ExtractBy("//title/text()")
    private String title;

    @ExtractBy(value = "div.BlogContent",type = ExtractBy.Type.Css)
    private String content;

    @ExtractBy(value = "//div[@class='BlogTags']/a/text()", multi = true)
    private List<String> tags;

    @Formatter("YYYY-MM-dd HH:mm")
    @ExtractBy("//div[@class='BlogStat']/regex('\\d+-\\d+-\\d+\\s+\\d+:\\d+')")
    private String date;

    public static void main(String[] args) {
        OOSpider.create(Site.me().addStartUrl("http://my.oschina.net/flashsword/blog")
                ,new JsonFilePageModelPipeline("/data/webmagic/"), OschinaBlog.class).run();
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public List<String> getTags() {
        return tags;
    }

//    public Date getDate() {
//        return date;
//    }

    @Override
    public void afterProcess(Page page) {
        System.out.println(date);
        System.out.println(title);
    }
}
