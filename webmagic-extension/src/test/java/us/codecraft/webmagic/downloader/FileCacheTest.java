package us.codecraft.webmagic.downloader;

import org.junit.Ignore;
import org.junit.Test;
import us.codecraft.webmagic.Spider;

/**
 * @author code4crafter@gmail.com <br>
 */
public class FileCacheTest {

    @Ignore("takes long")
    @Test
    public void test() {
        FileCache fileCache = new FileCache("http://my.oschina.net/flashsword/blog", "http://my.oschina.net/flashsword/blog/*");
        Spider.create(fileCache).downloader(fileCache).pipeline(fileCache).run();
    }
}
