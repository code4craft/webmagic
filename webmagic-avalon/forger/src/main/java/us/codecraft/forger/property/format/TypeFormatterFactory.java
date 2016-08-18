package us.codecraft.forger.property.format;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author code4crafter@gmail.com
 * @since 0.3.2
 */
public class TypeFormatterFactory {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private Map<Class, TypeFormatter> objectFormatterMapWithPropertyAsKey = new ConcurrentHashMap<Class, TypeFormatter>();

    private Map<Class, TypeFormatter> objectFormatterMapWithClassAsKey = new ConcurrentHashMap<Class, TypeFormatter>();

    public TypeFormatterFactory() {
        initFormatterMap();
    }

    private void initFormatterMap() {
        for (Class<? extends TypeFormatter> basicTypeFormatter : BasicTypeFormatter.basicTypeFormatters) {
            put(basicTypeFormatter);
        }
        put(DateFormatter.class);
    }

    public synchronized void put(Class<? extends TypeFormatter> objectFormatterClazz) {
        try {
            TypeFormatter typeFormatter = objectFormatterClazz.newInstance();
            if (typeFormatter.clazz() != null) {
                objectFormatterMapWithPropertyAsKey.put(typeFormatter.clazz(), typeFormatter);
            }
            objectFormatterMapWithClassAsKey.put(objectFormatterClazz, typeFormatter);
        } catch (InstantiationException e) {
            logger.error("Init objectFormatter error", e);
        } catch (IllegalAccessException e) {
            logger.error("Init objectFormatter error", e);
        }
    }

    public TypeFormatter get(Class<?> clazz) {
        return objectFormatterMapWithPropertyAsKey.get(clazz);
    }

    public TypeFormatter getByFormatterClass(Class<?> clazz) {
        return objectFormatterMapWithClassAsKey.get(clazz);
    }
}
