package us.codecraft.webmagic.pipeline;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * @author code4crafter@gmail.com
 * @since 0.4.0
 */
public class CollectorPipeline implements Pipeline{

    private List<ResultItems> collector = new ArrayList<ResultItems>();

    @Override
    public void process(ResultItems resultItems, Task task) {
        collector.add(resultItems);
    }

    public List<ResultItems> getCollector() {
        return collector;
    }
}
