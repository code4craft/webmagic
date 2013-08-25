package us.codecraft.webmagic.selector;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Extract text content in html.<br>
 * Algorithm from <a href="http://www.elias.cn/En/ExtMainText">http://www.elias.cn/En/ExtMainText</a>. <br>
 *
 * @author code4crafter@gmail.com <br>
 * @since 0.2.2
 */
public class TextContentSelector implements Selector {

    private String newLineSeperator = "\n";

    public TextContentSelector() {
    }

    public TextContentSelector(String newLineSeperator) {
        this.newLineSeperator = newLineSeperator;
    }

    private final static Set<String> TAGS_IN_NEWLINE = new HashSet<String>();

    private final static Set<String> TAGS_TO_IGNORE = new HashSet<String>();

    static {
        TAGS_IN_NEWLINE.addAll(Arrays.asList(new String[]{"p", "div", "h1", "h2", "h3", "h4", "h5", "h6", "br", "li"}));
        TAGS_TO_IGNORE.addAll(Arrays.asList(new String[]{"head", "style", "script", "noscript", "option"}));
    }

    @Override
    public String select(String text) {
        Document doc = Jsoup.parse(text);
        return select0(doc);
    }

    protected String select0(Element element) {
        String tagName = element.tagName().toLowerCase();
        if (TAGS_TO_IGNORE.contains(tagName)) {
            return "";
        }
        StringBuilder textBuilder = new StringBuilder();
        textBuilder.append(element.text());
        if (element.children() != null) {
            for (Element child : element.children()) {
                textBuilder.append(select0(child));
            }
        }
        if (TAGS_IN_NEWLINE.contains(tagName)) {
            textBuilder.append(newLineSeperator);
        }
        return textBuilder.toString();
    }

    @Override
    public List<String> selectList(String text) {
        throw new UnsupportedOperationException();
    }

}
