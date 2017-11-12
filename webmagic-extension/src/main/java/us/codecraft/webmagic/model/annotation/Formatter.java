package us.codecraft.webmagic.model.annotation;

import us.codecraft.webmagic.model.formatter.ObjectFormatter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Define how the result string is convert to an object for field.
 *
 * @author code4crafter@gmail.com <br>
 * @since 0.3.2
 */
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Formatter {

    Class<ObjectFormatter> DEFAULT_FORMATTER = ObjectFormatter.class;

    /**
     * Set formatter params.
     *
     * @return formatter params
     */
    String[] value() default "";

    /**
     * Specific the class of field of class of elements in collection for field. <br>
     * It is not necessary to be set because we can detect the class by class of field,
     * unless you use a collection as a field. <br>
     *
     * @return the class of field
     */
    Class subClazz() default Void.class;

    /**
     * If there are more than one formatter for a class, just specify the implement.
     * @return implement
     */
    Class<? extends ObjectFormatter> formatter() default ObjectFormatter.class;

}
