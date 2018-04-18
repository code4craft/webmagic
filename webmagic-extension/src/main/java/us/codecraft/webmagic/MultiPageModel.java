package us.codecraft.webmagic;

import java.util.Collection;
import us.codecraft.webmagic.utils.Experimental;

/**
 * Extract an object of more than one pages, such as news and articles.<br>
 *
 * @author code4crafter@gmail.com <br>
 * @since 0.2.0
 */
@Experimental
public interface MultiPageModel {

    /**
     * Page key is the identifier for the object.
     *
     * @return page key
     */
    String getPageKey();

    /**
     * page is the identifier of a page in pages for one object.
     *
     * @return page
     */
    String getPage();

    /**
     * other pages to be extracted.<br>
     * It is used to judge whether an object contains more than one page, and whether the pages of the object are all extracted.
     *
     * @return other pages
     */
    Collection<String> getOtherPages();

    /**
     * Combine multiPageModels to a whole object.
     *
     * @param multiPageModel multiPageModel
     *
     * @return multiPageModel combined
     */
    MultiPageModel combine(MultiPageModel multiPageModel);
}
