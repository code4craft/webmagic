package us.codecraft.webmagic.selector;

import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * select elements
     *
     * @param elementSelector
     * @return
     */
    protected Selectable selectElements(BaseElementSelector elementSelector) {
        if (!elementSelector.hasAttribute()) {
            List<Element> resultElements = new ArrayList<Element>();
            for (Element element : getElements()) {
                List<Element> selectElements = elementSelector.selectElements(element);
                resultElements.addAll(selectElements);
            }
            return new HtmlNode(resultElements);
        } else {
            // has attribute, consider as plaintext
            List<String> resultStrings = new ArrayList<String>();
            for (Element element : getElements()) {
                List<String> selectList = elementSelector.selectList(element);
                resultStrings.addAll(selectList);
            }
            return new PlainText(resultStrings);

        }
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
        ArrayList<Selectable> selectables = new ArrayList<Selectable>();
        selectables.add(this);
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
