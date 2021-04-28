package us.codecraft.webmagic.recover;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.IndexTreeList;
import org.mapdb.Serializer;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.scheduler.component.DuplicateRemover;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ：linweisen
 */
public class DuplicateStorageRemover implements DuplicateRemover {

    private DB db;

    private static String DATABASE_NAME = "duplicate";

    private IndexTreeList<String> urlDuplicateQueue;

    private BloomFilter<CharSequence> bloomFilter;

    private AtomicInteger counter;

    public DuplicateStorageRemover(String path) {

        String duplicatStoragePath = path;

        DB db = DBMaker.fileDB(duplicatStoragePath)
                .fileMmapEnableIfSupported()
                .fileMmapPreclearDisable()
                .cleanerHackEnable()
                .closeOnJvmShutdown()
                .transactionEnable()
                .concurrencyScale(128)
                .make();
        this.db = db;

        this.urlDuplicateQueue = db.indexTreeList(DATABASE_NAME, Serializer.STRING).createOrOpen();

        counter = new AtomicInteger(this.urlDuplicateQueue.size());
        this.bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), 200000, 1E-7);
        for (String url : this.urlDuplicateQueue){
            bloomFilter.put(url);
        }

    }

    @Override
    public boolean isDuplicate(Request request, Task task) {
        String url = request.getUrl();
        boolean isDuplicate = bloomFilter.mightContain(url);
        if (!isDuplicate) {
            bloomFilter.put(url);
            urlDuplicateQueue.add(url);
            this.db.commit();
            counter.incrementAndGet();
        }
        return isDuplicate;
    }

    @Override
    public void resetDuplicateCheck(Task task) {
        this.bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), 200000, 1E-7);
        this.urlDuplicateQueue.clear();
    }

    @Override
    public int getTotalRequestsCount(Task task) {
        return counter.get();
    }
}
