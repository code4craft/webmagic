package us.codecraft.webmagic.utils;

import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * @author code4crafter@gmail.com
 */
public class EnvironmentUtilTest {

    @Test
    public void test() {
        assertTrue(EnvironmentUtil.useXsoup());
        EnvironmentUtil.setUseXsoup(false);
        assertFalse(EnvironmentUtil.useXsoup());
    }
}
