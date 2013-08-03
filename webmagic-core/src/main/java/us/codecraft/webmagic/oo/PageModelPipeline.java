package us.codecraft.webmagic.oo;

import us.codecraft.webmagic.Task;

/**
 * @author code4crafter@gmail.com <br>
 * @date: 13-8-3 <br>
 * Time: 上午9:34 <br>
 */
public interface PageModelPipeline<T> {

    public void process(T t, Task task);

}
