package us.codecraft.webmagic.selector;

import org.jsoup.nodes.Element;
import us.codecraft.xsoup.XPathEvaluator;
import us.codecraft.xsoup.Xsoup;

import java.util.List;

/**
 * XPath selector based on Xsoup.<br>
 *
 * @author code4crafter@gmail.com <br>
 * @since 0.3.0
 */
public class XpathSelector extends BaseElementSelector {

    private XPathEvaluator xPathEvaluator;

    public XpathSelector(String xpathStr) {
        this.xPathEvaluator = Xsoup.compile(xpathStr);
    }

    @Override
    public String select(Element element) {
        return xPathEvaluator.evaluate(element).get();
    }

    @Override
    public List<String> selectList(Element element) {
        return xPathEvaluator.evaluate(element).list();
    }
}
