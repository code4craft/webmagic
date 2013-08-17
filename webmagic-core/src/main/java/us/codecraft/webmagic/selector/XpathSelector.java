package us.codecraft.webmagic.selector;

import org.htmlcleaner.*;

import java.util.ArrayList;
import java.util.List;

/**
 * XPath selector based on HtmlCleaner。<br>
 *
 * @author code4crafter@gmail.com <br>
 * @since 0.1.0
 */
public class XpathSelector implements Selector {

    private String xpathStr;

    public XpathSelector(String xpathStr) {
        this.xpathStr = xpathStr;
    }

    @Override
    public String select(String text) {
        HtmlCleaner htmlCleaner = new HtmlCleaner();
        TagNode tagNode = htmlCleaner.clean(text);
        if (tagNode == null) {
            return null;
        }
        try {
            Object[] objects = tagNode.evaluateXPath(xpathStr);
            if (objects != null && objects.length >= 1) {
                if (objects[0] instanceof TagNode) {
                    TagNode tagNode1 = (TagNode) objects[0];
                    return htmlCleaner.getInnerHtml(tagNode1);
                } else {
                    return objects[0].toString();
                }
            }
        } catch (XPatherException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<String> selectList(String text) {
        HtmlCleaner htmlCleaner = new HtmlCleaner();
        TagNode tagNode = htmlCleaner.clean(text);
        if (tagNode == null) {
            return null;
        }
        List<String> results = new ArrayList<String>();
        try {
            Object[] objects = tagNode.evaluateXPath(xpathStr);
            if (objects != null && objects.length >= 1) {
                for (Object object : objects) {
                    if (object instanceof TagNode) {
                        TagNode tagNode1 = (TagNode) object;
                        results.add(htmlCleaner.getInnerHtml(tagNode1));
                    } else {
                        results.add(object.toString());
                    }
                }
            }
        } catch (XPatherException e) {
            e.printStackTrace();
        }
        return results;
    }
}
