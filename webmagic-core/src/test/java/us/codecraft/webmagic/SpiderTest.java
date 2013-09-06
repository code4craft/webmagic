package us.codecraft.webmagic;

import org.junit.Ignore;
import org.junit.Test;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.SimplePageProcessor;

/**
 * @author code4crafter@gmail.com
 */
public class SpiderTest {

    @Ignore("long time")
    @Test
    public void testStartAndStop() throws InterruptedException {
        Spider spider = Spider.create(new SimplePageProcessor("http://www.oschina.net/", "http://www.oschina.net/*")).addPipeline(new Pipeline() {
            @Override
            public void process(ResultItems resultItems, Task task) {
                System.out.println(1);
            }
        });
        spider.start();
        Thread.sleep(10000);
        spider.stop();
//        spider.run();
        Thread.sleep(10000);
    }
}
