package us.codecraft.webmagic.samples;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.SimplePageProcessor;
import us.codecraft.webmagic.schedular.FileCacheQueueScheduler;

/**
 * @author yihua.huang@dianping.com <br>
 * @date: 13-7-14 <br>
 * Time: 上午8:33 <br>
 */
public class GuoxueProcessor {

    public static void main(String[] args) {
        SimplePageProcessor simplePageProcessor = new SimplePageProcessor("http://www.guoxue123.cn/", "http://www.guoxue123.cn/*");
        simplePageProcessor.getSite().setCharset("GBK").setSleepTime(500);
        Spider.create(simplePageProcessor).pipeline(new FilePipeline("/data/webmagic/")).scheduler(new FileCacheQueueScheduler("/data/webmagic/")).run();
    }
}
