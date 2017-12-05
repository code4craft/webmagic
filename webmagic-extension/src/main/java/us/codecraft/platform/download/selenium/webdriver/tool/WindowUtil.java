package us.codecraft.platform.download.selenium.webdriver.tool;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 修复窗口滚动加载工具类
 *
 * @author 刘太信
 * @date 2017年7月29日 下午7:49:24
 */
public class WindowUtil {
    /**
     * 滚动窗口。
     *
     * @param driver 驱动
     * @param height 高度
     */
    public static void scroll(WebDriver driver, int height) {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0," + height + " );");
    }

    /**
     * 重新调整窗口大小，以适应页面，需要耗费一定时间。建议等待合理的时间。
     *
     * @param driver 驱动
     */
    public static void loadAll(WebDriver driver) {
        Dimension od = driver.manage().window().getSize();
        int width = od.width;
        // 尝试性解决：https://github.com/ariya/phantomjs/issues/11526问题
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        long height = (Long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight;");
        driver.manage().window().setSize(new Dimension(width, (int) height));
        driver.navigate().refresh();
    }

    /**
     * 截图
     *
     * @param driver   驱动
     * @param saveFile 保存的文件
     * @author 刘太信
     * @date 2017年7月29日 下午7:50:08
     */
    public static void taskScreenShot(WebDriver driver, File saveFile) {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(src, saveFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 调整窗口
     *
     * @param driver 驱动
     * @author 刘太信
     * @date 2017年7月29日 下午7:50:18
     */
    public static void changeWindow(WebDriver driver) {
        // 获取当前页面句柄
        String handle = driver.getWindowHandle();
        // 获取所有页面的句柄，并循环判断不是当前的句柄，就做选取switchTo()
        for (String handles : driver.getWindowHandles()) {
            if (handles.equals(handle))
                continue;
            driver.switchTo().window(handles);
        }
    }
}
