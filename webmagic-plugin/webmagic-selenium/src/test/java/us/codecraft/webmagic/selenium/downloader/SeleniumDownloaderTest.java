package us.codecraft.webmagic.selenium.downloader;

import org.junit.Ignore;
import org.junit.Test;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;

/**
 * @author yihua.huang@dianping.com <br>
 * @date: 13-7-26 <br>
 * Time: 下午2:46 <br>
 */
public class SeleniumDownloaderTest {

    private String chromeDriverPath = "";

    @Ignore("need chrome driver")
    @Test
    public void test() {
        SeleniumDownloader seleniumDownloader = new SeleniumDownloader(chromeDriverPath);
        long time1 = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            Page page = seleniumDownloader.download(new Request("http://huaban.com/"), new Task() {
                @Override
                public String getUUID() {
                    return "huaban.com";
                }

                @Override
                public Site getSite() {
                    return Site.me();
                }
            });
            System.out.println(page.getHtml().$("#waterfall").links().regex(".*pins.*").all());
        }
        System.out.println(System.currentTimeMillis() - time1);
    }
}
