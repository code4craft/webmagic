package us.codecraft.webmagic.selector;

import java.util.ArrayList;
import java.util.List;

/**
 * @author code4crafter@gmail.com <br>
 *         Date: 13-4-21
 *         Time: 上午7:54
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
        SmartContentSelector smartContentSelector = SelectorFactory.getInstatnce().newSmartContentSelector();
        return select(smartContentSelector, strings);
    }

    @Override
    public Selectable links() {
        XpathSelector xpathSelector = SelectorFactory.getInstatnce().newXpathSelector("//a/@href");
        return selectList(xpathSelector, strings);
    }

    @Override
    public Selectable xpath(String xpath) {
        XpathSelector xpathSelector = SelectorFactory.getInstatnce().newXpathSelector(xpath);
        return selectList(xpathSelector, strings);
    }

}
