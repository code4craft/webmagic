package us.codecraft.webmagic.annotation;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * @author yihua.huang@dianping.com <br>
 * @date: 13-8-2 <br>
 * Time: 上午10:47 <br>
 */
public class ObjectPipeline implements Pipeline {
    @Override
    public void process(ResultItems resultItems, Task task) {

    }

    public <T> T read() {
        return null;
    }
}
