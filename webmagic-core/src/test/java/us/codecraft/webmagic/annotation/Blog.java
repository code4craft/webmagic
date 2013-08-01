package us.codecraft.webmagic.annotation;

/**
 * @author yihua.huang@dianping.com <br>
 * @date: 13-8-1 <br>
 * Time: 下午10:18 <br>
 */
@TargetUrl("http://my.oschina.net/flashsword/blog/*")
public class Blog {

    @Fetcher("//title")
    private String title;

    @Fetcher(value = "div.BlogContent",type = Fetcher.Type.Css)
    private String content;

    @Override
    public String toString() {
        return "Blog{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
