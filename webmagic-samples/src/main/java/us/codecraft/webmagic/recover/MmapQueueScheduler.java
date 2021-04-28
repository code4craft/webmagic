package us.codecraft.webmagic.recover;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.IndexTreeList;
import org.mapdb.Serializer;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.scheduler.DuplicateRemovedScheduler;
import us.codecraft.webmagic.scheduler.component.DuplicateRemover;

import java.io.IOException;

/**
 * @author ：linweisen
 */
public class MmapQueueScheduler extends DuplicateRemovedScheduler {

    private DB db;

    private static String DATABASE_NAME = "queue";

    private IndexTreeList<String> queue;

    private static ObjectMapper mapper;

    public MmapQueueScheduler(DuplicateRemover duplicateRemover, String path) {
        super.setDuplicateRemover(duplicateRemover);

        String queuePath = path;

        DB db = DBMaker.fileDB(queuePath)
                .fileMmapEnableIfSupported()
                .fileMmapPreclearDisable()
                .cleanerHackEnable()
                .closeOnJvmShutdown()
                .transactionEnable()
                .concurrencyScale(128)
                .make();
        this.db = db;
        this.mapper = new ObjectMapper();
        this.queue = db.indexTreeList(MmapQueueScheduler.DATABASE_NAME, Serializer.STRING).createOrOpen();
    }

    @Override
    public Request poll(Task task) {
        if (this.queue.size() > 0){
            String s = queue.remove(0);
            return fromJson(s, Request.class);
        }else{
            return null;
        }

    }

    @Override
    public void pushWhenNoDuplicate(Request request, Task task) {
        queue.add(toJson(request));
        this.db.commit();
    }

    public String toJson(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (IOException e) {
            logger.warn("write to json string error:" + object, e);
            return null;
        }
    }

    public <T> T fromJson(String jsonString, Class<T> clazz) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }
        try {
            return mapper.readValue(jsonString, clazz);
        } catch (IOException e) {
            logger.warn("parse json string error:" + jsonString, e);
            return null;
        }
    }

}
