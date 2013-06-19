package us.codecraft.webmagic;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.schedular.QueueScheduler;
import us.codecraft.webmagic.schedular.Scheduler;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * webmagic爬虫的入口类。
 *      示例：
 *      Spider.create(new SimplePageProcessor("http://my.oschina.net/", "http://my.oschina.net/*blog/*")).run();
 * </pre>
 * @author code4crafter@gmail.com <br>
 * Date: 13-4-21
 * Time: 上午6:53
 */
public class Spider implements Runnable, Task {

    private Downloader downloader = new HttpClientDownloader();

    private List<Pipeline> pipelines = new ArrayList<Pipeline>();

    private PageProcessor pageProcessor;

    private List<String> startUrls;

    private Site site;

    private String uuid;

    private Scheduler scheduler = new QueueScheduler();

    private Logger logger = Logger.getLogger(getClass());

    public Spider(PageProcessor pageProcessor){
        this.pageProcessor = pageProcessor;
        this.site = pageProcessor.getSite();
    }

    public static Spider create(PageProcessor pageProcessor) {
        return new Spider(pageProcessor);
    }

    public Spider startUrls(List<String> startUrls) {
        this.startUrls = startUrls;
        return this;
    }

    public Spider startUrl(String startUrl) {
        startUrls = new ArrayList<String>();
        startUrls.add(startUrl);
        return this;
    }

    public Spider setUUID(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public Spider scheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
        return this;
    }

    public Spider pipeline(Pipeline pipeline) {
        this.pipelines.add(pipeline);
        return this;
    }


    @Override
    public void run() {
        for (String startUrl : startUrls) {
            scheduler.push(new Request(startUrl), this);
        }
        Request request = scheduler.poll(this);
        if (pipelines.isEmpty()) {
            pipelines.add(new ConsolePipeline());
        }
        while (request != null) {
            Page page = downloader.download(request, site);
            if (page == null) {
                sleep(site.getSleepTime());
                continue;
            }
            pageProcessor.process(page);
            addRequest(page);
            for (Pipeline pipeline : pipelines) {
                pipeline.process(page, this);
            }
            sleep(site.getSleepTime());
            request = scheduler.poll(this);
        }
    }

    private void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void addRequest(Page page) {
        if (CollectionUtils.isNotEmpty(page.getTargetRequests())) {
            for (Request request : page.getTargetRequests()) {
                scheduler.push(request, this);
            }
        }
    }

    @Override
    public String getUUID() {
        if (uuid != null) {
            return uuid;
        }
        if (site != null) {
            return site.getDomain();
        }
        return null;
    }
}
