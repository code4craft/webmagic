package us.codecraft.webmagic.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author code4crafter@gmail.com
 *         Date: 16/12/18
 *         Time: 上午10:16
 */
public class WMCollections {

    public static <T> Set<T> newHashSet(T... t){
        Set<T> set = new HashSet<T>(t.length);
        for (T t1 : t) {
            set.add(t1);
        }
        return set;
    }

    public static <T> List<T> newArrayList(T... t){
        List<T> set = new ArrayList<T>(t.length);
        for (T t1 : t) {
            set.add(t1);
        }
        return set;
    }
}
