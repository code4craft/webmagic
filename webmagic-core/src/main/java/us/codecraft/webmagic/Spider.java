package us.codecraft.webmagic;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.scheduler.Scheduler;
import us.codecraft.webmagic.utils.EnvironmentUtil;
import us.codecraft.webmagic.utils.ThreadUtils;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Entrance of a crawler.<br>
 * A spider contains four modules: Downloader, Scheduler, PageProcessor and Pipeline.<br>
 * Every module is a field of Spider.                                                    <br>
 * The modules are defined in interface.                                                     <br>
 * You can customize a spider with various implementations of them.                              <br>
 * Examples:                                                                                         <br>
 * <br>
 * A simple crawler:                                                                                         <br>
 * Spider.create(new SimplePageProcessor("http://my.oschina.net/", "http://my.oschina.net/*blog/*")).run();<br>
 * <br>
 * Store results to files by FilePipeline:                                                                              <br>
 * Spider.create(new SimplePageProcessor("http://my.oschina.net/", "http://my.oschina.net/*blog/*"))                   <br>
 * .pipeline(new FilePipeline("/data/temp/webmagic/")).run();                                                          <br>
 * <br>
 * Use FileCacheQueueScheduler to store urls and cursor in files, so that a Spider can resume the status when shutdown.                 <br>
 * Spider.create(new SimplePageProcessor("http://my.oschina.net/", "http://my.oschina.net/*blog/*"))                                   <br>
 * .scheduler(new FileCacheQueueScheduler("/data/temp/webmagic/cache/")).run();                                                        <br>
 *
 * @author code4crafter@gmail.com <br>
 * @see Downloader
 * @see Scheduler
 * @see PageProcessor
 * @see Pipeline
 * @since 0.1.0
 */
public class Spider implements Runnable, Task {

    protected Downloader downloader;

    protected List<Pipeline> pipelines = new ArrayList<Pipeline>();

    protected PageProcessor pageProcessor;

    protected List<String> startUrls;

    protected Site site;

    protected String uuid;

    protected Scheduler scheduler = new QueueScheduler();

    protected Logger logger = Logger.getLogger(getClass());

    protected ExecutorService executorService;

    protected int threadNum = 1;

    protected AtomicInteger stat = new AtomicInteger(STAT_INIT);

    protected final static int STAT_INIT = 0;

    protected final static int STAT_RUNNING = 1;

    protected final static int STAT_STOPPED = 2;

    /**
     * create a spider with pageProcessor.
     *
     * @param pageProcessor
     */
    public Spider(PageProcessor pageProcessor) {
        this.pageProcessor = pageProcessor;
        this.site = pageProcessor.getSite();
        this.startUrls = pageProcessor.getSite().getStartUrls();
    }

    /**
     * create a spider with pageProcessor.
     *
     * @param pageProcessor
     * @return new spider
     * @see PageProcessor
     */
    public static Spider create(PageProcessor pageProcessor) {
        return new Spider(pageProcessor);
    }

    /**
     * Set startUrls of Spider.<br>
     * Prior to startUrls of Site.
     *
     * @param startUrls
     * @return this
     */
    public Spider startUrls(List<String> startUrls) {
        checkIfNotRunning();
        this.startUrls = startUrls;
        return this;
    }

    /**
     * Set an uuid for spider.<br>
     * Default uuid is domain of site.<br>
     *
     * @param uuid
     * @return this
     */
    public Spider setUUID(String uuid) {
        this.uuid = uuid;
        return this;
    }

    /**
     * set scheduler for Spider
     *
     * @param scheduler
     * @return this
     * @Deprecated
     * @see #setScheduler(us.codecraft.webmagic.scheduler.Scheduler)
     */
    public Spider scheduler(Scheduler scheduler) {
        return setScheduler(scheduler);
    }

    /**
     * set scheduler for Spider
     *
     * @param scheduler
     * @return this
     * @since 0.2.1
     * @see Scheduler
     */
    public Spider setScheduler(Scheduler scheduler) {
        checkIfNotRunning();
        this.scheduler = scheduler;
        return this;
    }

    /**
     * add a pipeline for Spider
     *
     * @param pipeline
     * @return this
     * @deprecated
     * @see #setPipeline(us.codecraft.webmagic.pipeline.Pipeline)
     */
    public Spider pipeline(Pipeline pipeline) {
        return addPipeline(pipeline);
    }

    /**
     * add a pipeline for Spider
     *
     * @param pipeline
     * @return this
     * @since 0.2.1
     * @see Pipeline
     */
    public Spider addPipeline(Pipeline pipeline) {
        checkIfNotRunning();
        this.pipelines.add(pipeline);
        return this;
    }

    /**
     * clear the pipelines set
     *
     * @return this
     */
    public Spider clearPipeline() {
        pipelines = new ArrayList<Pipeline>();
        return this;
    }

    /**
     * set the downloader of spider
     *
     * @param downloader
     * @return this
     * @deprecated
     * @see #setDownloader(us.codecraft.webmagic.downloader.Downloader)
     */
    public Spider downloader(Downloader downloader) {
        return setDownloader(downloader);
    }

    /**
     * set the downloader of spider
     * @see Downloader
     * @param downloader
     * @return this
     */
    public Spider setDownloader(Downloader downloader) {
        checkIfNotRunning();
        this.downloader = downloader;
        return this;
    }

    protected void checkComponent() {
        if (downloader == null) {
            this.downloader = new HttpClientDownloader();
        }
        if (pipelines.isEmpty()) {
            pipelines.add(new ConsolePipeline());
        }
        downloader.setThread(threadNum);
    }

    @Override
    public void run() {
        if (!stat.compareAndSet(STAT_INIT, STAT_RUNNING)) {
            throw new IllegalStateException("Spider is already running!");
        }
        checkComponent();
        if (startUrls != null) {
            for (String startUrl : startUrls) {
                scheduler.push(new Request(startUrl), this);
            }
        }
        Request request = scheduler.poll(this);
        //singel thread
        if (executorService == null) {
            while (request != null) {
                processRequest(request);
                request = scheduler.poll(this);
            }
        } else {
            //multi thread
            final AtomicInteger threadAlive = new AtomicInteger(0);
            while (true) {
                if (request == null) {
                    //when no request found but some thread is alive, sleep a while.
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                    }
                } else {
                    final Request requestFinal = request;
                    threadAlive.incrementAndGet();
                    executorService.execute(new Runnable() {
                        @Override
                        public void run() {
                            processRequest(requestFinal);
                            threadAlive.decrementAndGet();
                        }
                    });
                }
                request = scheduler.poll(this);
                if (threadAlive.get() == 0) {
                    request = scheduler.poll(this);
                    if (request == null) {
                        break;
                    }
                }
            }
            executorService.shutdown();
        }
        stat.compareAndSet(STAT_RUNNING, STAT_STOPPED);
        //release some resources
        destroy();
    }

    protected void destroy() {
        destroyEach(downloader);
        destroyEach(pageProcessor);
        for (Pipeline pipeline : pipelines) {
            destroyEach(pipeline);
        }
    }

    private void destroyEach(Object object) {
        if (object instanceof Closeable) {
            try {
                ((Closeable) object).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Process specific urls without url discovering.
     *
     * @param urls urls to process
     */
    public void test(String... urls) {
        checkComponent();
        if (urls.length > 0) {
            for (String url : urls) {
                processRequest(new Request(url));
            }
        }
    }

    protected void processRequest(Request request) {
        Page page = downloader.download(request, this);
        if (page == null) {
            sleep(site.getSleepTime());
            return;
        }
        //for cycle retry
        if (page.getHtml()==null){
            addRequest(page);
            sleep(site.getSleepTime());
            return;
        }
        pageProcessor.process(page);
        addRequest(page);
        if (!page.getResultItems().isSkip()) {
            for (Pipeline pipeline : pipelines) {
                pipeline.process(page.getResultItems(), this);
            }
        }
        sleep(site.getSleepTime());
    }

    protected void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void addRequest(Page page) {
        if (CollectionUtils.isNotEmpty(page.getTargetRequests())) {
            for (Request request : page.getTargetRequests()) {
                scheduler.push(request, this);
            }
        }
    }

    protected void checkIfNotRunning() {
        if (!stat.compareAndSet(STAT_INIT, STAT_INIT)) {
            throw new IllegalStateException("Spider is already running!");
        }
    }

    public void runAsync() {
        Thread thread = new Thread(this);
        thread.setDaemon(false);
        thread.start();
    }

    /**
     * start with more than one threads
     *
     * @param threadNum
     * @return this
     */
    public Spider thread(int threadNum) {
        checkIfNotRunning();
        this.threadNum = threadNum;
        if (threadNum <= 0) {
            throw new IllegalArgumentException("threadNum should be more than one!");
        }
        if (threadNum == 1) {
            return this;
        }
        synchronized (this) {
            this.executorService = ThreadUtils.newFixedThreadPool(threadNum);
        }
        return this;
    }

    /**
     * switch off xsoup
     * @return
     */
    public static void xsoupOff(){
        EnvironmentUtil.setUseXsoup(false);
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

    @Override
    public Site getSite() {
        return site;
    }
}
