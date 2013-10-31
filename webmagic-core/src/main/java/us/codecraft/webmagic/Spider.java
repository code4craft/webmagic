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
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Entrance of a crawler.<br>
 * A spider contains four modules: Downloader, Scheduler, PageProcessor and
 * Pipeline.<br>
 * Every module is a field of Spider. <br>
 * The modules are defined in interface. <br>
 * You can customize a spider with various implementations of them. <br>
 * Examples: <br>
 * <br>
 * A simple crawler: <br>
 * Spider.create(new SimplePageProcessor("http://my.oschina.net/",
 * "http://my.oschina.net/*blog/*")).run();<br>
 * <br>
 * Store results to files by FilePipeline: <br>
 * Spider.create(new SimplePageProcessor("http://my.oschina.net/",
 * "http://my.oschina.net/*blog/*")) <br>
 * .pipeline(new FilePipeline("/data/temp/webmagic/")).run(); <br>
 * <br>
 * Use FileCacheQueueScheduler to store urls and cursor in files, so that a
 * Spider can resume the status when shutdown. <br>
 * Spider.create(new SimplePageProcessor("http://my.oschina.net/",
 * "http://my.oschina.net/*blog/*")) <br>
 * .scheduler(new FileCacheQueueScheduler("/data/temp/webmagic/cache/")).run(); <br>
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

    protected boolean exitWhenComplete = true;

    protected final static int STAT_INIT = 0;

    protected final static int STAT_RUNNING = 1;

    protected final static int STAT_STOPPED = 2;

    private ReentrantLock newUrlLock = new ReentrantLock();

    private Condition newUrlCondition = newUrlLock.newCondition();

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
     * Set startUrls of Spider.<br>
     * Prior to startUrls of Site.
     *
     * @param startUrls
     * @return this
     */
    public Spider startUrls(List<String> startUrls) {
        checkIfRunning();
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
     * @see Scheduler
     * @since 0.2.1
     */
    public Spider setScheduler(Scheduler scheduler) {
        checkIfRunning();
        this.scheduler = scheduler;
        return this;
    }

    /**
     * add a pipeline for Spider
     *
     * @param pipeline
     * @return this
     * @see #setPipeline(us.codecraft.webmagic.pipeline.Pipeline)
     * @deprecated
     */
    public Spider pipeline(Pipeline pipeline) {
        return addPipeline(pipeline);
    }

    /**
     * add a pipeline for Spider
     *
     * @param pipeline
     * @return this
     * @see Pipeline
     * @since 0.2.1
     */
    public Spider addPipeline(Pipeline pipeline) {
        checkIfRunning();
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
     * @see #setDownloader(us.codecraft.webmagic.downloader.Downloader)
     * @deprecated
     */
    public Spider downloader(Downloader downloader) {
        return setDownloader(downloader);
    }

    /**
     * set the downloader of spider
     *
     * @param downloader
     * @return this
     * @see Downloader
     */
    public Spider setDownloader(Downloader downloader) {
        checkIfRunning();
        this.downloader = downloader;
        return this;
    }

    protected void initComponent() {
        if (downloader == null) {
            this.downloader = new HttpClientDownloader();
        }
        if (pipelines.isEmpty()) {
            pipelines.add(new ConsolePipeline());
        }
        downloader.setThread(threadNum);
        executorService = ThreadUtils.newFixedThreadPool(threadNum);
        if (startUrls != null) {
            for (String startUrl : startUrls) {
                scheduler.push(new Request(startUrl), this);
            }
            startUrls.clear();
        }
    }

    @Override
    public void run() {
        checkRunningStat();
        initComponent();
        logger.info("Spider " + getUUID() + " started!");
        final AtomicInteger threadAlive = new AtomicInteger(0);
        while (!Thread.currentThread().isInterrupted() && stat.get() == STAT_RUNNING) {
            Request request = scheduler.poll(this);
            if (request == null) {
                if (threadAlive.get() == 0 && exitWhenComplete) {
                    break;
                }
                // wait until new url added
                try {
                    newUrlLock.lock();
                    try {
                        newUrlCondition.await();
                    } catch (InterruptedException e) {
                    }
                } finally {
                    newUrlLock.unlock();
                }
            } else {
                final Request requestFinal = request;
                threadAlive.incrementAndGet();
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            processRequest(requestFinal);
                        } catch (Exception e) {
                            logger.error("download " + requestFinal + " error", e);
                        } finally {
                            threadAlive.decrementAndGet();
                            signalNewUrl();
                        }
                    }
                });
            }
        }
        executorService.shutdown();
        stat.set(STAT_STOPPED);
        // release some resources
        destroy();
    }

    private void checkRunningStat() {
        while (true) {
            int statNow = stat.get();
            if (statNow == STAT_RUNNING) {
                throw new IllegalStateException("Spider is already running!");
            }
            if (stat.compareAndSet(statNow, STAT_RUNNING)) {
                break;
            }
        }
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
        initComponent();
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
        // for cycle retry
        if (page.getHtml() == null) {
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
                addRequest(request);
            }
        }
    }

    private void addRequest(Request request) {
        scheduler.push(request, this);

    }

    protected void checkIfRunning() {
        if (stat.get() == STAT_RUNNING) {
            throw new IllegalStateException("Spider is already running!");
        }
    }

    public void runAsync() {
        Thread thread = new Thread(this);
        thread.setDaemon(false);
        thread.start();
    }

    /**
     * Add urls to crawl.<br/>
     *
     * @param urls
     * @return
     */
    public Spider addUrl(String... urls) {
        for (String url : urls) {
            addRequest(new Request(url));
        }
        signalNewUrl();
        return this;
    }

    private void signalNewUrl() {
        try {
            newUrlLock.lock();
            newUrlCondition.signalAll();
        } finally {
            newUrlLock.unlock();
        }
    }

    public void start() {
        runAsync();
    }

    public void stop() {
        if (stat.compareAndSet(STAT_RUNNING, STAT_STOPPED)) {
            logger.info("Spider " + getUUID() + " stop success!");
        } else {
            logger.info("Spider " + getUUID() + " stop fail!");
        }
    }

    /**
     * start with more than one threads
     *
     * @param threadNum
     * @return this
     */
    public Spider thread(int threadNum) {
        checkIfRunning();
        this.threadNum = threadNum;
        if (threadNum <= 0) {
            throw new IllegalArgumentException("threadNum should be more than one!");
        }
        return this;
    }

    /**
     * switch off xsoup
     *
     * @return
     */
    public static void xsoupOff() {
        EnvironmentUtil.setUseXsoup(false);
    }

    public boolean isExitWhenComplete() {
        return exitWhenComplete;
    }

    /**
     * Exit when complete. <br/>
     * True: exit when all url of the site is downloaded. <br/>
     * False: not exit until call stop manually.<br/>
     *
     * @param exitWhenComplete
     * @return
     */
    public Spider setExitWhenComplete(boolean exitWhenComplete) {
        this.exitWhenComplete = exitWhenComplete;
        return this;
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
