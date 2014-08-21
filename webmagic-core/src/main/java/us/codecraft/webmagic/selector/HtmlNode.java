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
        return xpath("//a/@href");
    }

    @Override
    public Selectable xpath(String xpath) {
        XpathSelector xpathSelector = Selectors.xpath(xpath);
        return selectElements(xpathSelector);
    }

    @Override
    public Selectable selectList(Selector selector) {
        if (selector instanceof BaseElementSelector) {
           return selectElements((BaseElementSelector) selector);
        }
        return selectList(selector, getSourceTexts());
    }

    @Override
    public Selectable select(Selector selector) {
        return selectList(selector);
    }

    /**
     * select elements
     *
     * @param elementSelector
     * @return
     */
    protected Selectable selectElements(BaseElementSelector elementSelector) {
        ListIterator<Element> elementIterator = getElements().listIterator();
        if (!elementSelector.hasAttribute()) {
            List<Element> resultElements = new ArrayList<Element>();
            while (elementIterator.hasNext()) {
                Element element = checkElementAndConvert(elementIterator);
                List<Element> selectElements = elementSelector.selectElements(element);
                resultElements.addAll(selectElements);
            }
            return new HtmlNode(resultElements);
        } else {
            // has attribute, consider as plaintext
            List<String> resultStrings = new ArrayList<String>();
            while (elementIterator.hasNext()) {
                Element element = checkElementAndConvert(elementIterator);
                List<String> selectList = elementSelector.selectList(element);
                resultStrings.addAll(selectList);
            }
            return new PlainText(resultStrings);

        }
    }

    /**
     * Only document can be select
     * See: https://github.com/code4craft/webmagic/issues/113
     *
     * @param elementIterator
     * @param element
     */
    private Element checkElementAndConvert(ListIterator<Element> elementIterator) {
        Element element = elementIterator.next();
        if (!(element instanceof Document)) {
            Document root = new Document(element.ownerDocument().baseUri());
            Element clone = element.clone();
            root.appendChild(clone);
            elementIterator.set(root);
            return root;
        }
        return element;
    }

    @Override
    public Selectable $(String selector) {
        CssSelector cssSelector = Selectors.$(selector);
        return selectElements(cssSelector);
    }

    @Override
    public Selectable $(String selector, String attrName) {
        CssSelector cssSelector = Selectors.$(selector, attrName);
        return selectElements(cssSelector);
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
