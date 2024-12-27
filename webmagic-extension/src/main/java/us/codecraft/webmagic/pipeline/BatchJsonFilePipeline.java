package us.codecraft.webmagic.pipeline;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.utils.FilePersistentBase;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Store results to files in JSON format.<br>
 *
 * @author zhanglubing927@163.com <br>
 * @since 0.7.3
 */
public class BatchJsonFilePipeline extends FilePersistentBase implements Pipeline, Closeable {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private boolean running;
    private int batchSize;

    private BlockingQueue<Map<String, Object>> queue = new LinkedBlockingDeque<Map<String, Object>>();
    private ExecutorService executorService = Executors.newFixedThreadPool(1);
    private AtomicInteger index = new AtomicInteger(0);
    private List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

    public BatchJsonFilePipeline(int batchSize) {
        this("/data/webmagic", batchSize);
    }

    public BatchJsonFilePipeline(String path, int batchSize) {
        this.batchSize = batchSize;
        setPath(path);

        startWriteTask();
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        try {
            queue.put(resultItems.getAll());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        this.running = false;

        executorService.shutdown();

        if (!list.isEmpty()) {
            write(list);
        }
    }

    protected String filename(int currentIndex) {
        return currentIndex + ".json";
    }

    private void startWriteTask() {
        running = true;
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                while (running) {
                    Map<String, Object> fields = null;
                    try {
                        fields = queue.poll(1, TimeUnit.SECONDS);
                    } catch (InterruptedException ignore) {
                    }

                    if (fields != null && !fields.isEmpty()) {
                        list.add(fields);
                        if (list.size() >= batchSize) {
                            write(list);
                            list.clear();
                        }
                    }
                }
            }
        });
    }

    private void write(List<Map<String, Object>> data) {
        try {
            String filename = filename(index.getAndAdd(1));
            File file = getFile(getPath() + PATH_SEPERATOR + filename);
            PrintWriter printWriter = new PrintWriter(new FileWriter(file));
            printWriter.write(JSON.toJSONString(data));
            printWriter.close();
        } catch (IOException e) {
            logger.warn("write file error", e);
        }
    }

}
