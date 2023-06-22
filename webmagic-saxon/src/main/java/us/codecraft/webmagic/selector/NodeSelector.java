package us.codecraft.webmagic.selector;

import org.w3c.dom.Node;

import java.util.List;

/**
 * Selector(extractor) for html node.<br>
 *
 * @author hooy <br>
 * @since 0.8.0
 */
public interface NodeSelector {

    /**
     * Extract single result in text.<br>
     * If there are more than one result, only the first will be chosen.
     *
     * @param node node
     * @return result
     */
    String select(Node node);

    /**
     * Extract all results in text.<br>
     *
     * @param node node
     * @return results
     */
    List<String> selectList(Node node);

}
