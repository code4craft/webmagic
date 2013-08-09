package us.codecraft.webmagic.model.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 定义类或者字段的抽取规则(从url中抽取，只支持正则表达式)。<br>
 * @author code4crafter@gmail.com <br>
 * Date: 13-8-1 <br>
 * Time: 下午8:40 <br>
 */
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ExtractByUrl{

    /**
     * 抽取规则，支持正则表达式
     *
     * @return 抽取规则
     */
    String value() default "";

    /**
     * 是否是不能为空的关键字段，若notNull为true，则对应字段抽取不到时，丢弃整个类，默认为false
     *
     * @return 是否是不能为空的关键字段
     */
    boolean notNull() default false;

    /**
     * 是否抽取多个结果<br>
     * 用于字段时，需要List<String>来盛放结果<br>
     * 用于类时，表示单页抽取多个对象<br>
     *
     * @return 是否抽取多个结果
     */
    boolean multi() default false;

}
