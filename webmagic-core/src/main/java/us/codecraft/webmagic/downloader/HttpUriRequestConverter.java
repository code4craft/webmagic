package us.codecraft.webmagic.downloader;

import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.message.BasicNameValuePair;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.utils.HttpConstant;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author code4crafter@gmail.com
 *         Date: 17/3/18
 *         Time: 上午11:28
 */
public class HttpUriRequestConverter {

    public HttpUriRequest convert(Request request, Site site, Proxy proxy) {
        RequestBuilder requestBuilder = selectRequestMethod(request).setUri(request.getUrl());
        if (site.getHeaders() != null) {
            for (Map.Entry<String, String> headerEntry : site.getHeaders().entrySet()) {
                requestBuilder.addHeader(headerEntry.getKey(), headerEntry.getValue());
            }
        }

        RequestConfig.Builder requestConfigBuilder = RequestConfig.custom();
        if (site != null) {
            requestConfigBuilder.setConnectionRequestTimeout(site.getTimeOut())
                    .setSocketTimeout(site.getTimeOut())
                    .setConnectTimeout(site.getTimeOut())
                    .setCookieSpec(CookieSpecs.BEST_MATCH);
        }

        if (proxy != null) {
            requestConfigBuilder.setProxy(new HttpHost(proxy.getHost(), proxy.getPort()));
        }
        requestBuilder.setConfig(requestConfigBuilder.build());
        return requestBuilder.build();
    }

    private RequestBuilder selectRequestMethod(Request request) {
        String method = request.getMethod();
        if (method == null || method.equalsIgnoreCase(HttpConstant.Method.GET)) {
            //default get
            return addQueryParams(RequestBuilder.get(),request.getParams());
        } else if (method.equalsIgnoreCase(HttpConstant.Method.POST)) {
            return addFormParams(RequestBuilder.post(), (NameValuePair[]) request.getExtra("nameValuePair"), request.getParams());
        } else if (method.equalsIgnoreCase(HttpConstant.Method.HEAD)) {
            return addQueryParams(RequestBuilder.head(),request.getParams());
        } else if (method.equalsIgnoreCase(HttpConstant.Method.PUT)) {
            return addFormParams(RequestBuilder.put(), (NameValuePair[]) request.getExtra("nameValuePair"), request.getParams());
        } else if (method.equalsIgnoreCase(HttpConstant.Method.DELETE)) {
            return addQueryParams(RequestBuilder.delete(),request.getParams());
        } else if (method.equalsIgnoreCase(HttpConstant.Method.TRACE)) {
            return addQueryParams(RequestBuilder.trace(),request.getParams());
        }
        throw new IllegalArgumentException("Illegal HTTP Method " + method);
    }

    private RequestBuilder addFormParams(RequestBuilder requestBuilder, NameValuePair[] nameValuePair, Map<String, String> params) {
        List<NameValuePair> allNameValuePair=new ArrayList<NameValuePair>();
        if (nameValuePair != null && nameValuePair.length > 0) {
            allNameValuePair= Arrays.asList(nameValuePair);
        }
        if (params != null) {
            for (String key : params.keySet()) {
                allNameValuePair.add(new BasicNameValuePair(key, params.get(key)));
            }
        }
        requestBuilder.setEntity(new UrlEncodedFormEntity(allNameValuePair, Charset.forName("utf8")));
        return requestBuilder;
    }

    private RequestBuilder addQueryParams(RequestBuilder requestBuilder, Map<String, String> params) {
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                requestBuilder.addParameter(entry.getKey(), entry.getValue());
            }
        }
        return requestBuilder;
    }

}
