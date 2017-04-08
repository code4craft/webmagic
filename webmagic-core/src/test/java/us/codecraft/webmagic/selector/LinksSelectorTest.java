package us.codecraft.webmagic.selector;

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
        List<String> links = new LinksSelector().selectList(html);
        System.out.println(links);
    }
}
