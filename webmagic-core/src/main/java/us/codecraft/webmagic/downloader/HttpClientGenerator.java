package us.codecraft.webmagic.downloader;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CookieStore;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.*;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.protocol.HttpContext;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.proxy.Proxy;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

/**
 * @author code4crafter@gmail.com <br>
 * @since 0.4.0
 */
public class HttpClientGenerator {

    private PoolingHttpClientConnectionManager connectionManager;

    public HttpClientGenerator() {
        SSLConnectionSocketFactory sslConnectionSocketFactory = null;
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();

            sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext, AllowAllHostnameVerifier.INSTANCE);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }

        Registry<ConnectionSocketFactory> reg = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.INSTANCE)
                    .register("https", sslConnectionSocketFactory)
                    .build();
        connectionManager = new PoolingHttpClientConnectionManager(reg);
        connectionManager.setDefaultMaxPerRoute(100);
    }

    public HttpClientGenerator setPoolSize(int poolSize) {
        connectionManager.setMaxTotal(poolSize);
        return this;
    }

    public CloseableHttpClient getClient(Site site, Proxy proxy) {
        return generateClient(site, proxy);
    }

    private CloseableHttpClient generateClient(Site site, Proxy proxy) {
        CredentialsProvider credsProvider = null;
        HttpClientBuilder httpClientBuilder = HttpClients.custom();
        
        if(proxy!=null && StringUtils.isNotBlank(proxy.getUser()) && StringUtils.isNotBlank(proxy.getPassword()))
        {
            credsProvider= new BasicCredentialsProvider();
            credsProvider.setCredentials(
                    new AuthScope(proxy.getHttpHost().getAddress().getHostAddress(), proxy.getHttpHost().getPort()),
                    new UsernamePasswordCredentials(proxy.getUser(), proxy.getPassword()));
            httpClientBuilder.setDefaultCredentialsProvider(credsProvider);
        }

        if(site!=null&&site.getHttpProxy()!=null&&site.getUsernamePasswordCredentials()!=null){
            credsProvider = new BasicCredentialsProvider();
            credsProvider.setCredentials(
                    new AuthScope(site.getHttpProxy()),//可以访问的范围
                    site.getUsernamePasswordCredentials());//用户名和密码
            httpClientBuilder.setDefaultCredentialsProvider(credsProvider);
        }
        
        httpClientBuilder.setConnectionManager(connectionManager);
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
        //解决post/redirect/post 302跳转问题
        httpClientBuilder.setRedirectStrategy(new CustomRedirectStrategy());
        
        SocketConfig socketConfig = SocketConfig.custom().setSoTimeout(site.getTimeOut()).setSoKeepAlive(true).setTcpNoDelay(true).build();
        httpClientBuilder.setDefaultSocketConfig(socketConfig);
        connectionManager.setDefaultSocketConfig(socketConfig);
        if (site != null) {
            httpClientBuilder.setRetryHandler(new DefaultHttpRequestRetryHandler(site.getRetryTimes(), true));
        }
        generateCookie(httpClientBuilder, site);
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
