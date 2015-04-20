package us.codecraft.webmagic.model.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;


/**
 * Make model annotation configurable.
 * Most of time the change parts come from html page. So the xpath in annotation part should be change
 * when the page is changed. Without compile source code, we can make the annotation value configurable.
 * @author dengxiaotao@gmail.com
 *
 */
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Configurable {
    /**
     * Anontation config path. <br>
     *
     * @return the path of config file.
     */
    String value() default "annotation.properties";
}
