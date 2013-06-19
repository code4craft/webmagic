package us.codecraft.webmagic.processor;

import org.junit.Ignore;
import org.junit.Test;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.FreemarkerPipeline;
import us.codecraft.webmagic.samples.DiandianBlogProcessor;
import us.codecraft.webmagic.schedular.FileCacheQueueScheduler;

import java.io.IOException;

/**
 * @author code4crafter@gmail.com <br>
 * Date: 13-6-9
 * Time: 上午8:02
 */
public class DiandianProcessorTest {

    @Ignore
    @Test
    public void test() throws IOException {
        DiandianBlogProcessor diaoyuwengProcessor = new DiandianBlogProcessor();
        //pipeline是抓取结束后的处理
        //ftl文件放到classpath:ftl/文件夹下
        //默认放到/data/temp/webmagic/ftl/[domain]目录下
        FreemarkerPipeline pipeline = new FreemarkerPipeline("wordpress.ftl");
        //Spider.me()是简化写法，其实就是new一个啦
        //Spider.pipeline()设定一个pipeline，支持链式调用
        //ConsolePipeline输出结果到控制台
        //FileCacheQueueSchedular保存url，支持断点续传，临时文件输出到/data/temp/webmagic/cache目录
        //Spider.run()执行
        Spider.create(diaoyuwengProcessor).pipeline(new ConsolePipeline()).pipeline(pipeline).scheduler(new FileCacheQueueScheduler("/data/temp/webmagic/cache/")).
                run();
    }
}
