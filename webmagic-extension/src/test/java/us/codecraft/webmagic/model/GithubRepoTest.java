package us.codecraft.webmagic.model;

import junit.framework.Assert;
import org.junit.Test;
import us.codecraft.webmagic.downloader.MockGithubDownloader;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

/**
 * @author code4crafter@gmail.com <br>
 */
public class GithubRepoTest {

    @Test
    public void test() {
        OOSpider.create(Site.me().addStartUrl("https://github.com/code4craft/webmagic").setSleepTime(0)
                , new PageModelPipeline<GithubRepo>() {
            @Override
            public void process(GithubRepo o, Task task) {
                Assert.assertEquals(86, o.getStar());
                Assert.assertEquals(70, o.getFork());
            }
        }, GithubRepo.class).setDownloader(new MockGithubDownloader()).test("https://github.com/code4craft/webmagic");
    }
}
