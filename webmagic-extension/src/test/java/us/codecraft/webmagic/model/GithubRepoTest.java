package us.codecraft.webmagic.model;

import org.junit.Test;
import us.codecraft.webmagic.SimpleHttpClient;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.MockGithubDownloader;
import us.codecraft.webmagic.pipeline.PageModelPipeline;
import us.codecraft.webmagic.example.GithubRepo;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author code4crafter@gmail.com <br>
 */
public class GithubRepoTest {

    @Test
    public void test() {
        OOSpider.create(Site.me().setSleepTime(0)
                , new PageModelPipeline<GithubRepo>() {
            @Override
            public void process(GithubRepo o, Task task) {
                assertThat(o.getStar()).isEqualTo(86);
                assertThat(o.getFork()).isEqualTo(70);
            }
        }, GithubRepo.class).addUrl("https://github.com/code4craft/webmagic").setDownloader(new MockGithubDownloader()).test("https://github.com/code4craft/webmagic");
    }

    @Test
    public void test1() throws Exception {
        SimpleHttpClient simpleHttpClient = new SimpleHttpClient();
        GithubRepo model = simpleHttpClient.get("https://github.com/code4craft/webmagic",GithubRepo.class);
        System.out.println(model);

    }
}
