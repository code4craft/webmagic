package us.codecraft.webmagic.recover;


import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.samples.SinaBlogProcessor;
import us.codecraft.webmagic.scheduler.component.DuplicateRemover;

/**
 * @author code4crafter@gmail.com <br>
 */
public class RecoverSample {

    public static void main(String[] args) {
        String storage = "queue";
        String duplicate = "duplicate";
        Spider spider = new Spider(new SinaBlogProcessor());
        DuplicateRemover remover = new DuplicateStorageRemover(duplicate);
        spider.setScheduler(new MmapQueueScheduler(remover, storage));
        spider.addUrl("http://blog.sina.com.cn/s/articlelist_1487828712_0_1.html")
                .run();
    }
}
