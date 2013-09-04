package us.codecraft.webmagic.utils;

import org.apache.commons.lang3.BooleanUtils;

import java.util.Properties;

/**
 * @author code4crafter@gmail.com
 * @since 0.2.2
 */
public abstract class EnvironmentUtil {

    private static final String USE_XSOUP = "xsoup";

    public static boolean useXsoup() {
        Properties properties = System.getProperties();
        Object o = properties.get(USE_XSOUP);
        if (o == null) {
            return true;
        }
        return BooleanUtils.toBoolean(((String) o).toLowerCase());
    }

    public static void setUseXsoup(boolean useXsoup) {
        Properties properties = System.getProperties();
        properties.setProperty(USE_XSOUP, BooleanUtils.toString(useXsoup, "true", "false"));
    }
}
