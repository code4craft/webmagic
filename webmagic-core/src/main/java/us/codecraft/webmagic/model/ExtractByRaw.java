package us.codecraft.webmagic.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 对于在Class级别就使用过ExtractBy的类，在字段中想抽取全部内容可使用此方法。<br>
 * @author code4crafter@gmail.com <br>
 * @date: 13-8-1 <br>
 * Time: 下午8:40 <br>
 */
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.TYPE})
public @interface ExtractByRaw {

    String value();

    public enum Type {XPath2, XPath, Regex, Css}

    Type type() default Type.XPath2;

    boolean notNull() default true;

    boolean multi() default false;

}
