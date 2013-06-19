package us.codecraft.webmagic;

import org.junit.Assert;
import org.junit.Test;
import us.codecraft.webmagic.selector.Html;

/**
 * @author code4crafter@gmail.com <br>
 * Date: 13-4-21
 * Time: 上午8:42
 */
public class HtmlTest {

    @Test
    public void testRegexSelector() {
        Html selectable = new Html("aaaaaaab");
        Assert.assertEquals("abbabbab", (selectable.regex("(.*)").replace("aa(a)", "$1bb").toString()));

    }
}
