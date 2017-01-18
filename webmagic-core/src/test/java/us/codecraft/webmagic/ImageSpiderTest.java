package us.codecraft.webmagic;

import org.jsoup.Jsoup;
import org.junit.Ignore;
import org.junit.Test;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.scheduler.Scheduler;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author code4crafter@gmail.com
 */
public class ImageSpiderTest {

    @Ignore("long time")
    @Test
    public void testStartAndStop() throws InterruptedException {
//        PageProcessor pageProcessor = new ImagePageProcessor("http://www.oschina.net/", "http://www.oschina.net/*");
        PageProcessor pageProcessor = new PageProcessor() {
            @Override
            public void process(Page page) {
                if (page.getRequest().getType() == Request.Type.TEXT) {
                    page.putField("title", page.getHtml().xpath("//title"));

                    String url = Jsoup.parse(page.getRawText(), page.getRequest().getUrl()).select("a.pdtname img").first().absUrl("src");
                    page.addTargetRequest(url, Request.Type.BYTES);
                }
                else {
                    page.putField("bytes", page.getRawBytes());
                }
            }
            @Override
            public Site getSite() {
                return null;
            }
        };
        Pipeline pipeline = new Pipeline() {
            @Override
            public void process(ResultItems resultItems, Task task) {
                System.out.println(resultItems.get("title"));

                byte[] bytes = resultItems.get("bytes");
                System.out.println(bytes);
            }
        };

        Scheduler scheduler = new QueueScheduler();

        Spider spider = Spider
                .create(pageProcessor)
                .addPipeline(pipeline)
                .setScheduler(scheduler)
                .thread(6);

        spider.run();
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
