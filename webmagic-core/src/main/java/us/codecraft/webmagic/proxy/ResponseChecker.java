package us.codecraft.webmagic.proxy;

import org.apache.http.HttpResponse;

/**
 * @author code4crafter@gmail.com
 *         Date: 17/3/20
 *         Time: 下午10:52
 */
public interface ResponseChecker {

    boolean isBanned(HttpResponse httpResponse);
}
