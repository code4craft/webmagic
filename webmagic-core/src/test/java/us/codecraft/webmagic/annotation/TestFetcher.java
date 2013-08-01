package us.codecraft.webmagic.annotation;

import org.junit.Ignore;
import org.junit.Test;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;

/**
 * @author yihua.huang@dianping.com <br>
 * @date: 13-8-1 <br>
 * Time: 下午8:42 <br>
 */
public class TestFetcher {

    @Ignore("takes long")
    @Test
    public void test() {
        Spider.create(ObjectPageProcessor.create(Site.me().addStartUrl("http://my.oschina.net/flashsword/blog/145796"), OschinaBlog.class)).run();

    }

}
