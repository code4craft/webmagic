package us.codecraft.webmagic.model;

import org.junit.Ignore;
import org.junit.Test;
import us.codecraft.webmagic.Site;

/**
 * @author code4crafter@gmail.com <br>
 * @date: 13-8-1 <br>
 * Time: 下午8:42 <br>
 */
public class TestFetcher {

    @Ignore("takes long")
    @Test
    public void test() {
        OOSpider.create(Site.me().addStartUrl("http://my.oschina.net/flashsword/blog/145796"), OschinaBlog.class)
                .run();

    }



}
