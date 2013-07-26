package us.codecraft.webmagic.selenium;

import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @author yihua.huang@dianping.com <br>
 * @date: 13-7-26 <br>
 * Time: 下午12:27 <br>
 */
public class SeleniumTest {

    @Ignore("need chrome driver")
    @Test
    public void testSelenium() {
        System.getProperties().setProperty("webdriver.chrome.driver", "/Users/yihua/Downloads/chromedriver");
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("http://huaban.com/");
        WebElement webElement = webDriver.findElement(By.xpath("/html"));
        System.out.println(webElement.getAttribute("outerHTML"));
        webDriver.close();
    }
}
