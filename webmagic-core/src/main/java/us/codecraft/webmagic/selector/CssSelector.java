package us.codecraft.webmagic.selector;


import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

/**
 * CSS selector. Based on Jsoup.
 *
 * @author code4crafter@gmail.com <br>
 * @since 0.1.0
 */
public class CssSelector extends BaseElementSelector {

    private String selectorText;

    private String attrName;

    public CssSelector(String selectorText) {
        this.selectorText = selectorText;
    }

    public CssSelector(String selectorText, String attrName) {
        this.selectorText = selectorText;
        this.attrName = attrName;
    }

    private String getValue(Element element) {
        if (attrName == null) {
            return element.outerHtml();
        } else if ("innerHtml".equalsIgnoreCase(attrName)) {
            return element.html();
        } else if ("text".equalsIgnoreCase(attrName)) {
            return getText(element);
        } else if ("allText".equalsIgnoreCase(attrName)) {
            return element.text();
        } else {
            return element.attr(attrName);
        }
    }

    protected String getText(Element element) {
        StringBuilder accum = new StringBuilder();
        for (Node node : element.childNodes()) {
            if (node instanceof TextNode) {
                TextNode textNode = (TextNode) node;
                accum.append(textNode.text());
            }
        }
        return accum.toString();
    }

    @Override
    public String select(Element element) {
        List<Element> elements = selectElements(element);
        if (CollectionUtils.isEmpty(elements)) {
            return null;
        }
        return getValue(elements.get(0));
    }

    @Override
    public List<String> selectList(Element doc) {
        List<String> strings = new ArrayList<String>();
        List<Element> elements = selectElements(doc);
        if (CollectionUtils.isNotEmpty(elements)) {
            for (Element element : elements) {
                String value = getValue(element);
                if (value != null) {
                    strings.add(value);
                }
            }
        }
        return strings;
    }

    @Override
    public Element selectElement(Element element) {
        Elements elements = element.select(selectorText);
        if (CollectionUtils.isNotEmpty(elements)) {
            return elements.get(0);
        }
        return null;
    }

    @Override
    public List<Element> selectElements(Element element) {
        return element.select(selectorText);
    }

    @Override
    public boolean hasAttribute() {
        return attrName != null;
    }
}
