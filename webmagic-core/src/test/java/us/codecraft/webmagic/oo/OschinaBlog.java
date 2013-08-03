package us.codecraft.webmagic.oo;

import us.codecraft.webmagic.Page;

import java.util.List;

/**
 * @author yihua.huang@dianping.com <br>
 * @date: 13-8-1 <br>
 * Time: 下午10:18 <br>
 */
@TargetUrl(value="http://my.oschina.net/flashsword/blog/*",sourceRegion = "//div[@class='BlogLinks']")
public class OschinaBlog implements AfterExtractor {

    @ExtractBy("//title")
    private String title;

    @ExtractBy(value = "div.BlogContent", type = ExtractBy.Type.Css)
    private String content;

    @ExtractBy(value = "//div[@class='BlogTags']/a/text()", multi = true)
    private List<String> tags;

    @Override
    public void afterProcess(Page page) {
        content = null;
    }
}
