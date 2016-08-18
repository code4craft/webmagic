package us.codecraft.forger.property;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author code4crafter@gmail.com
 */
public class AnnotationPropertyLoader extends AbstractPropertyLoader {

    @Override
    public List<Property> getProperties(Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        List<Property> properties = new ArrayList<Property>(fields.length);
        for (Field field : fields) {
            Inject inject = field.getAnnotation(Inject.class);
            if (inject != null) {
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                Property property = Property.fromField(field);
                if (inject.value().length() > 0) {
                    property.setName(inject.value());
                }
                property.setObjectFormatter(getObjectFormatter(field));
                properties.add(property);
            }
        }
        return properties;
    }
}
