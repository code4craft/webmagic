package us.codecraft.webmagic.selector;

import junit.framework.Assert;
import org.junit.Test;

/**
 * User: cairne
 * Date: 13-4-21
 * Time: 上午7:13
 */
public class RegexSelectorTest {

    @Test
    public void testInvalidRegex() {
        String regex = "\\d+(";
        try {
            new RegexSelector(regex);
            Assert.assertNotNull(regex);
        } catch (Exception e) {

        }
    }
}
