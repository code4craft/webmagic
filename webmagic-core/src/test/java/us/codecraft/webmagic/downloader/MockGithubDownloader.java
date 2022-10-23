package us.codecraft.webmagic.downloader;


import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import org.apache.commons.io.IOUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.selector.PlainText;


/**
 * @author code4crafter@gmail.com
 */
public class MockGithubDownloader implements Downloader {

    @Override
    public Page download(Request request, Task task) {
        Page page = new Page();
        InputStream resourceAsStream = this.getClass().getResourceAsStream("/html/mock-github.html");
        try {
            page.setRawText(IOUtils.toString(resourceAsStream, Charset.defaultCharset()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        page.setRequest(new Request("https://github.com/code4craft/webmagic"));
        page.setUrl(new PlainText("https://github.com/code4craft/webmagic"));
        return page;
    }

    @Override
    public void setThread(int threadNum) {
    }
}
