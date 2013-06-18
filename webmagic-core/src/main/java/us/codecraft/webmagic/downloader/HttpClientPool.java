package us.codecraft.webmagic.downloader;

import org.apache.http.HttpVersion;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.params.*;
import us.codecraft.webmagic.Site;

import java.util.Map;

/**
 * Author: code4crafter@gmail.com
 * Date: 13-4-21
 * Time: 下午12:29
 */
public class HttpClientPool {

    public static final HttpClientPool INSTANCE = new HttpClientPool(5);

    public static HttpClientPool getInstance() {
        return INSTANCE;
    }

    private int poolSize;

    private HttpClientPool(int poolSize) {
        this.poolSize = poolSize;
    }

    public HttpClient getClient(Site site) {
        return generateClient(site);
    }

    private HttpClient generateClient(Site site) {
        HttpParams params = new BasicHttpParams();
        params.setParameter(CoreProtocolPNames.USER_AGENT, site.getUserAgent());
        params.setIntParameter(CoreConnectionPNames.SO_TIMEOUT, 1000);
        params.setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 2000);

        HttpProtocolParamBean paramsBean = new HttpProtocolParamBean(params);
        paramsBean.setVersion(HttpVersion.HTTP_1_1);
        paramsBean.setContentCharset("UTF-8");
        paramsBean.setUseExpectContinue(false);

        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));

        PoolingClientConnectionManager connectionManager = new PoolingClientConnectionManager(schemeRegistry);
        connectionManager.setMaxTotal(poolSize);
        connectionManager.setDefaultMaxPerRoute(100);
        DefaultHttpClient httpClient = new DefaultHttpClient(connectionManager, params);
        generateCookie(httpClient, site);
        httpClient.getParams().setIntParameter("http.socket.timeout", 60000);
        httpClient.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.BEST_MATCH);
        return httpClient;
    }

    private void generateCookie(DefaultHttpClient httpClient, Site site) {
        CookieStore cookieStore = new BasicCookieStore();
        for (Map.Entry<String, String> cookieEntry : site.getCookies().entrySet()) {
            BasicClientCookie cookie = new BasicClientCookie(cookieEntry.getKey(), cookieEntry.getValue());
            cookie.setDomain(site.getDomain());
            cookieStore.addCookie(cookie);
        }
        httpClient.setCookieStore(cookieStore);
    }

}
