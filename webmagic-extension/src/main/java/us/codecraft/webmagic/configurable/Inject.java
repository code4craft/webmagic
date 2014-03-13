package us.codecraft.webmagic.configurable;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @author yihua.huang@dianping.com
 */
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Inject {

    String value() default "";
}
