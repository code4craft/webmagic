package us.codecraft.webmagic.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;

/**
 * Remove duplicate urls and only push urls which are not duplicate.<br></br>
 *
 * @author code4crafer@gmail.com
 * @since 0.5.0
 */
public abstract class DuplicatedRemoveScheduler implements Scheduler {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void push(Request request, Task task) {
        logger.trace("get a candidate url {}", request.getUrl());
        if (isDuplicate(request, task) || shouldReserved(request)) {
            logger.debug("push to queue {}", request.getUrl());
            pushWhenNoDuplicate(request, task);
        }
    }

    /**
     * Reset duplicate check.
     */
    public abstract void resetDuplicateCheck(Task task);

    /**
     * @param request
     * @return
     */
    protected abstract boolean isDuplicate(Request request, Task task);

    protected boolean shouldReserved(Request request) {
        return request.getExtra(Request.CYCLE_TRIED_TIMES) != null;
    }

    protected void pushWhenNoDuplicate(Request request, Task task) {

    }
}
