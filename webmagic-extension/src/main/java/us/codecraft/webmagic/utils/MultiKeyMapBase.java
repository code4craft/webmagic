package us.codecraft.webmagic.utils;

/**
 * @author code4crafter@gmail.com
 */

import java.util.HashMap;
import java.util.Map;

/**
 * multi-key map, some basic objects *
 *
 * @author yihua.huang
 */
public abstract class MultiKeyMapBase {

    protected static final Class<? extends Map> DEFAULT_CLAZZ = HashMap.class;
    @SuppressWarnings("rawtypes")
    private Class<? extends Map> protoMapClass = DEFAULT_CLAZZ;

    public MultiKeyMapBase() {
    }

    @SuppressWarnings("rawtypes")
    public MultiKeyMapBase(Class<? extends Map> protoMapClass) {
        this.protoMapClass = protoMapClass;
    }

    @SuppressWarnings("unchecked")
    protected <K, V2> Map<K, V2> newMap() {
        try {
            return (Map<K, V2>) protoMapClass.newInstance();
        } catch (InstantiationException e) {
            throw new IllegalArgumentException("wrong proto type map "
                    + protoMapClass);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException("wrong proto type map "
                    + protoMapClass);
        }
    }
}