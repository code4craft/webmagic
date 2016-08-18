package us.codecraft.forger.property;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.forger.property.format.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author code4crafter@gmail.com
 */
public abstract class AbstractPropertyLoader implements PropertyLoader {

    private TypeFormatterFactory typeFormatterFactory = new TypeFormatterFactory();

    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected TypeFormatterFactory getTypeFormatterFactory() {
        return typeFormatterFactory;
    }

    @Override
    public <T> T load(T object, Map<String, Object> propertyConfigs) {
        List<Property> properties = getProperties(object.getClass());
        for (Property property : properties) {
            Object value = propertyConfigs.get(property.getName());
            if (value == null) {
                throw new IllegalArgumentException("Config for property " + property.getName() + " is missing!");
            }
            ObjectFormatter objectFormatter = property.getObjectFormatter();
            switch (property.getType()) {
                case PropertyString:
                    Object fieldValue = objectFormatter.format(String.valueOf(value));
                    try {
                        property.getField().set(object, fieldValue);
                    } catch (IllegalAccessException e) {
                        logger.warn("Set field " + property.getField() + " error!", e);
                    }
                    break;
                case PropertyList:
                    if (!List.class.isAssignableFrom(value.getClass())) {
                        throw new IllegalArgumentException("Config for property " + property.getName() + " should be subclass of List!");
                    }
                    List listField = new ArrayList();
                    List<String> listConfigs = (List) value;
                    for (String listConfig : listConfigs) {
                        listField.add(objectFormatter.format(listConfig));
                    }
                    try {
                        property.getField().set(object, listField);
                    } catch (IllegalAccessException e) {
                        logger.warn("Set field " + property.getField() + " error!", e);
                    }
                    break;
                case PropertyMap:
                    if (!Map.class.isAssignableFrom(value.getClass())) {
                        throw new IllegalArgumentException("Config for property " + property.getName() + " should be subclass of List!");
                    }
                    Map mapField = new HashMap();
                    Map<String, String> mapConfigs = (Map<String, String>) value;
                    for (Map.Entry<String, String> entry : mapConfigs.entrySet()) {
                        mapField.put(entry.getKey(), objectFormatter.format(entry.getValue()));
                    }
                    try {
                        property.getField().set(object, mapField);
                    } catch (IllegalAccessException e) {
                        logger.warn("Set field " + property.getField() + " error!", e);
                    }
                    break;
            }
        }
        return object;
    }

    protected ObjectFormatter prepareTypeFormatterParam(TypeFormatter objectFormatter, String[] params) {
        if (params == null) {
            return objectFormatter;
        }
        return new ObjectFormatterWithParams().setTypeFormatter(objectFormatter).setParams(params);
    }

    protected ObjectFormatter getObjectFormatter(Field field) {
        Class type = field.getType();
        if (List.class.isAssignableFrom(type) || Map.class.isAssignableFrom(type)) {
            type = String.class;
        }
        if (field.isAnnotationPresent(Formatter.class)) {
            Formatter formatter = field.getAnnotation(Formatter.class);
            if (!formatter.formatter().equals(TypeFormatter.class)) {
                TypeFormatter typeFormatter = typeFormatterFactory.getByFormatterClass(formatter.formatter());
                if (typeFormatter != null) {
                    return prepareTypeFormatterParam(typeFormatter,formatter.value());
                }
                typeFormatterFactory.put(formatter.formatter());
                return prepareTypeFormatterParam(typeFormatterFactory.getByFormatterClass(formatter.formatter()), formatter.value());
            } else if (!formatter.subClazz().equals(String.class)) {
                type = formatter.subClazz();
                TypeFormatter typeFormatter = typeFormatterFactory.get(type);
                if (typeFormatter == null) {
                    throw new IllegalArgumentException("No typeFormatter for class " + type);
                }
                return prepareTypeFormatterParam(typeFormatter, formatter.value());
            }
        }
        return getTypeFormatterFactory().get(BasicTypeFormatter.detectBasicClass(type));
    }

}
