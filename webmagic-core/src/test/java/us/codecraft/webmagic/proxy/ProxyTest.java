package us.codecraft.webmagic.proxy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpHost;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * @author yxssfxwzy@sina.com May 30, 2014
 *
 */
class ProxyTest {

    private static List<String[]> httpProxyList = new ArrayList<String[]>();

    @BeforeAll
    static void before() {
        // String[] source = { "0.0.0.1:0", "0.0.0.2:0", "0.0.0.3:0",
        // "0.0.0.4:0" };
        String[] source = { "::0.0.0.1:0", "::0.0.0.2:0", "::0.0.0.3:0", "::0.0.0.4:0" };
        for (String line : source) {
            httpProxyList.add(new String[] {line.split(":")[0], line.split(":")[1], line.split(":")[2], line.split(":")[3] });
        }
    }

    class Fetch extends Thread {
        HttpHost hp;

        public Fetch(HttpHost hp) {
            this.hp = hp;
        }

        @Override
        public void run() {
            try {
                System.out.println("fetch web page use proxy: " + hp.getHostName() + ":" + hp.getPort());
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    void testCreate() {
        Proxy proxy = Proxy.create(URI.create("//127.0.0.1:8080"));
        assertNull(proxy.getScheme());
        assertNull(proxy.getUsername());
        assertNull(proxy.getPassword());
        assertEquals("127.0.0.1", proxy.getHost());
        assertEquals(8080, proxy.getPort());

        proxy = Proxy.create(URI.create("http://127.0.0.1:8080"));
        assertEquals("http", proxy.getScheme());
        assertNull(proxy.getUsername());
        assertNull(proxy.getPassword());
        assertEquals("127.0.0.1", proxy.getHost());
        assertEquals(8080, proxy.getPort());

        proxy = Proxy.create(URI.create("//username:password@127.0.0.1:8080"));
        assertNull(proxy.getScheme());
        assertEquals("username", proxy.getUsername());
        assertEquals("password", proxy.getPassword());
        assertEquals("127.0.0.1", proxy.getHost());
        assertEquals(8080, proxy.getPort());

        proxy = Proxy.create(URI.create("//username@127.0.0.1:8080"));
        assertNull(proxy.getScheme());
        assertEquals("username", proxy.getUsername());
        assertNull(proxy.getPassword());
        assertEquals("127.0.0.1", proxy.getHost());
        assertEquals(8080, proxy.getPort());

        proxy = Proxy.create(URI.create("//:password@127.0.0.1:8080"));
        assertNull(proxy.getScheme());
        assertNull(proxy.getUsername());
        assertEquals("password", proxy.getPassword());
        assertEquals("127.0.0.1", proxy.getHost());
        assertEquals(8080, proxy.getPort());
    }

    @Test
    void testEqualsHashCode() {
        var proxy0 = new Proxy("::1", 1080);
        var proxy1 = new Proxy("::1", 1080);
        assertEquals(proxy0, proxy1);
        assertEquals(proxy0.hashCode(), proxy1.hashCode());
    }

    @Test
    void testToString() {
        assertEquals("//127.0.0.1:8080", new Proxy("127.0.0.1", 8080).toString());
        assertEquals("http://127.0.0.1:8080", new Proxy("127.0.0.1", 8080, "http").toString());
        assertEquals("//username:password@127.0.0.1:8080", new Proxy("127.0.0.1", 8080, "username", "password").toString());
        assertEquals("//username@127.0.0.1:8080", new Proxy("127.0.0.1", 8080, "username", null).toString());
        assertEquals("//:password@127.0.0.1:8080", new Proxy("127.0.0.1", 8080, null, "password").toString());
    }

}
