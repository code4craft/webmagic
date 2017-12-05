package us.codecraft.platform.download.selenium.webdriver.tool;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.platform.utils.PropertiesUtil;

/**
 * 
 * @author : 王龙
 * @Description ： 读取配置文件，创建DesiredCapabilities类
 * @params ：
 * @return ：
 * @Date ：2017/11/15 13:49
 */
public class DesiredCapabilitiesUtilsl {
    private static Logger logger = LoggerFactory.getLogger(DesiredCapabilitiesUtilsl.class);

    /**
     * 配置文件中的key
     */
    protected final static String PHANTOMJS_PATH_KEY = "phantomjs.path";

    public static DesiredCapabilities getPhantomjsCaps() {
        PropertiesConfiguration config = PropertiesUtil.parseFile("webdriver.properties");
        if (null == config || !config.containsKey(PHANTOMJS_PATH_KEY)) {
            logger.error("缺少webdriver.properties配置文件");
            throw new RuntimeException("缺少webdriver.properties配置文件");
        }

        DesiredCapabilities caps = DesiredCapabilities.phantomjs();
        String phantomjs_path = (String) config.getProperty(PHANTOMJS_PATH_KEY);
        caps.setJavascriptEnabled(true);
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, phantomjs_path);
        // 截图，需要验证某个元素的状态或者显示的数值时，可以将屏幕截取下来进行对比；或者在异常或者错误发生的时候将屏幕截取并保存起来，供后续分析和调试所用
        caps.setCapability(CapabilityType.TAKES_SCREENSHOT, false);
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_PAGE_CUSTOMHEADERS_PREFIX + "User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");

        String[] cliArgsCap = new String[]{"--load-images=no", "--ignore-ssl-errors=yes"};
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, cliArgsCap);
        return caps;
    }
}
