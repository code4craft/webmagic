package us.codecraft.webmagic.scheduler;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


/**
 * Basic Scheduler implementation.<br>
 * Store urls to fetch in LinkedBlockingQueue and remove duplicate urls by HashMap.
 *
 * @author code4crafter@gmail.com <br>
 * @since 0.1.0
 */
public class QueueScheduler extends DuplicateRemovedScheduler implements MonitorableScheduler {

    private final BlockingQueue<Request> queue;

    public QueueScheduler() {
        this.queue = new LinkedBlockingQueue<>();
    }

    /**
     * Creates a {@code QueueScheduler} with the given (fixed) capacity.
     *
     * @param capacity the capacity of this queue,
     * see {@link LinkedBlockingQueue#LinkedBlockingQueue(int)}
     * @since 0.8.0
     */
    public QueueScheduler(int capacity) {
        this.queue = new LinkedBlockingQueue<>(capacity);
    }

    @Override
    public void pushWhenNoDuplicate(Request request, Task task) {
        try {
            queue.put(request);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public Request poll(Task task) {
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
}
