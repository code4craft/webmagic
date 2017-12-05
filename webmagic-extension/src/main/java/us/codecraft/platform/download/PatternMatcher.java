package us.codecraft.platform.download;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.handler.RequestMatcher;

import java.util.regex.Pattern;

/** 
 * 默认实现RequestMatcher方法
 * @author 王龙 
 * @date 2017年11月1日 上午11:12:22  
 */
public interface PatternMatcher extends RequestMatcher {
    /**
     * 获取正则表达式
     * 
     * @description
     * @author 王龙
     * @date 2017年10月31日 下午3:06:54
     * @return
     */
    Pattern getPattern();

    @Override
    default boolean match(Request page) {
        return getPattern().matcher(page.getUrl()).matches();
    }
}
