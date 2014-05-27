package us.codecraft.webmagic.selector;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static us.codecraft.webmagic.selector.Selectors.*;

/**
 * @author code4crafter@gmail.com <br>
 */
public class ExtractorsTest {

    String html = "<div><h1>test<a href=\"xxx\">aabbcc</a></h1></div>";

    String html2 = "<title>aabbcc</title>";

    @Test
    public void testEach() {
        assertThat($("div h1 a").select(html)).isEqualTo("<a href=\"xxx\">aabbcc</a>");
        assertThat($("div h1 a", "href").select(html)).isEqualTo("xxx");
        assertThat($("div h1 a", "innerHtml").select(html)).isEqualTo("aabbcc");
        assertThat(xpath("//a/@href").select(html)).isEqualTo("xxx");
        assertThat(regex("a href=\"(.*)\"").select(html)).isEqualTo("xxx");
        assertThat(regex("(a href)=\"(.*)\"", 2).select(html)).isEqualTo("xxx");
    }

    @Test
    public void testCombo() {
        assertThat(and($("title"), regex("aa(bb)cc")).select(html2)).isEqualTo("bb");
        OrSelector or = or($("div h1 a", "innerHtml"), xpath("//title"));
        assertThat(or.select(html)).isEqualTo("aabbcc");
        assertThat(or.select(html2)).isEqualTo("<title>aabbcc</title>");
    }
}
