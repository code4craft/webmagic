package us.codecraft.webmagic.monitor;

import us.codecraft.webmagic.Request;

/**
 * @author code4crafer@gmail.com
 * @since 0.5.0
 */
public interface SpiderListener {

    public void onSuccess(Request request);

    public void onError(Request request);
}
