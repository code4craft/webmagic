package us.codecraft.webmagic.configurable;

import us.codecraft.webmagic.processor.PageProcessor;

import java.util.Map;

/**
 * @author yihua.huang@dianping.com
 */
public interface PropertyLoader<T> {

    PropertyLoader<T> clazz(Class<?> clazz);

    T load(Map<String, String> properties);
}
