package us.codecraft.webmagic.model;

import junit.framework.Assert;
import org.junit.Test;
import us.codecraft.webmagic.MockDownloader;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.ExtractByUrl;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

import java.util.List;

/**
 * @author code4crafter@gmail.com <br>
 */
@TargetUrl("https://github.com/\\w+/\\w+")
@HelpUrl({"https://github.com/\\w+\\?tab=repositories", "https://github.com/\\w+", "https://github.com/explore/*"})
public class GithubRepo implements HasKey {

    @ExtractBy(value = "//h1[@class='entry-title public']/strong/a/text()", notNull = true)
    private String name;

    @ExtractByUrl("https://github\\.com/(\\w+)/.*")
    private String author;

    @ExtractBy("//div[@id='readme']")
    private String readme;

    @ExtractBy(value = "//div[@class='repository-lang-stats']//li//span[@class='lang']", multi = true)
    private List<String> language;

    @ExtractBy("//ul[@class='pagehead-actions']/li[2]//a[@class='social-count js-social-count']/text()")
    private String star;

    @ExtractBy("//ul[@class='pagehead-actions']/li[3]//a[@class='social-count']/text()")
    private String fork;

    @ExtractByUrl
    private String url;

    @Test
    public void test() {
        OOSpider.create(Site.me().addStartUrl("https://github.com/code4craft/webmagic").setSleepTime(0)
                , new PageModelPipeline<GithubRepo>() {
            @Override
            public void process(GithubRepo o, Task task) {
                Assert.assertEquals("78",o.getStar().trim());
                Assert.assertEquals("65",o.getFork().trim());
            }
        }, GithubRepo.class).setDownloader(new MockDownloader()).test("https://github.com/code4craft/webmagic");
    }

    @Override
    public String key() {
        return author + ":" + name;
    }

    public String getName() {
        return name;
    }

    public String getReadme() {
        return readme;
    }

    public String getAuthor() {
        return author;
    }

    public List<String> getLanguage() {
        return language;
    }

    public String getUrl() {
        return url;
    }

    public String getStar() {
        return star;
    }

    public String getFork() {
        return fork;
    }
}
