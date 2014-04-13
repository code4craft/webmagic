package us.codecraft.webmagic.selector;

import org.junit.Test;
import us.codecraft.webmagic.Page;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author code4crafter@gmai.com
 * @since 0.5.0
 */
public class JsonTest {

    private String text = "callback({\"name\":\"json\"})";

    @Test
    public void testRemovePadding() throws Exception {
        String name = new Json(text).removePadding("callback").jsonPath("$.name").get();
        assertThat(name).isEqualTo("json");
        Page page = null;

        page.getJson().jsonPath("$.name").get();
        page.getJson().removePadding("callback").jsonPath("$.name").get();
    }
}
