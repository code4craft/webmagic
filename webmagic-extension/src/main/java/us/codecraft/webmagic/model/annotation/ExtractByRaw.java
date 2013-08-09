package us.codecraft.webmagic.model.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 对于在Class级别就使用过ExtractBy的类，在字段中想抽取全部内容可使用此方法。<br>
 *
 * @author code4crafter@gmail.com <br>
 * @date: 13-8-1 <br>
 * Time: 下午8:40 <br>
 */
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
public @interface ExtractByRaw {

    /**
     * 抽取规则
     *
     * @return 抽取规则
     */
    String value();

    public enum Type {XPath, Regex, Css}

    /**
     * 抽取规则类型，支持XPath、Css selector、正则表达式，默认是XPath
     *
     * @return 抽取规则类型
     */
    Type type() default Type.XPath;

    /**
     * 是否是不能为空的关键字段，若notNull为true，则对应字段抽取不到时，丢弃整个类，默认为false
     *
     * @return 是否是不能为空的关键字段
     */
    boolean notNull() default false;

    /**
     * 是否抽取多个结果<br>
     * 需要List<String>来盛放结果<br>
     *
     * @return 是否抽取多个结果
     */
    boolean multi() default false;

}
