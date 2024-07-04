package us.codecraft.webmagic;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.Map;

import org.junit.Test;

import us.codecraft.webmagic.utils.HttpConstant;

/**
 * @author code4crafter@gmail.com
 *         Date: 17/3/11
 */
public class RequestTest {

    @Test
    public void testEqualsAndHashCode() throws Exception {
        Request requestA = new Request("http://www.google.com/");
        Request requestB = new Request("http://www.google.com/");
        assertThat(requestA.hashCode()).isEqualTo(requestB.hashCode());
        assertThat(requestA).isEqualTo(requestB);
        requestA.setMethod(HttpConstant.Method.GET);
        requestA.setMethod(HttpConstant.Method.POST);
        assertThat(requestA).isNotEqualTo(requestB);
        assertThat(requestA.hashCode()).isNotEqualTo(requestB.hashCode());
    }

    @Test
    public void testSetExtras() {
        Request request = new Request();
        Map<String, Object> extras = Collections.singletonMap("a", "1");
        request.setExtras(extras);
        request.putExtra("b", "2");
        assertThat(request.<String>getExtra("a")).isEqualTo("1");
        assertThat(request.<String>getExtra("b")).isEqualTo("2");
    }

    @Test
    public void testGetExtras() {
        Request request = new Request();
        request.putExtra("a", "1");
        assertThat(request.getExtras()).containsEntry("a", "1");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetExtrasShouldBeUnmodifiable() {
        Request request = new Request();
        request.getExtras().put("a", "1");
    }

}
