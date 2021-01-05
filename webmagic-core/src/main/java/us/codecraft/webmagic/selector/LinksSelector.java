package us.codecraft.webmagic.selector;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Links selector based on jsoup. Use absolute url. <br>
 *
 * @author code4crafter@gmail.com <br>
 * @since 0.7.0
 */
public class LinksSelector extends BaseElementSelector {

    @Override
    public String select(Element element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> selectList(Element element) {
        Elements elements = element.select("a");
        List<String> links = new ArrayList<>(elements.size());
        for (Element element0 : elements) {
            if (StringUtils.isNotBlank(element0.baseUri())) {
                links.add(element0.attr("abs:href"));
            } else {
                links.add(element0.attr("href"));
            }
        }
        return links;
    }

    @Override
    public Element selectElement(Element element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Element> selectElements(Element element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasAttribute() {
        return true;
    }
}
