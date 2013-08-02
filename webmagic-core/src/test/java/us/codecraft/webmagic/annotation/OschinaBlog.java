package us.codecraft.webmagic.annotation;

/**
 * @author yihua.huang@dianping.com <br>
 * @date: 13-8-1 <br>
 * Time: 下午10:18 <br>
 */
@TargetUrl("http://my.oschina.net/flashsword/blog/*")
public class OschinaBlog {

    @ExtractBy("//title")
    private String title;

    @ExtractBy(value = "div.BlogContent",type = ExtractBy.Type.Css)
    private String content;

}
