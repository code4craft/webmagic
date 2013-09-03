package us.codecraft.webmagic.selector;

import org.jsoup.nodes.Element;

import java.util.List;

/**
 * Cache parsed element for extract.
 *
 * @author code4crafter@gmail.com
 * @since 0.2.2
 */
public class CacheElement {

    public String text;

    public Element element;

    public String select(Selector selector) {
        if (selector instanceof ElementSelector) {
            ElementSelector elementSelector = (ElementSelector) selector;
            return elementSelector.select(getElement());
        } else {
            return selector.select(getText());
        }
    }

    public List<String> selectList(Selector selector) {
        if (selector instanceof ElementSelector) {
            ElementSelector elementSelector = (ElementSelector) selector;
            return elementSelector.selectList(getElement());
        } else {
            return selector.selectList(getText());
        }
    }
}
