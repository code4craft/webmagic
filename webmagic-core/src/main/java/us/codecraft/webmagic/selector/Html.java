package us.codecraft.webmagic.selector;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Selectable html.<br>
 *
 * @author code4crafter@gmail.com <br>
 * @since 0.1.0
 */
public class Html extends PlainText {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Store parsed document for better performance when only one text exist.
     */
    private Document document;

    private boolean init = false;

    public Html(List<String> strings) {
        super(strings);
    }

    public Html(String text) {
        super(text);
    }

    /**
     * lazy init
     */
    private void initDocument() {
        if (this.document == null && !init) {
            init = true;
            //just init once whether the parsing succeeds or not
            try {
                this.document = Jsoup.parse(getText());
            } catch (Exception e) {
                logger.warn("parse document error ", e);
            }
        }
    }

    public Html(Document document) {
        super(document.html());
        this.document = document;
    }

    public static Html create(String text) {
        return new Html(text);
    }

    @Override
    protected Selectable select(Selector selector, List<String> strings) {
        initDocument();
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
        initDocument();
        List<String> results = new ArrayList<String>();
        for (String string : strings) {
            List<String> result = selector.selectList(string);
            results.addAll(result);
        }
        return new Html(results);
    }

    @Override
    public Selectable smartContent() {
        initDocument();
        SmartContentSelector smartContentSelector = Selectors.smartContent();
        return select(smartContentSelector, strings);
    }

    @Override
    public Selectable links() {
        return xpath("//a/@href");
    }

    @Override
    public Selectable xpath(String xpath) {
        XpathSelector xpathSelector = new XpathSelector(xpath);
        if (document != null) {
            return new Html(xpathSelector.selectList(document));
        }
        return selectList(xpathSelector, strings);
    }

    @Override
    public Selectable $(String selector) {
        CssSelector cssSelector = Selectors.$(selector);
        if (document != null) {
            return new Html(cssSelector.selectList(document));
        }
        return selectList(cssSelector, strings);
    }

    @Override
    public Selectable $(String selector, String attrName) {
        CssSelector cssSelector = Selectors.$(selector, attrName);
        if (document != null) {
            return new Html(cssSelector.selectList(document));
        }
        return selectList(cssSelector, strings);
    }

    public Document getDocument() {
        return document;
    }

    public String getText() {
        if (strings != null && strings.size() > 0) {
            return strings.get(0);
        }
        return document.html();
    }

    /**
     * @param selector
     * @return
     */
    public String selectDocument(Selector selector) {
        if (selector instanceof ElementSelector) {
            ElementSelector elementSelector = (ElementSelector) selector;
            return elementSelector.select(getDocument());
        } else {
            return selector.select(getText());
        }
    }

    public List<String> selectDocumentForList(Selector selector) {
        if (selector instanceof ElementSelector) {
            ElementSelector elementSelector = (ElementSelector) selector;
            return elementSelector.selectList(getDocument());
        } else {
            return selector.selectList(getText());
        }
    }
}
