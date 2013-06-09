package us.codecraft.webmagic;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.schedular.QueueSchedular;
import us.codecraft.webmagic.schedular.Schedular;

import java.util.ArrayList;
import java.util.List;

/**
 * User: cairne
 * Date: 13-4-21
 * Time: 上午6:53
 */
public class Spider implements Runnable {

    private Downloader downloader = new HttpClientDownloader();

    private List<Pipeline> pipelines = new ArrayList<Pipeline>();

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
        this.pipelines.add(pipeline);
        return this;
    }


    @Override
    public void run() {
        Site site = pageProcessor.getSite();
        Request request = schedular.poll(site);
        if (pipelines.isEmpty()){
            pipelines.add(new ConsolePipeline());
        }
        while (request != null) {
            Page page = downloader.download(request,site);
            if (page == null) {
                sleep(site.getSleepTime());
                continue;
            }
            pageProcessor.process(page);
            addRequest(page);
            for (Pipeline pipeline : pipelines) {
                pipeline.process(page,site);
            }
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
