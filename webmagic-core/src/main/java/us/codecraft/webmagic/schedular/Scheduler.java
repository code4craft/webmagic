package us.codecraft.webmagic.schedular;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;

/**
 * 包含url管理和调度的接口。包括url抓取队列，url去重等功能。<br>
 * Scheduler的接口包含一个Task参数，该参数是为单Scheduler多Task预留的(Spider就是一个Task)。<br>
 * @author code4crafter@gmail.com <br>
 * Date: 13-4-21
 * Time: 下午1:12
 */
public interface Scheduler {

    /**
     * 加入一个待抓取的链接
     * @param request 待抓取的链接
     * @param task 定义的任务，以满足单Scheduler多Task的情况
     */
    public void push(Request request,Task task);

    /**
     * 返回下一个要抓取的链接
     * @param task 定义的任务，以满足单Scheduler多Task的情况
     * @return
     */
    public Request poll(Task task);

}
