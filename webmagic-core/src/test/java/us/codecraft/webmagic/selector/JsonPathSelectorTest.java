package us.codecraft.webmagic.selector;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author code4crafter@gmai.com <br>
 */
public class JsonPathSelectorTest {

    private String text = "{ \"store\": {\n" +
            "    \"book\": [ \n" +
            "      { \"category\": \"reference\",\n" +
            "        \"author\": \"Nigel Rees\",\n" +
            "        \"title\": \"Sayings of the Century\",\n" +
            "        \"price\": 8.95\n" +
            "      },\n" +
            "      { \"category\": \"fiction\",\n" +
            "        \"author\": \"Evelyn Waugh\",\n" +
            "        \"title\": \"Sword of Honour\",\n" +
            "        \"price\": 12.99,\n" +
            "        \"isbn\": \"0-553-21311-3\"\n" +
            "      }\n" +
            "    ],\n" +
            "    \"bicycle\": {\n" +
            "      \"color\": \"red\",\n" +
            "      \"price\": 19.95\n" +
            "    }\n" +
            "  }\n" +
            "}";

    @Test
    public void testJsonPath() {
        JsonPathSelector jsonPathSelector = new JsonPathSelector("$.store.book[*].author");
        String select = jsonPathSelector.select(text);
        List<String> list = jsonPathSelector.selectList(text);
        assertThat(select).isEqualTo("Nigel Rees");
        assertThat(list).contains("Nigel Rees","Evelyn Waugh");
        jsonPathSelector = new JsonPathSelector("$.store.book[?(@.category == 'reference')]");
        list = jsonPathSelector.selectList(text);
        select = jsonPathSelector.select(text);
        assertThat(select).isEqualTo("{\"author\":\"Nigel Rees\",\"title\":\"Sayings of the Century\",\"category\":\"reference\",\"price\":8.95}");
        assertThat(list).contains("{\"author\":\"Nigel Rees\",\"title\":\"Sayings of the Century\",\"category\":\"reference\",\"price\":8.95}");
    }
}
