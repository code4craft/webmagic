package us.codecraft.webmagic.selector;

import java.util.ArrayList;
import java.util.List;

/**
 * Selectable plain text.<br>
 * Can not be selected by XPath or CSS Selector.
 *
 * @author code4crafter@gmail.com <br>
 * @since 0.1.0
 */
public class PlainText extends AbstractSelectable {

    public PlainText(List<String> strings) {
        super(strings);
    }

    public PlainText(String text) {
       super(text);
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
    public Selectable smartContent() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Selectable links() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Selectable> nodes() {
        List<Selectable> nodes = new ArrayList<Selectable>(strings.size());
        for (String string : strings) {
            nodes.add(PlainText.create(string));
        }
        return nodes;
    }

}
