package us.codecraft.webmagic.scheduler;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.schedular.Scheduler;

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
 * 磁盘文件实现的url管理模块，可以保证在长时间执行的任务中断后，下次启动从中断位置重新开始。<br>
 * @author code4crafter@gmail.com <br>
 * Date: 13-4-21
 * Time: 下午1:13
 */
public class FileCacheQueueScheduler implements Scheduler {

    private Logger logger = Logger.getLogger(getClass());

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

    public FileCacheQueueScheduler(String filePath) {
        if (!filePath.endsWith("/")&&!filePath.endsWith("\\")){
            filePath+="/";
        }
        this.filePath = filePath;
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
            throw new RuntimeException("init cache scheduler error", e);
        }
    }

    private void readFile() {
        try {
            queue = new LinkedBlockingQueue<Request>();
            urls = new LinkedHashSet<String>();
            readCursorFile();
            readUrlFile();
        } catch (IOException e) {
            logger.error("init file error",e);
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
                queue.add(new Request(line));
            }
        }
    }

    private void readCursorFile() throws IOException {
        BufferedReader fileCursorReader = new BufferedReader(new FileReader(getFileName(fileCursor)));
        String line;
        //read the last number
        while ((line = fileCursorReader.readLine()) != null) {
            cursor = new AtomicInteger(NumberUtils.toInt(line));
        }
    }

    private String getFileName(String filename) {
        return filePath + task.getUUID()  + filename;
    }

    @Override
    public synchronized void push(Request request, Task task) {
        if (!inited.get()) {
            init(task);
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
    public synchronized Request poll(Task task) {
        if (!inited.get()) {
            init(task);
        }
        fileCursorWriter.println(cursor.incrementAndGet());
        return queue.poll();
    }
}
