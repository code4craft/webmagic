package us.codecraft.webmagic.downloader;

import org.apache.http.HttpVersion;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.params.*;
import us.codecraft.webmagic.Site;

import java.util.Map;

/**
 * @author code4crafter@gmail.com <br>
 * @since 0.1.0
 */
public class HttpClientPool {

    public static volatile HttpClientPool INSTANCE;

    public static HttpClientPool getInstance(int poolSize) {
        if (INSTANCE == null) {
            synchronized (HttpClientPool.class) {
                if (INSTANCE == null) {
                    INSTANCE = new HttpClientPool(poolSize);
                }
            }
        }
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
        if (site != null && site.getUserAgent() != null) {
            params.setParameter(CoreProtocolPNames.USER_AGENT, site.getUserAgent());
            params.setIntParameter(CoreConnectionPNames.SO_TIMEOUT, site.getTimeOut());
            params.setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, site.getTimeOut());
        } else {
            params.setIntParameter(CoreConnectionPNames.SO_TIMEOUT, 3000);
            params.setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 3000);
        }


        params.setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.BEST_MATCH);
        HttpProtocolParamBean paramsBean = new HttpProtocolParamBean(params);
        paramsBean.setVersion(HttpVersion.HTTP_1_1);
        if (site != null && site.getCharset() != null) {
            paramsBean.setContentCharset(site.getCharset());
        }
        paramsBean.setUseExpectContinue(false);

        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
        schemeRegistry.register(new Scheme("https", 443, SSLSocketFactory.getSocketFactory()));

        PoolingClientConnectionManager connectionManager = new PoolingClientConnectionManager(schemeRegistry);
        connectionManager.setMaxTotal(poolSize);
        connectionManager.setDefaultMaxPerRoute(100);
        DefaultHttpClient httpClient = new DefaultHttpClient(connectionManager, params);
        if (site != null) {
            generateCookie(httpClient, site);
        }
        return httpClient;
    }

    private void generateCookie(DefaultHttpClient httpClient, Site site) {
        CookieStore cookieStore = new BasicCookieStore();
        if (site.getCookies() != null) {
            for (Map.Entry<String, String> cookieEntry : site.getCookies().entrySet()) {
                BasicClientCookie cookie = new BasicClientCookie(cookieEntry.getKey(), cookieEntry.getValue());
                cookie.setDomain(site.getDomain());
                cookieStore.addCookie(cookie);
            }
        }
        httpClient.setCookieStore(cookieStore);
    }

}
