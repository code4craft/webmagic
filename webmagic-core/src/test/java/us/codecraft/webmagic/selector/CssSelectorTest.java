package us.codecraft.webmagic.selector;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;
import static org.junit.Assert.*;

public class CssSelectorTest {

    @Test
    public void testSelectElement() {
        CssSelector cssSelector = new CssSelector("div");
        String htmlContent = "<html><head><title>Dummy Page</title></head><body><div id=\"dummyDiv\">Hello World!</div></body></html>";
        Document doc = Jsoup.parse(htmlContent);
        Element dummyElement = doc.getElementById("dummyDiv");
        Element resultElement = cssSelector.selectElement(dummyElement);
        assertNotNull(resultElement);
    }

    @Test
    public void testSelectList() {
        CssSelector cssSelector = new CssSelector("div");
        String htmlContent = "<html><head><title>Dummy Page</title></head><body><div id=\"dummyDiv\">Hello World!</div></body></html>";
        Document doc = Jsoup.parse(htmlContent);
        Element dummyElement = doc.getElementById("dummyDiv");
        List<String> result = cssSelector.selectList(dummyElement);
        assertEquals(1, result.size());
        assertEquals("[<div id=\"dummyDiv\">\n Hello World!\n</div>]", result.toString());
    }

}
