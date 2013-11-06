package us.codecraft.webmagic.pipeline;

import us.codecraft.webmagic.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * @author code4crafter@gmail.com
 */
public class CollectorPageModelPipeline<T> implements PageModelPipeline<T> {

    private List<T> collected = new ArrayList<T>();

    @Override
    public synchronized void process(T t, Task task) {
        collected.add(t);
    }

    public List<T> getCollected() {
        return collected;
    }
}
