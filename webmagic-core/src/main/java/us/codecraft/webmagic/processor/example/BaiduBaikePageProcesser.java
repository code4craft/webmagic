package us.codecraft.webmagic.processor.example;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author code4crafter@gmail.com <br>
 * @since 0.4.0
 */
public class BaiduBaikePageProcesser implements PageProcessor {

    private Site site = Site.me()//.setHttpProxy(new HttpHost("127.0.0.1",8888))
            .setCharset("utf-8").setRetryTimes(3).setSleepTime(1000).setUseGzip(true);

    @Override
    public void process(Page page) {
        page.putField("name", page.getHtml().$("h1.title div.lemmaTitleH1","text").toString());
        page.putField("description", page.getHtml().xpath("//div[@id='lemmaContent-0']//div[@class='para']/allText()"));
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider spider = Spider.create(new BaiduBaikePageProcesser()).thread(2);
        List<String> list = new ArrayList<String>();
        String urlTemplate = "http://baike.baidu.com/search/word?word=%s&pic=1&sug=1&enc=utf8";
        list.add(String.format(urlTemplate,"水力发电"));
        list.add(String.format(urlTemplate,"风力发电"));
        list.add(String.format(urlTemplate,"太阳能"));
        list.add(String.format(urlTemplate,"地热发电"));
        list.add(String.format(urlTemplate,"地热发电"));
        List<ResultItems> resultItemses = spider.getAll(list);
        for (ResultItems resultItemse : resultItemses) {
            System.out.println(resultItemse.getAll());
        }
        spider.close();
    }
}
