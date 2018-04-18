package us.codecraft.webmagic.samples.scheduler;

import java.util.concurrent.TimeUnit;
import org.junit.Ignore;
import org.junit.Test;
import us.codecraft.webmagic.Request;

/**
 * @author code4crafter@gmail.com
 */
public class DelayQueueSchedulerTest {

    @Ignore("infinite")
    @Test
    public void test() {
        DelayQueueScheduler delayQueueScheduler = new DelayQueueScheduler(1, TimeUnit.SECONDS);
        delayQueueScheduler.push(new Request("1"), null);
        while (true) {
            Request poll = delayQueueScheduler.poll(null);
            System.out.println(System.currentTimeMillis() + "\t" + poll);
        }
    }
}
