package us.codecraft.webmagic.model.formatter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author code4crafter@gmail.com
 * @since 0.3.2
 */
public class ObjectFormatters {

    private static Map<Class, Class<? extends ObjectFormatter>> formatterMap = new ConcurrentHashMap<Class, Class<? extends ObjectFormatter>>();

    static {
        for (Class<? extends ObjectFormatter> basicTypeFormatter : BasicTypeFormatter.basicTypeFormatters) {
            put(basicTypeFormatter);
        }
        put(DateFormatter.class);
    }

    public static void put(Class<? extends ObjectFormatter> objectFormatter) {
        try {
            formatterMap.put(objectFormatter.newInstance().clazz(), objectFormatter);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static Class<? extends ObjectFormatter> get(Class<?> clazz){
        return formatterMap.get(clazz);
    }
}
