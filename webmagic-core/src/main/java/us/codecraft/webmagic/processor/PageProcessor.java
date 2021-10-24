package us.codecraft.webmagic.processor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;

/**
 * Interface to be implemented to customize a crawler.
 *
 * <p>
 * In PageProcessor, you can customize:
 * </p>
 * <ul>
 * <li>start URLs and other settings in {@link Site}</li>
 * <li>how the URLs to fetch are detected</li>
 * <li>how the data are extracted and stored</li>
 * </ul>
 *
 * @author code4crafter@gmail.com <br>
 * @see Site
 * @see Page
 * @since 0.1.0
 */
public interface PageProcessor {

    /**
     * Processes the page, extract URLs to fetch, extract the data and store.
     *
     * @param page page
     */
    void process(Page page);

    /**
     * Returns the site settings.
     *
     * @return site
     * @see Site
     */
    default Site getSite() {
        return Site.me();
    }

}
