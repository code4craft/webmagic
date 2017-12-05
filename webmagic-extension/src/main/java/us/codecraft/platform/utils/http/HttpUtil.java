package us.codecraft.platform.utils.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map.Entry;

/**
 * Title: HttpUtil.java<br>
 * Description: Http工具类<br>
 * Company: www.e7code.com<br>
 *
 * @author ssr
 * @author 2016年8月20日
 */
public class HttpUtil {

    public static HttpConnectionManager connManager = new HttpConnectionManager();
    // 读取超时 30秒
    public static int SOCKET_TIMEOUT = 30000;
    // 连接超时 30秒
    public static int CONNECTION_TIMEOUT = 30000;

    /**
     * 发送get请求.
     *
     * @param url
     *            请求地址
     * @return 响应结果
     * @throws IOException
     */
    public static String get(String url) throws IOException {
        return get(url, new HttpData());
    }

    /**
     * 发送get请求.
     *
     * @param url
     *            请求地址
     * @param data
     *            请求数据
     * @return 响应结果
     * @throws IOException
     */
    public static String get(String url, HttpData data) throws IOException {
        CloseableHttpClient httpclient = connManager.getHttpClient();
        HttpGet httpGet = new HttpGet(url);
        // 设置请求数据信息
        setData(httpGet, data);
        // 设置请求配置信息
        httpGet.setConfig(requestConfig);
        // 返回响应结果
        return httpclient.execute(httpGet, responseHandler);
    }

    public static String get(String url, List<String> proxyList) throws IOException {
        CloseableHttpClient httpclient = connManager.getHttpClient();
        HttpGet httpGet = new HttpGet(url);
        // 设置请求数据信息
        setData(httpGet, proxyList);
        // 设置请求配置信息
        httpGet.setConfig(requestConfig);
        httpGet.setConfig(requestConfig);
        // 返回响应结果
        return httpclient.execute(httpGet, responseHandler);
    }

    /***
     * 设置请求头信息
     *
     * @param request
     *            请求对象
     * @param data
     *            请求头参数
     * @throws UnsupportedEncodingException
     */
    private static void setData(HttpRequestBase request, HttpData data) throws UnsupportedEncodingException {
        /* -----------设置请求头信息------------- */
        if (data.getHeaders() != null) {
            for (Entry<String, String> entry : data.getHeaders().entrySet()) {
                request.addHeader(entry.getKey(), entry.getValue());
            }
        }

        /* -----------设置get请求参数------------- */
        if (request instanceof HttpGet) {
            setHttpGetParams((HttpGet) request, data);
            return;
        }
    }

    private static void setData(HttpRequestBase request, List<String> data) throws UnsupportedEncodingException {

        /* -----------设置get请求参数------------- */
        if (request instanceof HttpGet) {
            setHttpGetParams((HttpGet) request, data);
            return;
        }
    }

    /***
     * 设置get类型参数
     *
     * @param request
     *            请求对象
     * @param data
     *            请求数据
     * @throws UnsupportedEncodingException
     */
    private static void setHttpGetParams(HttpGet request, HttpData data) throws UnsupportedEncodingException {
        if (data.getParams() == null) {
            return;
        }
        StringBuilder url = new StringBuilder(request.getURI().toString());
        // 如果url以"/"结尾，则移除末尾的"/"
        if (url.lastIndexOf("/") == url.length() - 1) {
            url.delete (url.length () - 1, url.length ());
        }
        // 如果url中无"?"，则在末尾添加"?"
        if (url.indexOf("?") == -1) {
            url.append ("?");
        }

        for (Entry<String, String> entry : data.getParams().entrySet()) {
            // url最后一位不是"?",则在末尾添加"&"参数连接符
            if (url.lastIndexOf("?") != url.length() - 1) {
                url.append ("&");
            }
            url.append(entry.getKey()).append("=");
            url.append(URLEncoder.encode(entry.getValue(), data.getCharset()));
        }

        request.setURI(URI.create(url.toString()));
    }

    private static void setHttpGetParams(HttpGet request, List<String> data) throws UnsupportedEncodingException {
        if (data == null) {
            return;
        }
        StringBuilder url = new StringBuilder(request.getURI().toString());
        // 如果url以"/"结尾，则移除末尾的"/"
        if (url.lastIndexOf("/") == url.length() - 1) {
            url.delete (url.length () - 1, url.length ());
        }
        // 如果url中无"?"，则在末尾添加"?"
        if (url.indexOf("?") == -1) {
            url.append ("?");
        }
        for (String s : data) {
            // url最后一位不是"?",则在末尾添加"&"参数连接符
            if (url.lastIndexOf("?") != url.length() - 1) {
                url.append ("&");
            }

            url.append(URLEncoder.encode("ip_ports[]", "utf-8")).append("=");
            url.append(URLEncoder.encode(s, "utf-8"));
        }

        request.setURI(URI.create(url.toString()));
    }

    /***
     * 请求配置对象
     */
    private static RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(SOCKET_TIMEOUT) // 读取超时时间
            .setConnectTimeout(CONNECTION_TIMEOUT) // 连接超时时间
            .setCookieSpec(CookieSpecs.DEFAULT).build();

    /***
     * 响应处理器
     */
    private static ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
        @Override
        public String handleResponse(final HttpResponse response) throws IOException {
            HttpEntity entity = response.getEntity();
            return entity != null ? EntityUtils.toString(entity) : null;
        }
    };

    /***
     * 测试方法
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String url = "http://restapi.amap.com/v3/geocode/geo";
        String key = "9b03168b7de2bf593b8d4cbbe02611c8";
        HttpData paramDatas = new HttpData();
        paramDatas.addParam("key", key);
        paramDatas.addParam("address", "浙新小区");
        paramDatas.addParam("city", "浙江");
        System.out.println(HttpUtil.get(url, paramDatas));
    }

}
