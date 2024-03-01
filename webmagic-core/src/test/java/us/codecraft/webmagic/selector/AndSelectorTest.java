package us.codecraft.webmagic.selector;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class AndSelectorTest {

    @Test
    public void testSelectList() {
        String htmlContent = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>HTML with XPath</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"container\">\n" +
                "        <div class=\"item1\">Item 1</div>\n" +
                "        <div class=\"item2\">Item 2</div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";
        List<Selector> selectors = new ArrayList<Selector>();
        selectors.add(new CssSelector("div"));
        selectors.add(new XpathSelector("//div[@class='item1']"));
        AndSelector andSelector = new AndSelector(selectors);
        List<String> result = andSelector.selectList(htmlContent);
        assertEquals("<div class=\"item1\">\n Item 1\n</div>", result.get(0));
    }

    @Test
    public void testSelectList_NoResults() {
        String htmlContent = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>HTML with XPath</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"container\">\n" +
                "        <div class=\"item1\">Item 1</div>\n" +
                "        <div class=\"item2\">Item 2</div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";
        List<Selector> selectors = new ArrayList<Selector>();
        selectors.add(new CssSelector("div"));
        selectors.add(new XpathSelector("//div[@class='item']"));
        AndSelector andSelector = new AndSelector(selectors);
        List<String> result = andSelector.selectList(htmlContent);
        assertEquals(0, result.size());
    }
}
