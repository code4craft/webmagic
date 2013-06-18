package us.codecraft.webmagic.schedular;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Request;

import java.io.*;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Author: code4crafter@gmail.com
 * Date: 13-4-21
 * Time: 下午1:13
 */
public class FileCacheQueueSchedular implements Schedular {

    private Logger logger = Logger.getLogger(getClass());

    private String filePath = System.getProperty("java.io.tmpdir");

    private String fileUrlAllName = ".urls.txt";

    private Site site;

    private String fileCursor = ".cursor.txt";

    private PrintWriter fileUrlWriter;

    private PrintWriter fileCursorWriter;

    private AtomicInteger cursor = new AtomicInteger();

    private AtomicBoolean inited = new AtomicBoolean(false);

    private BlockingQueue<Request> queue;

    private Set<String> urls;

    public FileCacheQueueSchedular(Site site) {
        this.site = site;
    }

    public FileCacheQueueSchedular(Site site, String filePath) {
        this.filePath = filePath;
        this.site = site;
    }

    private void flush() {
        fileUrlWriter.flush();
        fileCursorWriter.flush();
    }

    private void init() {
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        readFile();
        initWriter();
        initFlushThread();
        inited.set(true);
        logger.info("init cache schedular success");
    }

    private void initFlushThread() {
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                flush();
            }
        }, 10, 10, TimeUnit.SECONDS);
    }

    private void initWriter() {
        try {
            fileUrlWriter = new PrintWriter(new FileWriter(getFileName(fileUrlAllName), true));
            fileCursorWriter = new PrintWriter(new FileWriter(getFileName(fileCursor), false));
        } catch (IOException e) {
            throw new RuntimeException("init cache schedular error", e);
        }
    }

    private void readFile() {
        try {
            queue = new LinkedBlockingQueue<Request>();
            urls = new LinkedHashSet<String>();
            readCursorFile();
            readUrlFile();
        } catch (IOException e) {
        }
    }

    private void readUrlFile() throws IOException {
        String line;
        BufferedReader fileUrlReader = new BufferedReader(new FileReader(getFileName(fileUrlAllName)));
        int lineReaded = 0;
        while ((line = fileUrlReader.readLine()) != null) {
            urls.add(line.trim());
            lineReaded++;
            if (lineReaded > cursor.get()) {
                queue.add(new Request(line, site));
            }
        }
    }

    private void readCursorFile() throws IOException {
        BufferedReader fileCursorReader = new BufferedReader(new FileReader(getFileName(fileCursor)));
        String line = null;
        //read the last number
        while ((line = fileCursorReader.readLine()) != null) {
            cursor = new AtomicInteger(NumberUtils.toInt(line));
        }
    }

    private String getFileName(String filename) {
        return filePath + site.getDomain() + "#" + site.getIdentifier() + filename;
    }

    @Override
    public synchronized void push(Request request, Site site) {
        if (!inited.get()) {
            init();
        }
        if (logger.isDebugEnabled()) {
            logger.debug("push to queue " + request.getUrl());
        }
        if (urls.add(request.getUrl())) {
            queue.add(request);
            fileUrlWriter.println(request.getUrl());
        }

    }

    @Override
    public synchronized Request poll(Site site) {
        if (!inited.get()) {
            init();
        }
        fileCursorWriter.println(cursor.incrementAndGet());
        return queue.poll();
    }
}
