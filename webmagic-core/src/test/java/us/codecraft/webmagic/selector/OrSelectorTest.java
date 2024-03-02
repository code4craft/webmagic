package us.codecraft.webmagic.selector;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class OrSelectorTest {
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
        String expectedResult = "[<head>\n" +
                " <meta charset=\"UTF-8\">\n" +
                " <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                " <title>HTML with XPath</title>\n" +
                "</head>, <div class=\"item1\">\n" +
                " Item 1\n" +
                "</div>, <div class=\"item2\">\n" +
                " Item 2\n" +
                "</div>]";
        List<Selector> selectors = new ArrayList<Selector>();
        selectors.add(new CssSelector("head"));
        selectors.add(new XpathSelector("//div[@class='item1']"));
        selectors.add(new XpathSelector("//div[@class='item2']"));
        OrSelector orSelector = new OrSelector(selectors);
        List<String> result = orSelector.selectList(htmlContent);
        assertEquals(expectedResult, result.toString());
    }
}
