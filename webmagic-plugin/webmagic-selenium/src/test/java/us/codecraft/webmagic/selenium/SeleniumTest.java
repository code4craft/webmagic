package us.codecraft.webmagic.selenium;

import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

/**
 * @author yihua.huang@dianping.com <br>
 * @date: 13-7-26 <br>
 * Time: 下午12:27 <br>
 */
public class SeleniumTest {

    @Ignore("need chrome driver")
    @Test
    public void test(){
        System.getProperties().setProperty("webdriver.chrome.driver","/Users/yihua/Downloads/chromedriver");
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("http://huaban.com/");
        List<WebElement> elements = webDriver.findElements(By.xpath("/html"));
        for (WebElement element : elements) {
            System.out.println(element.getAttribute("outerHTML"));
        }
        webDriver.close();
    }
}
