package us.codecraft.webmagic.downloader.selenium;

import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class WebDriverGeneratorTest {
    @Test
    public void test() {
        WebDriverGenerator generator=new WebDriverGenerator();
        try {
            WebDriver browser=generator.generateWebDriver(null);
            browser.get("http://www.baidu.com");
            System.out.println(browser.getPageSource());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
