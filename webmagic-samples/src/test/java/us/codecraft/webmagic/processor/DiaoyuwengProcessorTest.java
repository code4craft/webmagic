package us.codecraft.webmagic.processor;

import org.junit.Ignore;
import org.junit.Test;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.pipeline.FreemarkerPipeline;
import us.codecraft.webmagic.samples.DiaoyuwengProcessor;
import us.codecraft.webmagic.schedular.FileCacheQueueSchedular;

import java.io.IOException;

/**
 * Author: code4crafter@gmail.com
 * Date: 13-6-9
 * Time: 上午8:02
 */
public class DiaoyuwengProcessorTest {

    @Ignore
    @Test
    public void test() throws IOException {
        DiaoyuwengProcessor diaoyuwengProcessor = new DiaoyuwengProcessor();
        FreemarkerPipeline pipeline = new FreemarkerPipeline("wordpress.ftl");
        Spider.me().pipeline(new FilePipeline()).pipeline(pipeline).schedular(new FileCacheQueueSchedular(diaoyuwengProcessor.getSite(), "/data/temp/webmagic/cache/")).
                processor(diaoyuwengProcessor).run();
    }
}
