package us.codecraft.webmagic.example;

import org.apache.log4j.Logger;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.handler.PatternHandler;
import us.codecraft.webmagic.handler.SubPageProcessor;
import us.codecraft.webmagic.pipeline.PatternPipeline;
import us.codecraft.webmagic.processor.PatternPageProcessor;

/**
 * Created with IntelliJ IDEA.
 * User: Sebastian MA
 * Date: April 04, 2014
 * Time: 21:23
 */
public class PatternProcessorDemo {

	private static Logger log = Logger.getLogger(PatternProcessorDemo.class);

	public static void main(String... args) {

		PatternPageProcessor processor
				= new PatternPageProcessor("http://item.jd.com/981821.html",
				PatternPageProcessor.TARGET_PATTERN_ALL
		);

		PatternPipeline pipeline = new PatternPipeline();

		// define a handler which handles only "http://item.jd.com/.*"
		PatternHandler handler = new PatternHandler("http://item.jd.com/.*") {

			@Override
			public SubPageProcessor.MatchOtherProcessor process(Page page) {

				log.info("Extracting from " + page.getUrl());
				page.putField("test", "hello world:)");
				return MatchOtherProcessor.YES;
			}

			@Override
			public void handle(ResultItems result, Task task) {

				log.info("Handling " + result.getRequest().getUrl());
				log.info("Retrieved test=" + result.get("test"));
			}
		};

		processor.addHandler(handler);
		pipeline.addHandler(handler);

		Spider.create(processor).thread(5).addPipeline(pipeline).runAsync();
	}
}
