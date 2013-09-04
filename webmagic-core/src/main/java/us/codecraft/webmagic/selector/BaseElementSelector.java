package us.codecraft.webmagic.selector;

import org.jsoup.Jsoup;

import java.util.List;

/**
 * @author code4crafter@gmail.com
 * @since 0.3.0
 */
public abstract class BaseElementSelector implements Selector,ElementSelector {

    @Override
    public String select(String text) {
        return select(Jsoup.parse(text));
    }

    @Override
    public List<String> selectList(String text) {
        return selectList(Jsoup.parse(text));
    }

}
