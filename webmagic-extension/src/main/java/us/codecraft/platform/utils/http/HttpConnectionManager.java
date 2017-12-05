package us.codecraft.platform.utils.http;

import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

/**
 * http连接池
 * 
 * @author wanglong
 *
 *         Jul 12, 2017
 */
public class HttpConnectionManager {

    static PoolingHttpClientConnectionManager cm = null;

    static {
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", new PlainConnectionSocketFactory()).build();
        cm = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        cm.setMaxTotal(100);
        cm.setDefaultMaxPerRoute(10);
    }

    public CloseableHttpClient getHttpClient() {
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();
        return httpClient;
    }
}