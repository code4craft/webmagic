package us.codecraft.webmagic.pipeline;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Task;

/**
 * Pipeline是数据离线处理和持久化的接口。通过实现Pipeline以实现不同的持久化方式(例如保存到数据库)。
 * @author code4crafter@gmail.com <br>
 * Date: 13-4-21
 * Time: 下午1:39
 */
public interface Pipeline {

    public void process(Page page,Task task);
}
