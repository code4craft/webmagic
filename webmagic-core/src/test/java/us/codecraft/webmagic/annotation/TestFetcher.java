package us.codecraft.webmagic.annotation;

import org.junit.Test;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;

/**
 * @author yihua.huang@dianping.com <br>
 * @date: 13-8-1 <br>
 * Time: 下午8:42 <br>
 */
public class TestFetcher {

    @Test
    public void test() {
        Spider.create(ObjectPageProcessor.create(Site.me().addStartUrl("http://djjchobits.iteye.com/blog/569000"), Blog.class)).run();

    }

}
