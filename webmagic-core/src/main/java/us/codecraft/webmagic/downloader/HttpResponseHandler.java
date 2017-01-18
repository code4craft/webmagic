package us.codecraft.webmagic.downloader;

import org.apache.http.HttpResponse;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;

import java.io.IOException;

/**
 * Http client response handler
 * Created by zhoubing on 2016/12/20.
 */
public interface HttpResponseHandler {
    /**
     * Handle http client response.
     * @param request
     * @param charset
     * @param httpResponse
     * @param task
     * @return
     * @throws IOException
     */
    public Page handleResponse(Request request, String charset, HttpResponse httpResponse, Task task) throws IOException;

    /**
     * Close http client response
     * @param httpResponse
     */
    public void close(Request request, String charset, HttpResponse httpResponse, Task task) throws IOException;
}
