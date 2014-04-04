package us.codecraft.webmagic.pipeline;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.handler.PatternHandler;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Sebastian MA
 * Date: April 04, 2014
 * Time: 20:44
 */
public class PatternPipeline implements Pipeline {

	protected ArrayList<PatternHandler> handlers = new ArrayList<PatternHandler>();

	/**
	 * A handler works only if it is added to BOTH the page processor and the pipeline.
	 * Uses PatternHandler's register instead.
	 *
	 * @param handler the pattern handler
	 *
	 * @see PatternHandler#register
	 */
	public void addHandler(PatternHandler handler) {

		handlers.add(handler);
	}

	public void removeHandler(PatternHandler handler) {

		handlers.remove(handler);
	}

	@Override
	public void process(ResultItems resultItems, Task task) {

		for(PatternHandler handler : handlers) {
			handler.process(resultItems, task);
		}
	}
}
