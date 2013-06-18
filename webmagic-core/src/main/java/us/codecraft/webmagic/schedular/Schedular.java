package us.codecraft.webmagic.schedular;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;

/**
 * @author code4crafter@gmail.com <br>
 * Date: 13-4-21
 * Time: 下午1:12
 */
public interface Schedular {

    public void push(Request request,Task task);

    public Request poll(Task task);

}
