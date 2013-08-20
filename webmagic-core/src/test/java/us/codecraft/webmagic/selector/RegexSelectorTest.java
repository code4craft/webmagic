package us.codecraft.webmagic.selector;

import junit.framework.Assert;
import org.junit.Test;

/**
 * @author code4crafter@gmail.com <br>
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
