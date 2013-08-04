package us.codecraft.webmagic.utils;

import java.util.Map;

/**
 * @author yihua.huang@dianping.com
 * @date Dec 14, 2012
 */
public class DoubleKeyMap<K1, K2, V> extends MultiKeyMapBase {
    private Map<K1, Map<K2, V>> map;

    public DoubleKeyMap() {
        init();
    }

    public DoubleKeyMap(Map<K1, Map<K2, V>> map) {
        this(map,DEFAULT_CLAZZ);
    }

    public DoubleKeyMap(Class<? extends Map> protoMapClass) {
        super(protoMapClass);
        init();
    }

    private void init() {
        if (map == null) {
            map = this.<K1, Map<K2, V>>newMap();
        }
    }

    /**
     * init map with protoMapClass
     *
     * @param protoMapClass
     */
    @SuppressWarnings("rawtypes")
    public DoubleKeyMap(Map<K1, Map<K2, V>> map, Class<? extends Map> protoMapClass) {
        super(protoMapClass);
        this.map = map;
        init();
    }

    /**
     * @param key
     * @return
     */
    public Map<K2, V> get(K1 key) {
        return map.get(key);
    }

    /**
     * @param key1
     * @param key2
     * @return
     */
    public V get(K1 key1, K2 key2) {
        if (get(key1) == null) {
            return null;
        }
        return get(key1).get(key2);
    }


    /**
     * @param key1
     * @param submap
     * @return
     */
    public V put(K1 key1, Map<K2, V> submap) {
        return put(key1, submap);
    }

    /**
     * @param key1
     * @param key2
     * @param value
     * @return
     */
    public V put(K1 key1, K2 key2, V value) {
        if (map.get(key1) == null) {
            map.put(key1, this.<K2, V>newMap());
        }
        return get(key1).put(key2, value);
    }

    /**
     * @param key1
     * @param key2
     * @return
     */
    public V remove(K1 key1, K2 key2) {
        if (get(key1) == null) {
            return null;
        }
        V remove = get(key1).remove(key2);
        // 如果上一级map为空，把它也回收掉
        if (get(key1).size() == 0) {
            remove(key1);
        }
        return remove;
    }

    /**
     * @param key1
     * @return
     */
    public Map<K2, V> remove(K1 key1) {
        Map<K2, V> remove = map.remove(key1);
        return remove;
    }
}
