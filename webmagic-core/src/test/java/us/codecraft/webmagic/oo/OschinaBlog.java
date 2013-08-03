package us.codecraft.webmagic.oo;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;

import java.util.List;

/**
 * @author yihua.huang@dianping.com <br>
 * @date: 13-8-1 <br>
 * Time: 下午10:18 <br>
 */
@TargetUrl(value="http://my.oschina.net/flashsword/blog/*",sourceRegion = "//div[@class='BlogLinks']//a/@href")
public class OschinaBlog implements AfterExtractor {

    @ExtractBy("//title")
    private String title;

    @ExtractBy(value = "div.BlogContent", type = ExtractBy.Type.Css)
    private String content;

    @ExtractBy(value = "//div[@class='BlogTags']/a/text()", multi = true)
    private List<String> tags;

    @Override
    public void afterProcess(Page page) {
        System.out.println("title:\t"+title);
        System.out.println("content:\t"+content);
        System.out.println("tags:\t" + tags);
    }

    public static void main(String[] args) {
        OOSpider.create(Site.me().addStartUrl("http://my.oschina.net/flashsword/blog/145796"), OschinaBlog.class)
                .run();
    }
}