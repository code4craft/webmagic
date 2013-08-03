package us.codecraft.webmagic.annotation.samples;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.annotation.ExtractBy;
import us.codecraft.webmagic.annotation.OOSpider;
import us.codecraft.webmagic.annotation.TargetUrl;

/**
 * @author yihua.huang@dianping.com <br>
 * @date: 13-8-2 <br>
 * Time: 上午7:52 <br>
 */
@TargetUrl("http://dengminhui.iteye.com/blog/*")
public class IteyeBlog implements Blog{

    @ExtractBy("//title")
    private String title;

    @ExtractBy(value = "div#blog_content",type = ExtractBy.Type.Css)
    private String content;

    @Override
    public String toString() {
        return "IteyeBlog{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public static void main(String[] args) {
        OOSpider.create(Site.me().addStartUrl("http://dengminhui.iteye.com/blog"), IteyeBlog.class).run();
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
