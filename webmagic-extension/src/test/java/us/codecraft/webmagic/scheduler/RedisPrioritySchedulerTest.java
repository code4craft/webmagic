package us.codecraft.webmagic.scheduler;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;

/**
 * @author sai
 * Created by sai on 16-7-5.
 */
public class RedisPrioritySchedulerTest
{

    private RedisPriorityScheduler scheduler;

    @Before
    public void setUp()
    {
        scheduler = new RedisPriorityScheduler("localhost");
    }

    @Ignore("environment depended")
    @Test
    public void test()
    {
        Task task = new Task() {
            @Override
            public String getUUID() {
                return "TestTask";
            }

            @Override
            public Site getSite() {
                return null;
            }
        };

        scheduler.resetDuplicateCheck(task);

        Request request = new Request("https://www.google.com");
        Request request1= new Request("https://www.facebook.com/");
        Request request2= new Request("https://twitter.com");

        request.setPriority(1).putExtra("name", "google");
        request1.setPriority(0).putExtra("name", "facebook");
        request2.setPriority(-1).putExtra("name", "twitter");

        scheduler.push(request, task);
        scheduler.push(request1, task);
        scheduler.push(request2, task);

        Request GRequest    = scheduler.poll(task);
        Request FBRequest   = scheduler.poll(task);
        Request TRequest    = scheduler.poll(task);

        Assert.assertEquals(GRequest.getUrl(), request.getUrl());
        Assert.assertEquals(GRequest.getExtra("name"), request.getExtra("name"));

        Assert.assertEquals(FBRequest.getUrl(), request1.getUrl());
        Assert.assertEquals(FBRequest.getExtra("name"), request.getExtra("name"));

        Assert.assertEquals(TRequest.getUrl(), request2.getUrl());
        Assert.assertEquals(TRequest.getExtra("name"), request.getExtra("name"));
    }

}
