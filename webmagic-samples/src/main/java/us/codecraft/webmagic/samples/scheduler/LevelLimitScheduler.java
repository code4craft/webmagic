package us.codecraft.webmagic.samples.scheduler;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.scheduler.PriorityScheduler;

/**
 * @author code4crafter@gmail.com
 */
public class LevelLimitScheduler extends PriorityScheduler {

    private int levelLimit = 3;

    public LevelLimitScheduler(int levelLimit) {
        this.levelLimit = levelLimit;
    }

    @Override
    public synchronized void push(Request request, Task task) {
        if (((Integer) request.getExtra("_level")) <= levelLimit) {
            super.push(request, task);
        }
    }
}
