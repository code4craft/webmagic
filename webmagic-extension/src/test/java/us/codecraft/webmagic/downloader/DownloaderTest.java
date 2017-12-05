package us.codecraft.webmagic.downloader;

import org.junit.Test;
import us.codecraft.platform.download.DownloaderRule;
import us.codecraft.platform.download.selenium.cdp4j.CdpSeleniumSubDownloader;
import us.codecraft.platform.download.selenium.webdriver.phantomjs.PhantomJsSeleniumSubDownloader;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.Arrays;
import java.util.List;

/**
 * @Author :王龙
 * @Description
 * @Data 2017/11/23 11:26
 * @Modified By：
 */
public class DownloaderTest {
    static PageProcessor pageProcessor = new PageProcessor() {
        @Override
        public void process(Page page) {
            System.out.println("-------------------");
            System.out.println(page.getStatusCode());
            System.out.println(page.getUrl());
//            System.out.println(page.getRawText().toString());
            System.out.println(page.getHtml().getDocument().title());
        }

        @Override
        public Site getSite() {
            return Site.me();
        }
    };

    @Test
    public void phantomsJsTest() {
        PhantomJsSeleniumSubDownloader downloader = new PhantomJsSeleniumSubDownloader(new DownloaderRule());
        Spider.create(pageProcessor).addUrl("https://github.com/webfolderio/cdp4j", "https://www.cnblogs.com/", "http://hz.58.com/").thread(3).setDownloader(downloader).run();
    }

    @Test
    public void cdp4jTest() {
        List<String> configList = Arrays.asList("--headless", "--disable-gpu");
        CdpSeleniumSubDownloader downloader = new CdpSeleniumSubDownloader(new DownloaderRule(),configList);
//        CdpSeleniumSubDownloader downloader = new CdpSeleniumSubDownloader(new DownloaderRule());
        Spider.create(pageProcessor).addUrl("https://github.com/webfolderio/cdp4j", "https://www.cnblogs.com/", "http://hz.58.com/").thread(3).setDownloader(downloader).run();
    }

}
