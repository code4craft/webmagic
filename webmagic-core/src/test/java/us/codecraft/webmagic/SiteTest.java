package us.codecraft.webmagic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class SiteTest {

    @Test
    public void test() {
        Site site = Site.me().setDefaultCharset(StandardCharsets.UTF_8.name());
        assertEquals(StandardCharsets.UTF_8.name(), site.getDefaultCharset());
    }

    @Test
    public void addCookieTest(){
        Site site=Site.me().setDefaultCharset(StandardCharsets.UTF_8.name());
        site.addCookie("cookieDefault","cookie-webmagicDefault");
        String domain="example.com";
        site.addCookie(domain, "cookie", "cookie-webmagic");
        site.addCookie(domain, "cookieCopy", "cookie-webmagicCopy");
        Map<String, Map<String, String>> allCookies = site.getAllCookies();
        List<String> domains=new ArrayList<>();
        for(String key : allCookies.keySet()){
            domains.add(key);
        }
        assertTrue(allCookies.containsKey(domain));
        assertTrue(allCookies.get(domain).containsKey("cookie"));
        assertEquals("cookie-webmagic", allCookies.get(domain).get("cookie"));
        assertEquals("cookie-webmagicCopy", allCookies.get(domain).get("cookieCopy"));
        assertEquals(1, domains.size());
    }
}
