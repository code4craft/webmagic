package us.codecraft.webmagic.scheduler;

import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Base Scheduler with duplicated urls removed locally.
 *
 * @author code4crafter@gmail.com
 * @since 0.5.0
 */
public abstract class LocalDuplicatedRemovedScheduler implements Scheduler {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    private Set<String> urls = Sets.newSetFromMap(new ConcurrentHashMap<String, Boolean>());

    @Override
    public void push(Request request, Task task) {
        logger.trace("get a candidate url " + request.getUrl());
        if (request.getExtra(Request.CYCLE_TRIED_TIMES) != null || urls.add(request.getUrl())) {
        	logger.debug("push to queue " + request.getUrl());
            pushWhenNoDuplicate(request, task);
        }
    }

    protected abstract void pushWhenNoDuplicate(Request request, Task task);
}
