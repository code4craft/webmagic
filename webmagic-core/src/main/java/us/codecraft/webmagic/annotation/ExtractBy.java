package us.codecraft.webmagic.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @author yihua.huang@dianping.com <br>
 * @date: 13-8-1 <br>
 * Time: 下午8:40 <br>
 */
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ExtractBy {

    String value();

    public enum Type {XPath, Regex, Css};

    Type type() default Type.XPath;
}
