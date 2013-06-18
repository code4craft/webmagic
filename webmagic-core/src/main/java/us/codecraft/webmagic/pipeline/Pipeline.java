package us.codecraft.webmagic.pipeline;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Task;

/**
 * Author: code4crafter@gmail.com
 * Date: 13-4-21
 * Time: 下午1:39
 */
public interface Pipeline {

    public void process(Page page,Task task);
}
