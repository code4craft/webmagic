package us.codecraft.webmagic.oo.samples;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.oo.ExtractBy;
import us.codecraft.webmagic.oo.OOSpider;
import us.codecraft.webmagic.oo.TargetUrl;

/**
 * @author code4crafter@gmail.com <br>
 * @date: 13-8-2 <br>
 * Time: 上午7:52 <br>
 */
@TargetUrl("http://my.oschina.net/flashsword/blog/\\d+")
public class OschinaBlog implements Blog{

    @ExtractBy("//title")
    private String title;

    @ExtractBy(value = "div.BlogContent",type = ExtractBy.Type.Css)
    private String content;

    @Override
    public String toString() {
        return "OschinaBlog{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public static void main(String[] args) {
        OOSpider.create(Site.me().addStartUrl("http://my.oschina.net/flashsword/blog"), OschinaBlog.class).run();
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
