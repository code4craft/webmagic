package us.codecraft.webmagic.selector;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * @author code4crafter@gmail.com
 * @since 0.3.0
 */
public abstract class BaseElementSelector implements Selector, ElementSelector {

    @Override
    public String select(String text) {
        if (text != null) {
            return select(Jsoup.parse(text));
        }
        return null;
    }

    @Override
    public List<String> selectList(String text) {
        if (text != null) {
            return selectList(Jsoup.parse(text));
        } else {
            return new ArrayList<String>();
        }
    }

    public Element selectElement(String text) {
        if (text != null) {
            return selectElement(Jsoup.parse(text));
        }
        return null;
    }

    public List<Element> selectElements(String text) {
        if (text != null) {
            return selectElements(Jsoup.parse(text));
        } else {
            return new ArrayList<Element>();
        }
    }

    public abstract Element selectElement(Element element);

    public abstract List<Element> selectElements(Element element);

    public abstract boolean hasAttribute();

}
