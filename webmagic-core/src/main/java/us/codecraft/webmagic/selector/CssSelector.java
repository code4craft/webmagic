package us.codecraft.webmagic.selector;

import org.apache.commons.collections.CollectionUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * css风格的选择器。包装了Jsoup。<br>
 * @author code4crafter@gmail.com <br>
 *         Date: 13-4-21
 *         Time: 上午9:39
 */
public class CssSelector implements Selector {

    private String selectorText;

    public CssSelector(String selectorText) {
        this.selectorText = selectorText;
    }

    @Override
    public String select(String text) {
        Document doc = Jsoup.parse(text);
        Elements elements = doc.select(selectorText);
        if (CollectionUtils.isNotEmpty(elements)) {
            return null;
        }
        return elements.get(0).outerHtml();
    }

    @Override
    public List<String> selectList(String text) {
        List<String> strings = new ArrayList<String>();
        Document doc = Jsoup.parse(text);
        Elements elements = doc.select(selectorText);
        if (CollectionUtils.isNotEmpty(elements)) {
            for (Element element : elements) {
                strings.add(element.outerHtml());
            }
        }
        return strings;
    }
}
