package us.codecraft.webmagic.lucene;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.TargetUrl;
import us.codecraft.webmagic.pipeline.LucenePipeline;

import java.io.IOException;
import java.util.List;

/**
 * @author code4crafter@gmail.com <br>
 * Date: 13-8-2 <br>
 * Time: 上午7:52 <br>
 */
@TargetUrl("http://my.oschina.net/flashsword/blog/\\d+")
public class OschinaBlog {

    @ExtractBy("//title")
    private String title;

    @ExtractBy(value = "div.BlogContent", type = ExtractBy.Type.Css)
    private String content;

    @Override
    public String toString() {
        return "OschinaBlog{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public static void main(String[] args) {
        LucenePipeline pipeline = new LucenePipeline();
        OOSpider.create(Site.me().addStartUrl("http://my.oschina.net/flashsword/blog"), OschinaBlog.class).pipeline(pipeline).runAsync();
        while (true) {
            try {
                List<Document> search = pipeline.search("title", "webmagic");
                System.out.println(search);
                Thread.sleep(3000);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
