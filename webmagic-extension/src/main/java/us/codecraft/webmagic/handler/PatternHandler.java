package us.codecraft.webmagic.handler;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;

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
public abstract class PatternHandler implements SubPageProcessor {

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

	public boolean processPage(Page page) {

		if(match(page.getUrl().toString())) {
			page.putField(id, true);
			process(page);
			return true;
		} else {
			return false;
		}
	}

	public boolean processResult(ResultItems resultItems, Task task) {

		if(resultItems.isSkip()) {
			return false;
		}

		if(match(resultItems.getRequest().getUrl()) && resultItems.get(id) != null) {
			handle(resultItems, task);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * override this method to handle the extraction result. this method MUST use
	 * with PatternPipeline
	 *
	 * @param result
	 * 		extraction result
	 * @param task
	 */
	public void handle(ResultItems result, Task task) {

	}

	@Override
	public boolean match(Page page) {

		return match(page.getUrl().toString());
	}
}
