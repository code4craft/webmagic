package us.codecraft.webmagic.selector;

import org.apache.commons.collections.CollectionUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * CSS selector. Based on Jsoup.
 *
 * @author code4crafter@gmail.com <br>
 * @since 0.1.0
 */
public class CssSelector implements Selector {

    private String selectorText;

    private String attrName;

    public CssSelector(String selectorText) {
        this.selectorText = selectorText;
    }

    public CssSelector(String selectorText, String attrName) {
        this.selectorText = selectorText;
        this.attrName = attrName;
    }

    @Override
    public String select(String text) {
        Document doc = Jsoup.parse(text);
        Elements elements = doc.select(selectorText);
        if (CollectionUtils.isEmpty(elements)) {
            return null;
        }
        return getValue(elements.get(0));
    }

    private String getValue(Element element) {
        if (attrName == null) {
            return element.outerHtml();
        } else if ("innerHtml".equalsIgnoreCase(attrName)) {
            return element.html();
        } else {
            return element.attr(attrName);
        }
    }

    @Override
    public List<String> selectList(String text) {
        List<String> strings = new ArrayList<String>();
        Document doc = Jsoup.parse(text);
        Elements elements = doc.select(selectorText);
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
}
