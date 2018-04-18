package us.codecraft.webmagic.selector;

import java.util.List;
import org.jsoup.nodes.Element;

/**
 * Selector(extractor) for html elements.<br>
 *
 * @author code4crafter@gmail.com <br>
 * @since 0.3.0
 */
public interface ElementSelector {

    /**
     * Extract single result in text.<br>
     * If there are more than one result, only the first will be chosen.
     *
     * @param element element
     *
     * @return result
     */
    String select(Element element);

    /**
     * Extract all results in text.<br>
     *
     * @param element element
     *
     * @return results
     */
    List<String> selectList(Element element);
}
