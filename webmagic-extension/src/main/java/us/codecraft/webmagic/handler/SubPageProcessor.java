package us.codecraft.webmagic.handler;

import us.codecraft.webmagic.Page;

/**
 * @author code4crafter@gmail.com
 * @date 14-4-5
 */
public interface SubPageProcessor {

	/**
	 * Check whether the SubPageProcessor can process the page.<br></br>
	 * Please DO NOT change page status in this method.
	 *
	 * @param page
	 *
	 * @return
	 */
	public boolean match(Page page);

	/**
	 * process the page, extract urls to fetch, extract the data and store
	 *
	 * @param page
	 *
	 * @return whether continue to match
	 */
	public MatchOtherProcessor process(Page page);

	public enum MatchOtherProcessor {
		YES, NO
	}

}
