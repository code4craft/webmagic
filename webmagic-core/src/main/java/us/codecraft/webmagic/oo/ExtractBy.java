package us.codecraft.webmagic.oo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @author code4crafter@gmail.com <br>
 * @date: 13-8-1 <br>
 * Time: 下午8:40 <br>
 */
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ExtractBy {


    //TODO: add list support
    String value();

    public enum Type {XPath, Regex, Css};

    Type type() default Type.XPath;

    boolean notNull() default true;
}
