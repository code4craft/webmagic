package us.codecraft.spider.selector;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * User: cairne
 * Date: 13-4-21
 * Time: 上午10:35
 */
public class HtmlCleanerTest {

    @Test
    public void test() throws IOException {
        HtmlCleaner htmlCleaner = new HtmlCleaner();

        CleanerProperties props = htmlCleaner.getProperties();

        TagNode node = htmlCleaner.clean(new URL("http://www.huanqiu.com"),"UTF-8");
        System.out.println(node.getAllElementsList(true));
        System.out.println(node);
    }
}
