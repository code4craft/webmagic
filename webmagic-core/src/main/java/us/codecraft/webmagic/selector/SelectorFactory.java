package us.codecraft.webmagic.selector;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author code4crafter@gmail.com <br>
 * Date: 13-4-21
 * Time: 上午7:56
 */
public class SelectorFactory {

    private Map<String, Selector> innerCache = new ConcurrentHashMap<String, Selector>();

    private static final SelectorFactory INSTATNCE = new SelectorFactory();

    public static SelectorFactory getInstatnce() {
        return INSTATNCE;
    }

    public RegexSelector newRegexSelector(String regex) {
        return newSelector(RegexSelector.class, regex);
    }

    public ReplaceSelector newReplaceSelector(String regex, String replacement) {
        return newSelector(ReplaceSelector.class, regex, replacement);
    }

    public XpathSelector newXpathSelector(String xpath) {
        return newSelector(XpathSelector.class, xpath);
    }

    public SmartContentSelector newSmartContentSelector(){
        return newSelector(SmartContentSelector.class);
    }

    public <T extends Selector> T newAndCacheSelector(Class<T> clazz, String... param) {
        String cacheKey = getCacheKey(RegexSelector.class, param);
        if (innerCache.get(cacheKey) != null) {
            return (T) innerCache.get(cacheKey);
        }
        T selector = newSelector(clazz, param);
        if (selector != null) {
            innerCache.put(cacheKey, selector);
        }
        return selector;

    }

    public <T extends Selector> T newSelector(Class<T> clazz, String... param) {
        try {
            if (param.length == 0) {
                Constructor<T> constructor
                        = clazz.getConstructor();
                T selector = constructor.newInstance();
                return selector;
            } else if (param.length == 1) {
                Constructor<T> constructor
                        = clazz.getConstructor(String.class);
                T selector = constructor.newInstance(param[0]);
                return selector;
            } else if (param.length == 2) {
                Constructor<T> constructor
                        = clazz.getConstructor(String.class, String.class);
                T selector = constructor.newInstance(param[0], param[1]);
                return selector;
            } else {
                throw new UnsupportedOperationException();
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("init object error", e);
        }
    }

    private String getCacheKey(Class<?> clazz, String... param) {
        return clazz.toString() + "_" + StringUtils.join(param, "_");
    }

}
