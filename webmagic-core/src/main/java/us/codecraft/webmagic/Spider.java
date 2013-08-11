package us.codecraft.webmagic;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import us.codecraft.webmagic.downloader.Destroyable;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.scheduler.Scheduler;
import us.codecraft.webmagic.utils.ThreadUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <pre>
 * webmagic爬虫的入口类。
 *
 * 示例：
 * 定义一个最简单的爬虫：
 *      Spider.create(new SimplePageProcessor("http://my.oschina.net/", "http://my.oschina.net/*blog/*")).run();
 *
 * 使用FilePipeline保存结果到文件:
 *      Spider.create(new SimplePageProcessor("http://my.oschina.net/", "http://my.oschina.net/*blog/*"))
 *          .pipeline(new FilePipeline("/data/temp/webmagic/")).run();
 *
 * 使用FileCacheQueueScheduler缓存URL，关闭爬虫后下次自动从停止的页面继续抓取:
 *      Spider.create(new SimplePageProcessor("http://my.oschina.net/", "http://my.oschina.net/*blog/*"))
 *          .scheduler(new FileCacheQueueScheduler("/data/temp/webmagic/cache/")).run();
 * </pre>
 *
 * @author code4crafter@gmail.com <br>
 *         Date: 13-4-21
 *         Time: 上午6:53
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
     * 使用已定义的抽取规则新建一个Spider。
     *
     * @param pageProcessor 已定义的抽取规则
     */
    public Spider(PageProcessor pageProcessor) {
        this.pageProcessor = pageProcessor;
        this.site = pageProcessor.getSite();
        this.startUrls = pageProcessor.getSite().getStartUrls();
    }

    /**
     * 使用已定义的抽取规则新建一个Spider。
     *
     * @param pageProcessor 已定义的抽取规则
     * @return 新建的Spider
     */
    public static Spider create(PageProcessor pageProcessor) {
        return new Spider(pageProcessor);
    }

    /**
     * 重新设置startUrls，会覆盖Site本身的startUrls。
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
     * 为爬虫设置一个唯一ID，用于标志任务，默认情况下使用domain作为uuid，对于单domain多任务的情况，请为重复任务设置不同的ID。
     *
     * @param uuid 唯一ID
     * @return this
     */
    public Spider setUUID(String uuid) {
        this.uuid = uuid;
        return this;
    }

    /**
     * 设置调度器。调度器用于保存待抓取URL，并可以进行去重、同步、持久化等工作。默认情况下使用内存中的阻塞队列进行调度。
     *
     * @param scheduler 调度器
     * @return this
     */
    public Spider scheduler(Scheduler scheduler) {
        checkIfNotRunning();
        this.scheduler = scheduler;
        return this;
    }

    /**
     * 设置处理管道。处理管道用于最终抽取结果的后处理，例如：保存到文件、保存到数据库等。默认情况下会输出到控制台。
     *
     * @param pipeline 处理管道
     * @return this
     */
    public Spider pipeline(Pipeline pipeline) {
        checkIfNotRunning();
        this.pipelines.add(pipeline);
        return this;
    }

    public Spider downloader(Downloader downloader) {
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
        if (object instanceof Destroyable) {
            ((Destroyable) object).destroy();
        }
    }

    /**
     * 用某些特定URL进行爬虫测试
     * @param urls 要抓取的url
     */
    public void test(String... urls){
        checkComponent();
        if (urls.length>0){
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
        pageProcessor.process(page);
        addRequest(page);
        if (!page.getResultItems().isSkip()){
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
     * 建立多个线程下载
     *
     * @param threadNum 线程数
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

    public Spider clearPipeline(){
        pipelines=new ArrayList<Pipeline>();
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
