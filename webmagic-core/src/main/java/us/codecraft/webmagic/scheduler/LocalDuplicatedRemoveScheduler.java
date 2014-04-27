package us.codecraft.webmagic.scheduler;

import com.google.common.collect.Sets;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Base Scheduler with duplicated urls removed by hash set.<br></br>
 *
 * @author code4crafter@gmail.com
 * @since 0.5.0
 */
public abstract class LocalDuplicatedRemoveScheduler extends DuplicatedRemoveScheduler implements MonitorableScheduler {

    private Set<String> urls = Sets.newSetFromMap(new ConcurrentHashMap<String, Boolean>());

    @Override
    public void resetDuplicateCheck(Task task) {
        urls.clear();
    }

    @Override
    protected boolean isDuplicate(Request request, Task task) {
        return urls.add(request.getUrl());
    }

    @Override
    public int getTotalRequestsCount(Task task) {
        return urls.size();
    }
}
