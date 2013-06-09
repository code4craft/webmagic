package us.codecraft.webmagic.samples;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.PlainText;

import java.util.List;

/**
 * User: cairne
 * Date: 13-4-21
 * Time: 下午8:08
 */
public class DiaoyuwengProcessor implements PageProcessor {
    @Override
    public void process(Page page) {
        List<String> requests = page.getHtml().as().rs("(http://www\\.diaoyuweng\\.com/home\\.php\\?mod=space&uid=88304&do=thread&view=me&type=thread&order=dateline&from=space&page=\\d+)").toStrings();
        page.addTargetRequests(requests);
        requests = page.getHtml().as().rs("(http://www\\.diaoyuweng\\.com/thread-\\d+-1-1.html)").toStrings();
        page.addTargetRequests(requests);
        if (page.getUrl().toString().contains("thread")){
            page.putField("title", page.getHtml().x("//a[@id='thread_subject']"));
            page.putField("content", page.getHtml().x("//div[@class='pcb']//tbody"));
            page.putField("date",page.getHtml().r("发表于 (\\d{4}-\\d+-\\d+ \\d+:\\d+:\\d+)"));
            page.putField("id",new PlainText("1000"+page.getUrl().r("http://www\\.diaoyuweng\\.com/thread-(\\d+)-1-1.html").toString()));
        }
    }

    @Override
    public Site getSite() {
        return Site.me().setDomain("www.diaoyuweng.com").setStartUrl("http://www.diaoyuweng.com/home.php?mod=space&uid=88304&do=thread&view=me&type=thread&from=space").
                setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31").setEncoding("GBK").setSleepTime(500);
    }
}
