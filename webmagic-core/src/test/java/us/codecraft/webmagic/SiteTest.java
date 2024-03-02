package us.codecraft.webmagic;

import static org.junit.Assert.assertEquals;
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
        String firstDomain="example.com";
        String secondDomain="exampleCopy.com";
        site.addCookie(firstDomain, "cookie", "cookie-webmagic");
        site.addCookie(firstDomain, "cookieCopy", "cookie-webmagicCopy");
        site.addCookie(secondDomain, "cookie", "cookie-webmagic");
        Map<String, Map<String, String>> allCookies = site.getAllCookies();
        List<String> domains=new ArrayList<>();
        for(String key : allCookies.keySet()){
            domains.add(key);
        }
        assertEquals("cookie-webmagic", allCookies.get(firstDomain).get("cookie"));
        assertEquals("cookie-webmagicCopy", allCookies.get(firstDomain).get("cookieCopy"));
        assertEquals("cookie-webmagic", allCookies.get(secondDomain).get("cookie"));
        assertEquals(2, domains.size());
    }
}
