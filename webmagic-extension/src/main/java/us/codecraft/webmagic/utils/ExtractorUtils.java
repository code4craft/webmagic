package us.codecraft.webmagic.utils;

import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.selector.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Tools for annotation converting. <br>
 *
 * @author code4crafter@gmail.com <br>
 * @since 0.2.1
 */
public class ExtractorUtils {

    public static Selector getSelector(ExtractBy extractBy) {
        String value = extractBy.value();
        Selector selector;
        switch (extractBy.type()) {
            case Css:
                selector = new CssSelector(value);
                break;
            case Regex:
                selector = new RegexSelector(value);
                break;
            case XPath:
                selector = getXpathSelector(value);
                break;
            case JsonPath:
                selector = new JsonPathSelector(value);
                break;
            default:
                selector = getXpathSelector(value);
        }
        return selector;
    }
    
    public static Selector getSelector(String type, String value) {
        Selector selector;
        String t = type.toLowerCase();
        switch (t) {
            case "css":
                selector = new CssSelector(value);
                break;
            case "regex":
                selector = new RegexSelector(value);
                break;
            case "xpath":
                selector = getXpathSelector(value);
                break;
            case "jsonpath":
                selector = new JsonPathSelector(value);
                break;
            default:
                selector = getXpathSelector(value);
        }
        return selector;
    }

    private static Selector getXpathSelector(String value) {
        Selector selector = new XpathSelector(value);
        return selector;
    }

    public static List<Selector> getSelectors(ExtractBy[] extractBies) {
        List<Selector> selectors = new ArrayList<Selector>();
        if (extractBies == null) {
            return selectors;
        }
        for (ExtractBy extractBy : extractBies) {
            selectors.add(getSelector(extractBy));
        }
        return selectors;
    }
}
