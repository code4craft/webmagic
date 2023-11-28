package us.codecraft.webmagic.selector;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * @author code4crafer@gmail.com
 */
public class HtmlNode extends AbstractSelectable {

    private final List<Element> elements;

    public HtmlNode(List<Element> elements) {
        this.elements = elements;
    }

    public HtmlNode() {
        elements = null;
    }

    protected List<Element> getElements() {
        return elements;
    }

    @Override
    public Selectable smartContent() {
        SmartContentSelector smartContentSelector = Selectors.smartContent();
        return select(smartContentSelector, getSourceTexts());
    }

    @Override
    public Selectable links() {
        ElementsUtil elementsUtil = new ElementsUtil();
        return elementsUtil.selectElements(new LinksSelector());
    }

    @Override
    public Selectable xpath(String xpath) {
        ElementsUtil elementsUtil = new ElementsUtil();
        XpathSelector xpathSelector = Selectors.xpath(xpath);
        return elementsUtil.selectElements(xpathSelector);
    }

    @Override
    public Selectable selectList(Selector selector) {
        if (selector instanceof BaseElementSelector) {
            ElementsUtil elementsUtil = new ElementsUtil();
           return elementsUtil.selectElements((BaseElementSelector) selector);
        }
        return selectList(selector, getSourceTexts());
    }

    @Override
    public Selectable select(Selector selector) {
        return selectList(selector);
    }

    @Override
    public Selectable $(String selector) {
        ElementsUtil elementsUtil = new ElementsUtil();
        CssSelector cssSelector = Selectors.$(selector);
        return elementsUtil.selectElements(cssSelector);
    }

    @Override
    public Selectable $(String selector, String attrName) {
        ElementsUtil elementsUtil = new ElementsUtil();
        CssSelector cssSelector = Selectors.$(selector, attrName);
        return elementsUtil.selectElements(cssSelector);
    }

    @Override
    public List<Selectable> nodes() {
        List<Selectable> selectables = new ArrayList<Selectable>();
        for (Element element : getElements()) {
            List<Element> childElements = new ArrayList<Element>(1);
            childElements.add(element);
            selectables.add(new HtmlNode(childElements));
        }
        return selectables;
    }

    @Override
    protected List<String> getSourceTexts() {
        List<String> sourceTexts = new ArrayList<String>(getElements().size());
        for (Element element : getElements()) {
            sourceTexts.add(element.toString());
        }
        return sourceTexts;
    }
}
