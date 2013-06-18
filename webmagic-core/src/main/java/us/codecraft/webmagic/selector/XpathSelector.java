package us.codecraft.webmagic.selector;

import org.htmlcleaner.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author code4crafter@gmail.com <br>
 * Date: 13-4-21
 * Time: 上午9:39
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
                for (int i = 0; i < objects.length; i++) {
                    if (objects[i] instanceof TagNode) {
                        TagNode tagNode1 = (TagNode) objects[i];
                        results.add(htmlCleaner.getInnerHtml(tagNode1));
                    } else {
                        results.add(objects[i].toString());
                    }
                }
            }
        } catch (XPatherException e) {
            e.printStackTrace();
        }
        return results;
    }
}
