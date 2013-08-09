package us.codecraft.webmagic.model.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 定义辅助爬取的url。<br>
 * @author code4crafter@gmail.com <br>
 * @date: 13-8-1 <br>
 * Time: 下午8:40 <br>
 */
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface HelpUrl {

    /**
     * 某个类对应的URL规则列表<br>
     * webmagic对正则表达式进行了修改，"."仅表示字符"."而不代表任意字符，而"\*"则代表了".\*"，例如"http://\*.oschina.net/\*"代表了oschina所有的二级域名下的URL。<br>
     *
     * @return 抽取规则
     */
    String[] value();

    /**
     * 指定提取URL的区域(仅支持XPath)
     * @return 指定提取URL的区域
     */
    String sourceRegion() default "";
}
