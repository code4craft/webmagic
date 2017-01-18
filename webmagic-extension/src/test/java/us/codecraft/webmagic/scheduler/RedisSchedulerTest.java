package us.codecraft.webmagic.scheduler;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import us.codecraft.webmagic.*;
import us.codecraft.webmagic.monitor.SpiderMonitor;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.SimplePageProcessor;

/**
 * @author code4crafter@gmail.com <br>
 */
public class RedisSchedulerTest {

    private RedisScheduler redisScheduler;

    @Before
    public void setUp() {
        redisScheduler = new RedisScheduler("localhost");
    }

    @Ignore("environment depended")
    @Test
    public void test() {
        Task task = new Task() {
            @Override
            public String getUUID() {
                return "1";
            }

            @Override
            public Site getSite() {
                return null;
            }
        };
        Request request = new Request("http://www.ibm.com/developerworks/cn/java/j-javadev2-22/");
        request.putExtra("1","2");
        redisScheduler.push(request, task);
        Request poll = redisScheduler.poll(task);
        System.out.println(poll);

    }

    @Ignore("long time")
    @Test
    public void testStartAndStop() throws Exception {
        SimplePageProcessor pageProcessor = new SimplePageProcessor("http://www.oschina.net/", "http://www.oschina.net/*");
//        Pipeline pipeline = new ConsolePipeline();
        Pipeline pipeline = new Pipeline() {
            @Override
            public void process(ResultItems resultItems, Task task) {
//                System.out.println(resultItems);
                System.out.println(resultItems.get("title"));
            }
        };


        JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), "127.0.0.1", 6379, 2000, "123456");
        Scheduler scheduler = new RedisScheduler(jedisPool);

        Spider spider = Spider
                .create(pageProcessor)
                .addPipeline(pipeline)
                .setScheduler(scheduler)
                .thread(6);

        SpiderMonitor.instance().register(spider);

        spider.run();

//        spider.start();
//        Thread.sleep(10000);
//        spider.stop();
//        Thread.sleep(10000);
//        spider.start();
//        Thread.sleep(10000);
    }
}
