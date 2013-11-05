package us.codecraft.webmagic.example;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.model.AfterExtractor;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.UrlTemplate;
import us.codecraft.webmagic.model.direct.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * @since 0.4.0
 *   NO implement yet!!!!!!!!!!!!
 * @author code4crafter@gmail.com
 */
@UrlTemplate("http://baike.baidu.com/search/word?word=${word}&enc=utf8")
public class BaiduBaike implements AfterExtractor{

    private String word;

    @ExtractBy("//div[@id='lemmaContent-0']//div[@class='para']/allText()")
    private String description;

    @Override
    public void afterProcess(Page page) {

    }

    public static void main(String[] args) {
        List<Param> words = new ArrayList<Param>();
        words.add(new Param().put("word","红烧肉"));
        OOSpider.direct(words, BaiduBaike.class).thread(10).run();
    }
}
