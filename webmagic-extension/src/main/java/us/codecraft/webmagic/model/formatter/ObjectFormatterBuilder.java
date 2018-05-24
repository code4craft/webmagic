package us.codecraft.webmagic.model.formatter;

import us.codecraft.webmagic.model.annotation.Formatter;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author code4crafter@gmail.com
 * @since 0.7.0
 *         Date: 2017/6/3
 */
public class ObjectFormatterBuilder {

    private Field field;

    public ObjectFormatterBuilder setField(Field field) {
        this.field = field;
        return this;
    }

    private ObjectFormatter initFormatterForType(Class<?> fieldClazz, String[] params) {
        if (fieldClazz.equals(String.class) || List.class.isAssignableFrom(fieldClazz)){
            return null;
        }
        Class<? extends ObjectFormatter> formatterClass = ObjectFormatters.get(BasicTypeFormatter.detectBasicClass(fieldClazz));
        if (formatterClass == null) {
            throw new IllegalStateException("Can't find formatter for field " + field.getName() + " of type " + fieldClazz);
        }
        return initFormatter(formatterClass, params);
    }

    private ObjectFormatter initFormatter(Class<? extends ObjectFormatter> formatterClazz, String[] params) {
        try {
            ObjectFormatter objectFormatter = formatterClazz.newInstance();
            objectFormatter.initParam(params);
            return objectFormatter;
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public ObjectFormatter build() {
        Formatter formatter = field.getAnnotation(Formatter.class);
        if (formatter != null && !formatter.formatter().equals(Formatter.DEFAULT_FORMATTER)) {
            return initFormatter(formatter.formatter(), formatter.value());
        }
        if (formatter == null || formatter.subClazz().equals(Void.class)) {
            return initFormatterForType(field.getType(), formatter != null ? formatter.value() : null);
        } else {
            return initFormatterForType(formatter.subClazz(), formatter.value());
        }
    }
}
