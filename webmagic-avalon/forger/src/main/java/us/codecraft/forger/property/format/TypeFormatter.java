package us.codecraft.forger.property.format;

/**
 * @author code4crafter@gmail.com
 */
public interface TypeFormatter<T> extends ObjectFormatter<T> {

    T format(String text, String[] params);

    Class<T> clazz();

}
