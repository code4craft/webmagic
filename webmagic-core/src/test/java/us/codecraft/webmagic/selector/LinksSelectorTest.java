package us.codecraft.webmagic.selector;

import org.jsoup.Jsoup;
import org.junit.Test;

import java.util.List;

/**
 * @author code4crafter@gmail.com
 *         Date: 17/4/8
 *         Time: 下午9:41
 */
public class LinksSelectorTest {

    private String html = "<div><a href='http://whatever.com/aaa'></a></div><div><a href='http://whatever.com/bbb'></a></div>";

    @Test
    public void testLinks() throws Exception {
        LinksSelector linksSelector = new LinksSelector();
        List<String> links = linksSelector.selectList(html);
        System.out.println(links);

        html = "<div><a href='aaa'></a></div><div><a href='http://whatever.com/bbb'></a></div><div><a href='http://other.com/bbb'></a></div>";
        links = linksSelector.selectList(Jsoup.parse(html, "http://whatever.com/"));
        System.out.println(links);
    }
}
