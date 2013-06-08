package us.codecraft.webmagic.selector;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * User: cairne
 * Date: 13-4-21
 * Time: 上午7:54
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

    @Override
    public Selectable x(String xpath) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Selectable xs(String xpath) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Selectable sc() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Selectable a() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Selectable as() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Selectable r(String regex) {
        RegexSelector regexSelector = SelectorFactory.getInstatnce().newRegexSelector(regex);
        return select(regexSelector, strings);
    }

    @Override
    public Selectable rs(String regex) {
        RegexSelector regexSelector = SelectorFactory.getInstatnce().newRegexSelector(regex);
        return selectList(regexSelector, strings);
    }

    protected Selectable select(Selector selector, List<String> strings) {
        List<String> results = new ArrayList<String>();
        for (String string : strings) {
            String result = selector.select(string);
            if (result!=null){
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
    public Selectable rp(String regex, String replacement) {
        ReplaceSelector replaceSelector = SelectorFactory.getInstatnce().newReplaceSelector(regex, replacement);
        return select(replaceSelector, strings);
    }

    @Override
    public List<String> toStrings() {
        return strings;
    }

    @Override
    public String toString() {
        if (CollectionUtils.isNotEmpty(toStrings())) {
            return toStrings().get(0);
        } else {
            return null;
        }
    }
}
