package us.codecraft.webmagic.downloader.selenium;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import us.codecraft.webmagic.proxy.Proxy;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by cucod on 2017-06-30.
 */
public class WebDriverGenerator {
    private static final String DEFAULT_CONFIG_FILE = "/crawler/config.ini";
    private static final String DRIVER_FIREFOX = "firefox";
    private static final String DRIVER_CHROME = "chrome";
    private static final String DRIVER_PHANTOMJS = "phantomjs";
    private static Properties sConfig;
    private Logger logger = Logger.getLogger(getClass());



    public WebDriver generateWebDriver(Proxy proxy) throws IOException {
        WebDriver webDriver = null;
        // Read config file
        sConfig = new Properties();
        String configFilePath = DEFAULT_CONFIG_FILE;
        if (System.getenv("selenuim_config") != null) {
            configFilePath = System.getenv("selenuim_config");
        }
        File configFile = new File(configFilePath);
        if (configFile.exists() && !configFile.isDirectory()) {
            sConfig.load(new FileReader(configFile));
        } else {
            throw new IllegalArgumentException("please put the config in file:" + new File("").getCanonicalPath() + DEFAULT_CONFIG_FILE + ",or check selenuim_config ENV");
        }

        DesiredCapabilities capabilities = null;
        String driver = sConfig.getProperty("driver", DRIVER_PHANTOMJS);
        String remote = sConfig.getProperty("remote");

        try {
            if (remote != null) {
                if (isUrl(remote)) {
                    if (driver.equals(DRIVER_PHANTOMJS)) {
                        capabilities = getPhantomjsCapablities(proxy);
                    } else if (driver.equals(DRIVER_CHROME)) {
                        capabilities = getChromeCapabilities(proxy);
                    } else if (driver.equals(DRIVER_FIREFOX)) {
                        capabilities = DesiredCapabilities.firefox();
                    }
                    setCommonCapabilities(capabilities);
                    webDriver = new RemoteWebDriver(new URL(remote), capabilities);
                    logger.info(String.format("run webdriver with selenium hub %s", remote));
                } else {
                    throw new IllegalArgumentException("selenium hub address must be a url");
                }
            } else {
                if (driver.equals(DRIVER_PHANTOMJS)) {
                    capabilities = getPhantomjsCapablities(proxy);
                    setCommonCapabilities(capabilities);
                    webDriver = new PhantomJSDriver(capabilities);
                } else if (driver.equals(DRIVER_CHROME)) {
                    capabilities = getChromeCapabilities(proxy);
                    setCommonCapabilities(capabilities);
                    webDriver = new ChromeDriver(capabilities);
                } else if (driver.equals(DRIVER_FIREFOX)) {
                    capabilities = DesiredCapabilities.firefox();
                    setCommonCapabilities(capabilities);
                    webDriver = new FirefoxDriver();
                }
            }
        } catch (Exception e) {
            logger.error("get the web driver failed", e);
            if (webDriver != null) {
                webDriver.quit();
            }
        }

        return webDriver;

    }

    // Fetch PhantomJS-specific configuration parameters
    private DesiredCapabilities getPhantomjsCapablities(Proxy proxy) throws IOException {
        DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();

        // Disable "web-security", enable all possible "ssl-protocols" and
        // "ignore-ssl-errors" for PhantomJSDriver
        // capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, new
        // String[] {
        // "--web-security=false",
        // "--ssl-protocol=any",
        // "--ignore-ssl-errors=true"
        // });
        ArrayList<String> cliArgsCap = new ArrayList<String>();
        cliArgsCap.add("--web-security=false");
        cliArgsCap.add("--ssl-protocol=any");
        cliArgsCap.add("--ignore-ssl-errors=true");
        if(proxy!=null){
            cliArgsCap.add("--proxy=http://"+proxy.getHost()+":"+proxy.getPort());
            cliArgsCap.add("--proxy-auth=" + proxy.getUsername() + ":" + proxy.getPassword());
            cliArgsCap.add("--proxy-type=sock5");
        }

        if (System.getProperty(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY) == null) {
            if (sConfig.getProperty("phantomjs_exec_path") != null) {
                capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, sConfig.getProperty("phantomjs_exec_path"));
            } else {
                throw new IllegalArgumentException(String.format("Property '%s' not set!", PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY));
            }
        }
        // "phantomjs_driver_path"
        if (System.getProperty(PhantomJSDriverService.PHANTOMJS_GHOSTDRIVER_PATH_PROPERTY) == null) {
            if (sConfig.getProperty("phantomjs_driver_path") != null) {
                capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_GHOSTDRIVER_PATH_PROPERTY, sConfig.getProperty("phantomjs_driver_path"));
            } else {
                System.out.println("Test will use PhantomJS internal GhostDriver");
            }
        }

        // Control LogLevel for GhostDriver, via CLI arguments
        capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_GHOSTDRIVER_CLI_ARGS,
                new String[]{"--logLevel=" + (sConfig.getProperty("phantomjs_driver_loglevel") != null ? sConfig.getProperty("phantomjs_driver_loglevel") : "INFO")});
        capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, cliArgsCap);
        return capabilities;
    }

    private DesiredCapabilities getChromeCapabilities(Proxy proxy) throws IOException {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        if (System.getProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY) == null) {
            if (sConfig.getProperty("chrome_driver_path") != null) {
                capabilities.setCapability(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, sConfig.getProperty("chrome_driver_path"));
            } else {
                throw new IllegalArgumentException(String.format("Property '%s' not set!", ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY));
            }
        }
        if (System.getProperty("webdriver.chrome.bin") == null) {
            if (sConfig.getProperty("chrome_exec_path") != null) {
                System.setProperty("webdriver.chrome.bin", sConfig.getProperty("chrome_exec_path"));
            } else {
                throw new IllegalArgumentException("Property chrome_exec_path not set!");
            }
        }

        ChromeOptions options = new ChromeOptions();
        if (proxy != null) {
            options.addArguments("--proxy-server=socks5://" + proxy.getHost() + ":" + proxy.getPort());
        }
        options.addArguments("start-maximized");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        return capabilities;
    }

    private void setCommonCapabilities(DesiredCapabilities capabilities) {
        capabilities.setJavascriptEnabled(true);
        capabilities.setCapability("takesScreenshot", true);
        capabilities.setCapability("screen-resolution", "1280x1024");
    }

    /**
     * check whether input is a valid URL
     *
     * @param urlString urlString
     * @return true means yes, otherwise no.
     * @author bob.li.0718@gmail.com
     */
    private  boolean isUrl(String urlString) {
        try {
            new URL(urlString);
            return true;
        } catch (MalformedURLException mue) {
            return false;
        }
    }
}
