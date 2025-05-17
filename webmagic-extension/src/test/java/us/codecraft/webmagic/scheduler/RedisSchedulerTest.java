package us.codecraft.webmagic.scheduler;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author code4crafter@gmail.com <br>
 */
public class RedisSchedulerTest {

    private RedisScheduler redisScheduler;
    private Task task;

    @Before
    public void setUp() {
        redisScheduler = new RedisScheduler("localhost");

        task = new Task() {
            @Override
            public String getUUID() {
                return "1";
            }

            @Override
            public Site getSite() {
                return null;
            }
        };
    }

    @Ignore("environment depended")
    @Test
    public void test() {
        Request request = new Request("http://www.ibm.com/developerworks/cn/java/j-javadev2-22/");
        request.putExtra("1","2");
        redisScheduler.push(request, task);
        Request poll = redisScheduler.poll(task);
        assertThat(poll).isEqualTo(request);

    }

    @Ignore("environment depended")
    @Test
    public void testFlush() {
        Request request = new Request("http://www.baidu.com");
        request.putExtra("1","2");
        redisScheduler.push(request, task);
        int totalRequestsCount = redisScheduler.getTotalRequestsCount(task);
        assertThat(totalRequestsCount).isGreaterThan(0);
        redisScheduler.flush(task);
        totalRequestsCount = redisScheduler.getTotalRequestsCount(task);
        assertThat(totalRequestsCount).isEqualTo(0);
        int leftRequestsCount = redisScheduler.getLeftRequestsCount(task);
        assertThat(leftRequestsCount).isEqualTo(0);

    }
}
