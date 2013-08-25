package us.codecraft.webmagic.selector;

import junit.framework.Assert;
import org.junit.Ignore;
import org.junit.Test;
import us.codecraft.webmagic.downloader.HttpClientDownloader;

/**
 * @author code4crafter@gmail.com <br>
 * @since 0.2.2
 */
public class TextContentSelectorTest {

    @Test
    public void test() {
        String html = "<div class=\"edit-comment-hide\">\n" +
                "            <div class=\"js-comment-body comment-body markdown-body markdown-format\">\n" +
                "                <p>Add more powerful selector for content text extract refered to <a href=\"http://www.elias.cn/En/ExtMainText\">http://www.elias.cn/En/ExtMainText</a></p>\n" +
                "            </div>\n" +
                "          </div>";
        TextContentSelector textContentSelector = new TextContentSelector("<br>");
        String text = textContentSelector.select(html);
        Assert.assertNotNull(text);
    }

    @Ignore("takes long time")
    @Test
    public void testDownload() {
        String s = new HttpClientDownloader().download("http://blog.codecraft.us/blog/2013/08/18/ti-yan-dao-liao-open-sourcede-mei-li/", "utf-8")
                .smartContent().text().toString();
        Assert.assertNotNull(text);
    }

}
