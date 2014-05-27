package us.codecraft.webmagic.selector;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author code4crafer@gmail.com
 * @since 0.5.2
 */
public abstract class AbstractSelectable implements Selectable {

    protected List<String> strings;

    public AbstractSelectable(String text) {
        List<String> results = new ArrayList<String>();
        results.add(text);
        this.strings = results;
    }

    public AbstractSelectable(List<String> strings) {
        this.strings = strings;
    }

    @Override
    public Selectable css(String selector) {
        return $(selector);
    }

    @Override
    public Selectable css(String selector, String attrName) {
        return $(selector, attrName);
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
    public List<String> all() {
        return strings;
    }

    @Override
    public Selectable jsonPath(String jsonPath) {
        throw new UnsupportedOperationException();
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
    public Selectable select(Selector selector) {
        return select(selector, strings);
    }

    @Override
    public Selectable selectList(Selector selector) {
        return selectList(selector, strings);
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

    @Override
    public Selectable replace(String regex, String replacement) {
        ReplaceSelector replaceSelector = new ReplaceSelector(regex,replacement);
        return select(replaceSelector, strings);
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
