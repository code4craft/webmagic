package us.codecraft.webmagic.model.samples;

import us.codecraft.webmagic.MultiPageModel;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ComboExtract;
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

    @ComboExtract(value = {@ExtractBy("//div[@class=\"ep-pages\"]//a/@href"),
            @ExtractBy(value = "http://news\\.163\\.com/\\d+/\\d+/\\d+/\\w+_(\\d+)\\.html", type = ExtractBy.Type.Regex)},
            multi = true, notNull = false)
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
        OOSpider.create(Site.me().addStartUrl("http://news.163.com/13/0802/05/958I1E330001124J_2.html"), News163.class)
                .scheduler(new RedisScheduler("localhost")).clearPipeline().pipeline(new MultiPagePipeline()).pipeline(new ConsolePipeline()).run();
    }

}
