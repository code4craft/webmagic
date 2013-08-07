package us.codecraft.webmagic.model.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 定义类或者字段的抽取规则(从url中抽取，只支持正则表达式)。<br>
 * @author code4crafter@gmail.com <br>
 * @date: 13-8-1 <br>
 * Time: 下午8:40 <br>
 */
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ExtractByUrl{

    String value() default "";

    boolean notNull() default true;

    boolean multi() default false;

}
