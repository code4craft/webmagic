package us.codecraft.webmagic.example;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.ConsolePageModelPipeline;
import us.codecraft.webmagic.model.HasKey;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.ExtractByUrl;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

import java.util.List;

/**
 * @author code4crafter@gmail.com <br>
 * @since 0.3.2
 */
@TargetUrl("https://github.com/\\w+/\\w+")
@HelpUrl({"https://github.com/\\w+\\?tab=repositories", "https://github.com/\\w+", "https://github.com/explore/*"})
public class GithubRepo implements HasKey {

    @ExtractBy(value = "//h1[@class='public']/strong/a/text()", notNull = true)
    private String name;

    @ExtractByUrl("https://github\\.com/(\\w+)/.*")
    private String author;

    @ExtractBy("//div[@id='readme']/tidyText()")
    private String readme;

    @ExtractBy(value = "//div[@class='repository-lang-stats']//li//span[@class='lang']/text()", multi = true)
    private List<String> language;

    @ExtractBy("//ul[@class='pagehead-actions']/li[1]//a[@class='social-count js-social-count']/text()")
    private int star;

    @ExtractBy("//ul[@class='pagehead-actions']/li[2]//a[@class='social-count']/text()")
    private int fork;

    @ExtractByUrl
    private String url;

    public static void main(String[] args) {
        OOSpider.create(Site.me().setSleepTime(100)
                , new ConsolePageModelPipeline(), GithubRepo.class)
                .addUrl("https://github.com/code4craft").thread(10).run();
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

    public int getStar() {
        return star;
    }

    public int getFork() {
        return fork;
    }

    @Override
    public String toString() {
        return "GithubRepo{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", readme='" + readme + '\'' +
                ", language=" + language +
                ", star=" + star +
                ", fork=" + fork +
                ", url='" + url + '\'' +
                '}';
    }
}
