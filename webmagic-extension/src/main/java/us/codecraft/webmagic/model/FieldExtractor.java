package us.codecraft.webmagic.model;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import us.codecraft.webmagic.model.formatter.ObjectFormatter;
import us.codecraft.webmagic.selector.Selector;

/**
 * Wrapper of field and extractor.
 *
 * @author code4crafter@gmail.com <br>
 * @since 0.2.0
 */
class FieldExtractor extends Extractor {

    private final Field field;

    private Method setterMethod;

    private ObjectFormatter objectFormatter;

    public FieldExtractor(Field field, Selector selector, Source source, boolean notNull, boolean multi) {
        super(selector, source, notNull, multi);
        this.field = field;
    }

    Field getField() {
        return field;
    }

    Selector getSelector() {
        return selector;
    }

    Source getSource() {
        return source;
    }

    Method getSetterMethod() {
        return setterMethod;
    }

    void setSetterMethod(Method setterMethod) {
        this.setterMethod = setterMethod;
    }

    boolean isNotNull() {
        return notNull;
    }

    ObjectFormatter getObjectFormatter() {
        return objectFormatter;
    }

    void setObjectFormatter(ObjectFormatter objectFormatter) {
        this.objectFormatter = objectFormatter;
    }
}
