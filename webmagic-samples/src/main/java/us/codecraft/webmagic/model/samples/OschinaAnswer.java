package us.codecraft.webmagic.model.samples;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.*;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

/**
 * @author code4crafter@gmail.com <br>
 * @date: 13-8-3 <br>
 * Time: 下午8:25 <br>
 */
@TargetUrl("http://www.oschina.net/question/\\d+_\\d+*")
@HelpUrl("http://www.oschina.net/question/*")
@ExtractBy(value = "//ul[@class='list']/li[@class='Answer']", multi = true)
public class OschinaAnswer implements AfterExtractor{

    @ExtractBy("//img/@title")
    private String user;

    @ExtractBy("//div[@class='detail']")
    private String content;

    public static void main(String[] args) {
        OOSpider.create(Site.me().addStartUrl("http://www.oschina.net/question/567527_120597"), OschinaAnswer.class).run();
    }

    @Override
    public void afterProcess(Page page) {

    }
}
