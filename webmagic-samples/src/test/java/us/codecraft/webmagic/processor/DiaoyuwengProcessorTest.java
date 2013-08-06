package us.codecraft.webmagic.processor;

import org.junit.Ignore;
import org.junit.Test;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.pipeline.FreemarkerPipeline;
import us.codecraft.webmagic.samples.DiaoyuwengProcessor;
import us.codecraft.webmagic.scheduler.FileCacheQueueScheduler;

import java.io.IOException;

/**
 * @author code4crafter@gmail.com <br>
 *         Date: 13-6-9
 *         Time: 上午8:02
 */
public class DiaoyuwengProcessorTest {

    @Ignore
    @Test
    public void test() throws IOException {
        DiaoyuwengProcessor diaoyuwengProcessor = new DiaoyuwengProcessor();
        FreemarkerPipeline pipeline = new FreemarkerPipeline("wordpress.ftl");
        Spider.create(diaoyuwengProcessor).pipeline(new FilePipeline()).pipeline(pipeline).scheduler(new FileCacheQueueScheduler("/data/temp/webmagic/cache/")).
                run();
    }
}
