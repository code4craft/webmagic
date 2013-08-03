package us.codecraft.webmagic.oo;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author code4crafter@gmail.com <br>
 * @date: 13-8-2 <br>
 * Time: 上午10:47 <br>
 */
public class ObjectPipeline implements Pipeline {

    private Map<Class, PageModelPipeline> pageModelPipelines = new ConcurrentHashMap<Class, PageModelPipeline>();

    public ObjectPipeline() {
    }

    public ObjectPipeline put(Class clazz, PageModelPipeline pageModelPipeline) {
        pageModelPipelines.put(clazz, pageModelPipeline);
        return this;
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        if (resultItems.isSkip()) {
            return;
        }
        for (Map.Entry<Class, PageModelPipeline> classPageModelPipelineEntry : pageModelPipelines.entrySet()) {
            Object o = resultItems.get(classPageModelPipelineEntry.getKey().getCanonicalName());
            if (o != null) {
                classPageModelPipelineEntry.getValue().process(o, task);
            }
        }
    }
}
