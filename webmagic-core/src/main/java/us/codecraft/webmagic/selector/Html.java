package us.codecraft.webmagic.selector;

import java.util.ArrayList;
import java.util.List;

/**
 * User: cairne
 * Date: 13-4-21
 * Time: 上午7:54
 */
public class Html extends PlainText {

    public Html(List<String> strings) {
        super(strings);
    }

    public Html(String text) {
        super(text);
    }

    @Override
    public Selectable x(String xpath) {
        XpathSelector xpathSelector = SelectorFactory.getInstatnce().newXpathSelector(xpath);
        return select(xpathSelector,strings);
    }

    @Override
    protected Selectable select(Selector selector, List<String> strings) {
        List<String> results = new ArrayList<String>();
        for (String string : strings) {
            String result = selector.select(string);
            if (result!=null){
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
    public Selectable sc() {
        SmartContentSelector smartContentSelector = SelectorFactory.getInstatnce().newSmartContentSelector();
        return select(smartContentSelector,strings);
    }

    @Override
    public Selectable a() {
        XpathSelector xpathSelector = SelectorFactory.getInstatnce().newXpathSelector("//a/@href");
        return select(xpathSelector,strings);
    }

    @Override
    public Selectable as() {
        XpathSelector xpathSelector = SelectorFactory.getInstatnce().newXpathSelector("//a/@href");
        return selectList(xpathSelector,strings);
    }

    @Override
    public Selectable xs(String xpath) {
        XpathSelector xpathSelector = SelectorFactory.getInstatnce().newXpathSelector(xpath);
        return selectList(xpathSelector, strings);
    }

}
