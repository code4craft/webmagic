package us.codecraft.webmagic.example;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;

import java.util.ArrayList;
import java.util.List;

/**
 * @since 0.4.0
 * @author code4crafter@gmail.com
 */
public class BaiduBaike{

    @ExtractBy("//h1[@class=title]/div[@class=lemmaTitleH1]/text()")
    private String name;

    @ExtractBy("//div[@id='lemmaContent-0']//div[@class='para']/allText()")
    private String description;

    @Override
    public String toString() {
        return "BaiduBaike{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        String urlTemplate = "http://baike.baidu.com/search/word?word=%s&pic=1&sug=1&enc=utf8";
        list.add(String.format(urlTemplate,"水力发电"));
        list.add(String.format(urlTemplate,"风力发电"));
        list.add(String.format(urlTemplate,"太阳能"));
        list.add(String.format(urlTemplate,"地热发电"));
        list.add(String.format(urlTemplate, "地热发电"));
        List<BaiduBaike> baiduBaikes = OOSpider.create(Site.me().setSleepTime(100), BaiduBaike.class).<BaiduBaike>getAll(list);
        System.out.println(baiduBaikes);
    }
}
