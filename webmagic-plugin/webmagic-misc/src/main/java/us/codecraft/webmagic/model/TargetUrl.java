package us.codecraft.webmagic.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 定义某个类抽取的范围和来源，sourceRegion可以用xpath语法限定抽取区域。<br>
 * @author code4crafter@gmail.com <br>
 * @date: 13-8-1 <br>
 * Time: 下午8:40 <br>
 */
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface TargetUrl {

    String[] value();

    String sourceRegion() default "";

}
