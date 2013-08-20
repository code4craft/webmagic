package us.codecraft.webmagic.selector;

import junit.framework.Assert;
import org.junit.Test;

import static us.codecraft.webmagic.selector.Selectors.*;

/**
 * @author code4crafter@gmail.com <br>
 */
public class ExtractorsTest {

    String html = "<div><h1>test<a href=\"xxx\">aabbcc</a></h1></div>";

    String html2 = "<title>aabbcc</title>";

    @Test
    public void testEach() {
        Assert.assertEquals("<a href=\"xxx\">aabbcc</a>", $("div h1 a").select(html));
        Assert.assertEquals("xxx", $("div h1 a", "href").select(html));
        Assert.assertEquals("aabbcc", $("div h1 a", "innerHtml").select(html));
        Assert.assertEquals("xxx", xpath("//a/@href").select(html));
        Assert.assertEquals("xxx", regex("a href=\"(.*)\"").select(html));
        Assert.assertEquals("xxx", regex("(a href)=\"(.*)\"", 2).select(html));
    }

    @Test
    public void testCombo() {
        Assert.assertEquals("bb", and($("title"), regex("aa(bb)cc")).select(html2));
        OrSelector or = or($("div h1 a", "innerHtml"), xpath("//title"));
        Assert.assertEquals("aabbcc", or.select(html));
        Assert.assertEquals("aabbcc", or.select(html2));
    }
}
