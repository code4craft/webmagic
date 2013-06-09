package us.codecraft.webmagic;

import org.junit.Test;
import us.codecraft.webmagic.pipeline.FreemarkerPipeline;

import java.io.IOException;

/**
 * User: cairne
 * Date: 13-6-9
 * Time: 上午7:14
 */
public class FreemarkerPipelineTest {

    @Test
    public void test() throws IOException {
        FreemarkerPipeline freemarkerPipeline = new FreemarkerPipeline("wordpress.ftl");
    }
}
