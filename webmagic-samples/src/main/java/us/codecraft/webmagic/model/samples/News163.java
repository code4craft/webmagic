package us.codecraft.webmagic.model.samples;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.PagedModel;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.*;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.PagedPipeline;
import us.codecraft.webmagic.selector.Selectable;

import java.util.Collection;
import java.util.List;

/**
 * @author yihua.huang@dianping.com <br>
 * @date: 13-8-4 <br>
 * Time: 下午8:17 <br>
 */
@TargetUrl("http://news.163.com/\\d+/\\d+/\\d+/\\w+*.html")
public class News163 implements PagedModel, AfterExtractor {

    @ExtractByUrl("http://news\\.163\\.com/\\d+/\\d+/\\d+/(\\w+)*\\.html")
    private String pageKey;

    @ExtractByUrl(value = "http://news\\.163\\.com/\\d+/\\d+/\\d+/\\w+_(\\d+)\\.html", notNull = false)
    private String page;

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
    public PagedModel combine(PagedModel pagedModel) {
        News163 news163 = new News163();
        News163 pagedModel1 = (News163) pagedModel;
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
                .clearPipeline().pipeline(new PagedPipeline()).pipeline(new ConsolePipeline()).run();
    }

    @Override
    public void afterProcess(Page page) {
        Selectable xpath = page.getHtml().xpath("//div[@class=\"ep-pages\"]//a/@href");
        otherPage = xpath.regex("http://news\\.163\\.com/\\d+/\\d+/\\d+/\\w+_(\\d+)\\.html").all();
    }
}
