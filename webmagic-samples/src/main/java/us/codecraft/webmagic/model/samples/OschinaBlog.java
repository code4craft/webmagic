package us.codecraft.webmagic.model.samples;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.HasKey;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.TargetUrl;
import us.codecraft.webmagic.pipeline.JsonFilePageModelPipeline;

import java.util.List;

/**
 * @author code4crafter@gmail.com <br>
 */
@TargetUrl("http://my.oschina.net/flashsword/blog/\\d+")
public class OschinaBlog implements HasKey{

    @ExtractBy("//title")
    private String title;

    @ExtractBy(value = "div.BlogContent",type = ExtractBy.Type.Css)
    private String content;

    @ExtractBy(value = "//div[@class='BlogTags']/a/text()", multi = true)
    private List<String> tags;

    public static void main(String[] args) {
        OOSpider.create(Site.me().addStartUrl("http://my.oschina.net/flashsword/blog")
                ,new JsonFilePageModelPipeline(), OschinaBlog.class).run();
    }

    @Override
    public String key() {
        return title;
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
}
