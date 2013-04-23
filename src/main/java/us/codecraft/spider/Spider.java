package us.codecraft.spider;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import us.codecraft.spider.downloader.Downloader;
import us.codecraft.spider.downloader.HttpClientDownloader;
import us.codecraft.spider.pipeline.ConsolePipeline;
import us.codecraft.spider.pipeline.Pipeline;
import us.codecraft.spider.processor.PageProcessor;
import us.codecraft.spider.schedular.QueueSchedular;
import us.codecraft.spider.schedular.Schedular;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * User: cairne
 * Date: 13-4-21
 * Time: 上午6:53
 */
public class Spider implements Runnable {

    private Downloader downloader = new HttpClientDownloader();

    private Pipeline pipeline = new ConsolePipeline();

    private PageProcessor pageProcessor;

    private Schedular schedular = new QueueSchedular();

    private Logger logger = Logger.getLogger(getClass());

    public static Spider me() {
        return new Spider();
    }

    public Spider processor(PageProcessor pageProcessor) {
        this.pageProcessor = pageProcessor;
        schedular.push(new Request(pageProcessor.getSite().getStartUrl()), pageProcessor.getSite());
        return this;
    }

    public Thread thread() {
        return new Thread(this);
    }

    public Spider schedular(Schedular schedular) {
        this.schedular = schedular;
        return this;
    }

    public Spider pipeline(Pipeline pipeline) {
        this.pipeline = pipeline;
        return this;
    }


    @Override
    public void run() {
        Site site = pageProcessor.getSite();
        Request request = schedular.poll(site);
        while (request != null) {
            Page page = downloader.download(request,site);
            if (page == null) {
                sleep(site.getSleepTime());
                continue;
            }
            pageProcessor.process(page);
            addRequest(page);
            pipeline.process(page,site);
            sleep(site.getSleepTime());
            request = schedular.poll(site);
        }
    }

    private void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
            ;
        }
    }

    private void addRequest(Page page) {
        if (CollectionUtils.isNotEmpty(page.getTargetRequests())) {
            for (Request request : page.getTargetRequests()) {
                schedular.push(request,pageProcessor.getSite());
            }
        }
    }
}
