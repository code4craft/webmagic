package us.codecraft.webmagic.model.samples;

import us.codecraft.webmagic.MultiPageModel;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.ExtractByUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.MultiPagePipeline;
import us.codecraft.webmagic.scheduler.RedisScheduler;

import java.util.Collection;
import java.util.List;

/**
 * @author code4crafter@gmail.com <br>
 */
@TargetUrl("http://news.163.com/\\d+/\\d+/\\d+/\\w+*.html")
public class News163 implements MultiPageModel {

    @ExtractByUrl("http://news\\.163\\.com/\\d+/\\d+/\\d+/([^_]*).*\\.html")
    private String pageKey;

    @ExtractByUrl(value = "http://news\\.163\\.com/\\d+/\\d+/\\d+/\\w+_(\\d+)\\.html", notNull = false)
    private String page;

    @ExtractBy(value = "//div[@class=\"ep-pages\"]//a/regex('http://news\\.163\\.com/\\d+/\\d+/\\d+/\\w+_(\\d+)\\.html',1)"
            , multi = true, notNull = false)
    private List<String> otherPage;

    @ExtractBy("//h1[@id=\"h1title\"]/text()")
    private String title;

    @ExtractBy("//div[@id=\"epContentLeft\"]")
    private String content;

    @Override
    public String getPageKey() {
        return pageKey;
    }

    @Override
    public Collection<String> getOtherPages() {
        return otherPage;
    }

    @Override
    public String getPage() {
        if (page == null) {
            return "1";
        }
        return page;
    }

    @Override
    public MultiPageModel combine(MultiPageModel multiPageModel) {
        News163 news163 = new News163();
        news163.title = this.title;
        News163 pagedModel1 = (News163) multiPageModel;
        news163.content = this.content + pagedModel1.content;
        return news163;
    }

    @Override
    public String toString() {
        return "News163{" +
                "content='" + content + '\'' +
                ", title='" + title + '\'' +
                ", otherPage=" + otherPage +
                '}';
    }

    public static void main(String[] args) {
        OOSpider.create(Site.me(), News163.class).addUrl("http://news.163.com/13/0802/05/958I1E330001124J_2.html")
                .scheduler(new RedisScheduler("localhost")).addPipeline(new MultiPagePipeline()).addPipeline(new ConsolePipeline()).run();
    }

}
