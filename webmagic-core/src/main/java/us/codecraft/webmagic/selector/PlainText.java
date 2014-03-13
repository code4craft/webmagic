package us.codecraft.webmagic.selector;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Selectable plain text.<br>
 * Can not be selected by XPath or CSS Selector.
 *
 * @author code4crafter@gmail.com <br>
 * @since 0.1.0
 */
public class PlainText implements Selectable {

    protected List<String> strings;

    public PlainText(List<String> strings) {
        this.strings = strings;
    }

    public PlainText(String text) {
        List<String> results = new ArrayList<String>();
        results.add(text);
        this.strings = results;
    }

    public static PlainText create(String text) {
        return new PlainText(text);
    }

    @Override
    public Selectable xpath(String xpath) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Selectable $(String selector) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Selectable $(String selector, String attrName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Selectable css(String selector) {
        return $(selector);
    }

    @Override
    public Selectable css(String selector, String attrName) {
        return $(selector, attrName);
    }

    @Override
    public Selectable smartContent() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Selectable links() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Selectable regex(String regex) {
        RegexSelector regexSelector = Selectors.regex(regex);
        return selectList(regexSelector, strings);
    }

    @Override
    public Selectable regex(String regex, int group) {
        RegexSelector regexSelector = Selectors.regex(regex, group);
        return selectList(regexSelector, strings);
    }

    protected Selectable select(Selector selector, List<String> strings) {
        List<String> results = new ArrayList<String>();
        for (String string : strings) {
            String result = selector.select(string);
            if (result != null) {
                results.add(result);
            }
        }
        return new PlainText(results);
    }

    protected Selectable selectList(Selector selector, List<String> strings) {
        List<String> results = new ArrayList<String>();
        for (String string : strings) {
            List<String> result = selector.selectList(string);
            results.addAll(result);
        }
        return new PlainText(results);
    }

    @Override
    public Selectable replace(String regex, String replacement) {
        ReplaceSelector replaceSelector = new ReplaceSelector(regex,replacement);
        return select(replaceSelector, strings);
    }

    @Override
    public List<String> all() {
        return strings;
    }

    @Override
    public String get() {
        if (CollectionUtils.isNotEmpty(all())) {
            return all().get(0);
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return get();
    }

    @Override
    public boolean match() {
        return strings != null && strings.size() > 0;
    }
}
