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
        ObjectPipeline objectPipeline = new ObjectPipeline();
        Spider.create(ObjectPageProcessor.create(Site.me().addStartUrl("http://my.oschina.net/flashsword/blog/145796"), OschinaBlog.class))
                .pipeline(objectPipeline).runAsync();
        OschinaBlog oschinaBlog = null;
        while ((oschinaBlog = objectPipeline.read()) != null) {
            System.out.println(oschinaBlog);
        }

    }

}
