package us.codecraft.webmagic.model.formatter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author code4crafter@gmail.com
 * @since 0.3.2
 */
public class ObjectFormatters {

    private static Map<Class, ObjectFormatter> formatterMap = new ConcurrentHashMap<Class, ObjectFormatter>();

    static {
        for (ObjectFormatter basicTypeFormatter : BasicTypeFormatter.basicTypeFormatters) {
            put(basicTypeFormatter);
        }
    }

    public static void put(ObjectFormatter objectFormatter) {
        formatterMap.put(objectFormatter.clazz(), objectFormatter);
    }

    public static <T> ObjectFormatter<T> get(Class<T> clazz){
        return formatterMap.get(clazz);
    }
}
