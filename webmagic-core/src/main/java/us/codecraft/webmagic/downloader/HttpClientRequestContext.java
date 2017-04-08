package us.codecraft.webmagic.downloader;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.HttpClientContext;

/**
 * @author code4crafter@gmail.com
 *         Date: 17/4/8
 *         Time: 19:43
 * @since 0.7.0
 */
public class HttpClientRequestContext {

    private HttpUriRequest httpUriRequest;

    private HttpClientContext httpClientContext;

    public HttpUriRequest getHttpUriRequest() {
        return httpUriRequest;
    }

    public void setHttpUriRequest(HttpUriRequest httpUriRequest) {
        this.httpUriRequest = httpUriRequest;
    }

    public HttpClientContext getHttpClientContext() {
        return httpClientContext;
    }

    public void setHttpClientContext(HttpClientContext httpClientContext) {
        this.httpClientContext = httpClientContext;
    }

}
