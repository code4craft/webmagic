package us.codecraft.webmagic.downloader;

import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.*;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.protocol.HttpContext;
import us.codecraft.webmagic.Site;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.util.Map;

/**
 * @author code4crafter@gmail.com <br>
 * @since 0.4.0
 */
public class HttpClientGenerator {

    private PoolingHttpClientConnectionManager connectionManager;
    private int maxpoolSize = 1;

    public HttpClientGenerator() {
        Registry<ConnectionSocketFactory> reg = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", SSLConnectionSocketFactory.getSocketFactory())
                .build();
        connectionManager = new PoolingHttpClientConnectionManager(reg);
        connectionManager.setDefaultMaxPerRoute(100);
    }

    public HttpClientGenerator setPoolSize(int poolSize) {
        maxpoolSize = poolSize;
        connectionManager.setMaxTotal(poolSize);
        return this;
    }

    public CloseableHttpClient getClient(Site site) {
        return generateClient(site);
    }

    private CloseableHttpClient generateClient(Site site) {


        HttpClientBuilder httpClientBuilder = HttpClients.custom().setConnectionManager(connectionManager);
        if (site != null && site.isEnableSSL() == true) //无信任证书使用https
        {

            try {
                RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.BROWSER_COMPATIBILITY).build();
                SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(null, new TrustSelfSignedStrategy()).build();
                SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[]{"TLSv1"}, null, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
                httpClientBuilder = HttpClients.custom().setDefaultRequestConfig(globalConfig).setSSLSocketFactory(sslsf).setMaxConnTotal(maxpoolSize).setMaxConnPerRoute(100);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        if (site != null && site.getUserAgent() != null) {
            httpClientBuilder.setUserAgent(site.getUserAgent());
        } else {
            httpClientBuilder.setUserAgent("");
        }
        if (site == null || site.isUseGzip()) {
            httpClientBuilder.addInterceptorFirst(new HttpRequestInterceptor() {

                public void process(
                        final HttpRequest request,
                        final HttpContext context) throws HttpException, IOException {
                    if (!request.containsHeader("Accept-Encoding")) {
                        request.addHeader("Accept-Encoding", "gzip");
                    }

                }
            });
        }
        try {

            SocketConfig socketConfig = SocketConfig.custom().setSoKeepAlive(true).setTcpNoDelay(true).build();

            httpClientBuilder.setDefaultSocketConfig(socketConfig);
            if (site != null) {
                httpClientBuilder.setRetryHandler(new DefaultHttpRequestRetryHandler(site.getRetryTimes(), true));
            }

            generateCookie(httpClientBuilder, site);
        } finally {

        }
        return httpClientBuilder.build();
    }

    private void generateCookie(HttpClientBuilder httpClientBuilder, Site site) {
        CookieStore cookieStore = new BasicCookieStore();

        for (Map.Entry<String, String> cookieEntry : site.getCookies().entrySet()) {
            BasicClientCookie cookie = new BasicClientCookie(cookieEntry.getKey(), cookieEntry.getValue());
            cookie.setDomain(site.getDomain());
            cookieStore.addCookie(cookie);
        }
        for (Map.Entry<String, Map<String, String>> domainEntry : site.getAllCookies().entrySet()) {
            for (Map.Entry<String, String> cookieEntry : domainEntry.getValue().entrySet()) {
                BasicClientCookie cookie = new BasicClientCookie(cookieEntry.getKey(), cookieEntry.getValue());
                cookie.setDomain(domainEntry.getKey());
                cookieStore.addCookie(cookie);
            }
        }
        httpClientBuilder.setDefaultCookieStore(cookieStore);

    }

}
