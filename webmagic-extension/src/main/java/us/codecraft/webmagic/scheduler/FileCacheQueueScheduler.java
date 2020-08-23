package us.codecraft.webmagic.scheduler;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.math.NumberUtils;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.scheduler.component.DuplicateRemover;


/**
 * Store urls and cursor in files so that a Spider can resume the status when shutdown.<br>
 *
 * @author code4crafter@gmail.com <br>
 * @since 0.2.0
 */
public class FileCacheQueueScheduler extends DuplicateRemovedScheduler implements MonitorableScheduler,Closeable {

    private String filePath = System.getProperty("java.io.tmpdir");

    private String fileUrlAllName = ".urls.txt";

    private Task task;

    private String fileCursor = ".cursor.txt";

    private PrintWriter fileUrlWriter;

    private PrintWriter fileCursorWriter;

    private AtomicInteger cursor = new AtomicInteger();

    private AtomicBoolean inited = new AtomicBoolean(false);

    private BlockingQueue<Request> queue;

    private Set<String> urls;
    
    private ScheduledExecutorService flushThreadPool;

    public FileCacheQueueScheduler(String filePath) {
        if (!filePath.endsWith("/") && !filePath.endsWith("\\")) {
            filePath += "/";
        }
        this.filePath = filePath;
        initDuplicateRemover();
    }

    private void flush() {
        fileUrlWriter.flush();
        fileCursorWriter.flush();
    }

    private void init(Task task) {
        this.task = task;
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        readFile();
        initWriter();
        initFlushThread();
        inited.set(true);
        logger.info("init cache scheduler success");
    }

    private void initDuplicateRemover() {
        setDuplicateRemover(
                new DuplicateRemover() {
                    @Override
                    public boolean isDuplicate(Request request, Task task) {
                        if (!inited.get()) {
                            init(task);
                        }
                        return !urls.add(request.getUrl());
                    }

                    @Override
                    public void resetDuplicateCheck(Task task) {
                        urls.clear();
                    }

                    @Override
                    public int getTotalRequestsCount(Task task) {
                        return urls.size();
                    }
                });
    }

    private void initFlushThread() {
    	flushThreadPool = Executors.newScheduledThreadPool(1);
    	flushThreadPool.scheduleAtFixedRate(new Runnable() {
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
            throw new RuntimeException("init cache scheduler error", e);
        }
    }

    private void readFile() {
        try {
            queue = new LinkedBlockingQueue<Request>();
            urls = new LinkedHashSet<String>();
            readCursorFile();
            readUrlFile();
            // initDuplicateRemover();
        } catch (FileNotFoundException e) {
            //init
            logger.info("init cache file " + getFileName(fileUrlAllName));
        } catch (IOException e) {
            logger.error("init file error", e);
        }
    }

    private void readUrlFile() throws IOException {
        String line;
        BufferedReader fileUrlReader = null;
        try {
            fileUrlReader = new BufferedReader(new FileReader(getFileName(fileUrlAllName)));
            int lineReaded = 0;
            while ((line = fileUrlReader.readLine()) != null) {
                urls.add(line.trim());
                lineReaded++;
                if (lineReaded > cursor.get()) {
                    queue.add(deserializeRequest(line));
                }
            }
        } finally {
            if (fileUrlReader != null) {
                IOUtils.closeQuietly(fileUrlReader);
            }
        }
    }

    private void readCursorFile() throws IOException {
        BufferedReader fileCursorReader = null;
        try {
        	fileCursorReader = new BufferedReader(new FileReader(getFileName(fileCursor)));
            String line;
            //read the last number
            while ((line = fileCursorReader.readLine()) != null) {
                cursor = new AtomicInteger(NumberUtils.toInt(line));
            }
        } finally {
            if (fileCursorReader != null) {
                IOUtils.closeQuietly(fileCursorReader);
            }
        }
    }
    
    public void close() throws IOException {
		flushThreadPool.shutdown();	
		fileUrlWriter.close();
		fileCursorWriter.close();
	}

    private String getFileName(String filename) {
        return filePath + task.getUUID() + filename;
    }

    @Override
    protected void pushWhenNoDuplicate(Request request, Task task) {
        if (!inited.get()) {
            init(task);
        }
        queue.add(request);
        fileUrlWriter.println(serializeRequest(request));
    }

    @Override
    public synchronized Request poll(Task task) {
        if (!inited.get()) {
            init(task);
        }
        fileCursorWriter.println(cursor.incrementAndGet());
        return queue.poll();
    }

    @Override
    public int getLeftRequestsCount(Task task) {
        return queue.size();
    }

    @Override
    public int getTotalRequestsCount(Task task) {
        return getDuplicateRemover().getTotalRequestsCount(task);
    }

    protected String serializeRequest(Request request) {
        return request.getUrl();
    }

    protected Request deserializeRequest(String line) {
        return new Request(line);
    }

}
