package us.codecraft.webmagic.schedular;

import org.apache.log4j.Logger;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Author: code4crafter@gmail.com
 * Date: 13-4-21
 * Time: 下午1:13
 */
public class QueueSchedular implements Schedular {

    private Logger logger = Logger.getLogger(getClass());

    private BlockingQueue<Request> queue = new LinkedBlockingQueue<Request>();

    private Set<String> urls = new HashSet<String>();

    @Override
    public synchronized void push(Request request,Task task) {
        if (logger.isDebugEnabled()){
            logger.debug("push to queue "+request.getUrl());
        }
        if (urls.add(request.getUrl())){
            queue.add(request);
        }

    }

    @Override
    public synchronized Request poll(Task task) {
        return queue.poll();
    }
}
