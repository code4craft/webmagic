package us.codecraft.webmagic.processor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.handler.PatternHandler;
import us.codecraft.webmagic.utils.UrlUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Sebastian MA
 * Date: April 04, 2014
 * Time: 15:36
 * <p></p>
 * A PatternPageProcessor uses PatternHandler to setup extraction rules for specific url pattern.
 *
 * @see us.codecraft.webmagic.handler.PatternHandler
 */
public class PatternPageProcessor implements PageProcessor {

	public static final String TARGET_PATTERN_ALL = "http://*";

	protected Site site;

	protected String targetPattern;

	protected ArrayList<PatternHandler> handlers = new ArrayList<PatternHandler>();

	public PatternPageProcessor(String startUrl, String targetPattern) {

		this.targetPattern = targetPattern;

		this.site = Site.me().addStartUrl(startUrl).setDomain(UrlUtils.getDomain(startUrl));
		this.targetPattern = "(" + targetPattern.replace(".", "\\.").replace("*",
				"[^\"'#]*") + ")";

		site.setUserAgent("Chrome/5.0.354.0");
	}

	@Override
	public void process(Page page) {


		List<String> requests = page.getHtml().links().regex(targetPattern).all();
		page.addTargetRequests(requests);
		for(PatternHandler handler : handlers) {
			if(handler.match(page.getUrl().toString())) {
				handler.process(page);
			}
		}
	}

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
	public Site getSite() {

		return site;
	}
}
