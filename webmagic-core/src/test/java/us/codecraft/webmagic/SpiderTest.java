package us.codecraft.webmagic;

import org.junit.Ignore;
import org.junit.Test;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.processor.SimplePageProcessor;
import us.codecraft.webmagic.scheduler.Scheduler;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author code4crafter@gmail.com
 */
public class SpiderTest {

    @Ignore("long time")
    @Test
    public void testStartAndStop() throws InterruptedException {
        Spider spider = Spider.create(new SimplePageProcessor( "http://www.oschina.net/*")).addPipeline(new Pipeline() {
            @Override
            public void process(ResultItems resultItems, Task task) {
                System.out.println(1);
            }
        }).thread(1).addUrl("http://www.oschina.net/");
        spider.start();
        Thread.sleep(10000);
        spider.stop();
        Thread.sleep(10000);
        spider.start();
        Thread.sleep(10000);
    }

    @Ignore("long time")
    @Test
    public void testWaitAndNotify() throws InterruptedException {
        for (int i = 0; i < 10000; i++) {
            System.out.println("round " + i);
            testRound();
        }
    }

    private void testRound() {
        Spider spider = Spider.create(new PageProcessor() {

            private AtomicInteger count = new AtomicInteger();

            @Override
            public void process(Page page) {
                page.setSkip(true);
            }

            @Override
            public Site getSite() {
                return Site.me().setSleepTime(0);
            }
        }).setDownloader(new Downloader() {
            @Override
            public Page download(Request request, Task task) {
                return new Page().setRawText("");
            }

            @Override
            public void setThread(int threadNum) {

            }
        }).setScheduler(new Scheduler() {

            private AtomicInteger count = new AtomicInteger();

            private Random random = new Random();

            @Override
            public void push(Request request, Task task) {

            }

            @Override
            public synchronized Request poll(Task task) {
                if (count.incrementAndGet() > 1000) {
                    return null;
                }
                if (random.nextInt(100)>90){
                    return null;
                }
                return new Request("test");
            }
        }).thread(10);
        spider.run();
    }
}
