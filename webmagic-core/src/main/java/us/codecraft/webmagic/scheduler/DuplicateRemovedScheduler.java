package us.codecraft.webmagic.scheduler;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Sets;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.scheduler.component.DuplicateRemover;
import us.codecraft.webmagic.scheduler.component.HashSetDuplicateRemover;

/**
 * Remove duplicate urls and only push urls which are not duplicate.<br></br>
 *
 * @author code4crafer@gmail.com
 * @since 0.5.0
 */
public abstract class DuplicateRemovedScheduler implements Scheduler {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    private DuplicateRemover duplicatedRemover = new HashSetDuplicateRemover();
    /**
     * 抓取新增内容时 用它来避免重复添加旧HelpUrl到队列中
     */
    private Set<String> oldHelpUrlRemover = Sets.newSetFromMap(new ConcurrentHashMap<String, Boolean>());

    public DuplicateRemover getDuplicateRemover() {
        return duplicatedRemover;
    }

    public DuplicateRemovedScheduler setDuplicateRemover(DuplicateRemover duplicatedRemover) {
        this.duplicatedRemover = duplicatedRemover;
        return this;
    }

    @Override
    public void push(Request request, Task task) {
        logger.trace("get a candidate url {}", request.getUrl());
        if (!duplicatedRemover.isDuplicate(request, task) || shouldReserved(request)) {
            logger.debug("push to queue {}", request.getUrl());
            if(request.getExtra(Request.CRAWL_NEW_ADDED)!=null)
            	logger.info("New added url: {}",request);
            pushWhenNoDuplicate(request, task);
        }else{
        	// 已抓取过此Url
        	// 抓取增量 && HelpUrl 添加到队列中 但不持久化 目的是通过此HelpUrl来得到新的TargetUrl和HelpUrl
        	if(request.getExtra(Request.CRAWL_NEW_ADDED)!=null && Request.HELP_URL.equals(request.getExtra(Request.KIND_OF_URL))){
        		if(oldHelpUrlRemover.add(request.getUrl())){
        			request.putExtra(Request.ONLY_ADD_TO_QUEUE, true);
        			logger.info("Old Help Url {} only add  to queue, not persiste", request);
        			pushWhenNoDuplicate(request, task);
        		}
        	}
        }
    }
    public void clearOldHelpUrlRemover(){
    	oldHelpUrlRemover.clear();
    }
    protected boolean shouldReserved(Request request) {
        return request.getExtra(Request.CYCLE_TRIED_TIMES) != null ;
    }

    protected void pushWhenNoDuplicate(Request request, Task task) {

    }
}
