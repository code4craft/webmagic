package us.codecraft.webmagic.model.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Define the url patterns for class. <br>
 * All urls matching the pattern will be crawled and extracted for new objects. <br>
 *
 * @author code4crafter@gmail.com <br>
 * @since 0.2.0
 */
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface TargetUrl {

    /**
     * The url patterns for class.<br>
     * Use regex expression with some changes: <br>
     *      "." stand for literal character "." instead of "any character". <br>
     *      "*" stand for any legal character for url in 0-n length ([^"'#]*) instead of "any length". <br>
     *
     * @return the url patterns for class
     */
    String[] value();

    /**
     * Define the region for url extracting. <br>
     * Only support XPath.<br>
     * When sourceRegion is set, the urls will be extracted only from the region instead of entire content. <br>
     *
     * @return the region for url extracting
     */
    String sourceRegion() default "";

}
