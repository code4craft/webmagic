package us.codecraft.webmagic.utils;

/**
 * Numbers comparison utilility for schedule priority
 * 
 * @author yihua.huang@dianping.com
 */
public abstract class NumberUtils {

    public static int compareLong(long o1, long o2) {
        if (o1 < o2) {
            return -1;
        } else if (o1 == o2) {
            return 0;
        } else {
            return 1;
        }
    }
}
