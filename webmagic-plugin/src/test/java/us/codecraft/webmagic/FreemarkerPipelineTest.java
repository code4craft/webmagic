package us.codecraft.webmagic;

import org.junit.Test;
import us.codecraft.webmagic.pipeline.FreemarkerPipeline;

import java.io.IOException;

/**
 * @author code4crafter@gmail.com <br>
 * Date: 13-6-9
 * Time: 上午7:14
 */
public class FreemarkerPipelineTest {

    @Test
    public void testTemplateLoad() throws IOException {
        new FreemarkerPipeline("wordpress.ftl");
    }
}
