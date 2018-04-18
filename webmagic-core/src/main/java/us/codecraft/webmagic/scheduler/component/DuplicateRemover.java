package us.codecraft.webmagic.scheduler.component;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;

/**
 * Remove duplicate requests.
 *
 * @author code4crafer@gmail.com
 * @since 0.5.1
 */
public interface DuplicateRemover {
    /**
     * Check whether the request is duplicate.
     *
     * @param request request
     * @param task task
     *
     * @return true if is duplicate
     */
    boolean isDuplicate(Request request, Task task);

    /**
     * Reset duplicate check.
     *
     * @param task task
     */
    void resetDuplicateCheck(Task task);

    /**
     * Get TotalRequestsCount for monitor.
     *
     * @param task task
     *
     * @return number of total request
     */
    int getTotalRequestsCount(Task task);
}
