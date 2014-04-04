package us.codecraft.webmagic.handler;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PatternPipeline;
import us.codecraft.webmagic.processor.PatternPageProcessor;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: Sebastian MA
 * Date: April 03, 2014
 * Time: 10:00
 * <p></p>
 * A PatternHandler is in charge of both page extraction and data processing by implementing
 * its two abstract methods.
 */
public abstract class PatternHandler {

	/**
	 * identity of the handler.
	 */
	protected String id;

	/**
	 * match pattern. only matched page should be handled.
	 */
	protected String pattern;

	/**
	 * @param pattern
	 * 		url pattern to handle
	 */
	protected PatternHandler(String pattern) {

		this.pattern = pattern;
		this.id = UUID.randomUUID().toString();
	}

	/**
	 * determine if the page should be handled.
	 */
	public boolean match(String url) {

		return url.matches(pattern);
	}

	/**
	 * registers to both the page processor and the pipeline so the handler could take charge of
	 * both end of procedure.
	 *
	 * @param processor
	 * 		the processor to handle
	 * @param pipeline
	 * 		the pipeline to handle
	 */
	public void register(PatternPageProcessor processor, PatternPipeline pipeline) {

		processor.addHandler(this);
		pipeline.addHandler(this);
	}

	public void unregister(PatternPageProcessor processor, PatternPipeline pipeline) {

		processor.removeHandler(this);
		pipeline.removeHandler(this);
	}

	public boolean process(Page page) {

		if(match(page.getUrl().toString())) {
			page.putField(id, true);
			onExtract(page);
			return true;
		} else {
			return false;
		}
	}

	public boolean process(ResultItems resultItems, Task task) {

		if(resultItems.isSkip()) {
			return false;
		}

		if(match(resultItems.getRequest().getUrl()) && resultItems.get(id) != null) {
			onHandle(resultItems, task);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * implements this method to extract from page.
	 *
	 * @param page
	 * 		the page to extract
	 */
	public abstract void onExtract(Page page);

	/**
	 * implements this method to handle the extraction result.
	 *
	 * @param result
	 * 		extraction result
	 * @param task
	 */
	public abstract void onHandle(ResultItems result, Task task);

}
