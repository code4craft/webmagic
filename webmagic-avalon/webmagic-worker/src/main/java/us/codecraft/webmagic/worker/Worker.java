package us.codecraft.webmagic.worker;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.utils.ThreadUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;

/**
 * @author code4crafter@gmail.com
 */
public class Worker {

    public static final int DEFAULT_POOL_SIZE = 10;

    private int poolSize;

    private ExecutorService executorService;

    private Map<String,Spider> spiderMap;

    public Worker(int poolSize) {
        this.poolSize = poolSize;
        this.executorService = initExecutorService();
        this.spiderMap = new ConcurrentHashMap<String, Spider>();
    }

    public Worker() {
        this(DEFAULT_POOL_SIZE);
    }

    protected ExecutorService initExecutorService() {
        return ThreadUtils.newFixedThreadPool(poolSize);
    }

    public void addSpider(Spider spider) {
        spider.setExecutorService(executorService);
        spiderMap.put(spider.getUUID(), spider);
    }

    public Spider getSpider(String uuid){
        return spiderMap.get(uuid);
    }

}
