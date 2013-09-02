package us.codecraft.webmagic.selector;

import java.util.ArrayList;
import java.util.List;

/**
 * Selectable plain text.<br>
 *
 * @author code4crafter@gmail.com <br>
 * @since 0.1.0
 */
public class Html extends PlainText {

    public Html(List<String> strings) {
        super(strings);
    }

    public Html(String text) {
        super(text);
    }

    public static Html create(String text) {
        return new Html(text);
    }

    @Override
    protected Selectable select(Selector selector, List<String> strings) {
        List<String> results = new ArrayList<String>();
        for (String string : strings) {
            String result = selector.select(string);
            if (result != null) {
                results.add(result);
            }
        }
        return new Html(results);
    }

    @Override
    protected Selectable selectList(Selector selector, List<String> strings) {
        List<String> results = new ArrayList<String>();
        for (String string : strings) {
            List<String> result = selector.selectList(string);
            results.addAll(result);
        }
        return new Html(results);
    }

    @Override
    public Selectable smartContent() {
        SmartContentSelector smartContentSelector = Selectors.smartContent();
        return select(smartContentSelector, strings);
    }

    @Override
    public Selectable links() {
        XsoupSelector xpathSelector = new XsoupSelector("//a/@href");
        return selectList(xpathSelector, strings);
    }

    @Override
    public Selectable xpath(String xpath) {
        XsoupSelector xpathSelector = new XsoupSelector(xpath);
        return selectList(xpathSelector, strings);
    }

    @Override
    public Selectable $(String selector) {
        CssSelector cssSelector = Selectors.$(selector);
        return selectList(cssSelector, strings);
    }

    @Override
    public Selectable $(String selector, String attrName) {
        CssSelector cssSelector = Selectors.$(selector, attrName);
        return selectList(cssSelector, strings);
    }

    @Override
    public Selectable text() {
        TextContentSelector selector = Selectors.text();
        return select(selector, strings);
    }

    @Override
    public Selectable text(String newlineSeparator) {
        TextContentSelector selector = Selectors.text(newlineSeparator);
        return select(selector, strings);
    }

}
