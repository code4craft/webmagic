package us.codecraft.platform.utils;

import us.codecraft.webmagic.Request;

/**
 * @Author :王龙
 * @Description
 * @Data 2017/11/23 11:08
 * @Modified By：
 */
public class RequestUtils {
    /**
     * 获取当前Request的循环次数
     *
     * @param request
     * @return
     */
    public static int getCycleRetryTimes(Request request) {
        int cycleTriedTimes;
        Object cycleTriedTimesObject = request.getExtra(Request.CYCLE_TRIED_TIMES);
        if (cycleTriedTimesObject == null) {
            cycleTriedTimes = 0;
        } else {
            cycleTriedTimes = (Integer) cycleTriedTimesObject;
        }
        return cycleTriedTimes;
    }

    /**
     * 重置request的循环次数
     * 参照Spider类 137行
     *
     * @param request
     */
    public static  void setCycleRetryTimes(Request request) {
        request.setPriority(0).putExtra(Request.CYCLE_TRIED_TIMES, 3);
    }
}
