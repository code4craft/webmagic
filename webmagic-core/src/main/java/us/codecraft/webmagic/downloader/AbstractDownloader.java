package us.codecraft.webmagic.downloader;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.selector.Html;

/**
 * Base class of downloader with some common methods.
 *
 * @author code4crafter@gmail.com
 * @since 0.5.0
 */
public abstract class AbstractDownloader implements Downloader {

    /**
     * A simple method to download a url.
     *
     * @param url url
     * @return html
     */
    public Html download(String url) {
        return download(url, null);
    }

    /**
     * A simple method to download a url.
     *
     * @param url     url
     * @param charset charset
     * @return html
     */
    public Html download(String url, String charset) {
        Page page = download(new Request(url), Site.me().setCharset(charset).toTask());
        return page.getHtml();
    }

    @Deprecated
    protected void onSuccess(Request request) {
    }

    /**
     * @since 0.7.6
     */
    protected void onSuccess(Request request, Task task) {
        this.onSuccess(request);
    }

    @Deprecated
    protected void onError(Request request) {
    }

    /**
     * @since 0.7.6
     */
    protected void onError(Request request, Task task, Throwable e) {
        this.onError(request);
    }

}
