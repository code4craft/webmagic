package us.codecraft.webmagic.configurable;

import us.codecraft.webmagic.processor.PageProcessor;

import java.util.Map;

/**
 * Inject property to object by {@link Inject} annotation.
 *
 * @author yihua.huang@dianping.com
 */
public class PropertyLoader<T> {

    public T load(T object, Map<String, String> properties) {
        return object;
    }

}
