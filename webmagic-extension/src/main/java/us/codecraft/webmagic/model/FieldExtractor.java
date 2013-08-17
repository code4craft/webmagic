package us.codecraft.webmagic.model;

import us.codecraft.webmagic.selector.Selector;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Wrapper of field and extractor.
 * @author code4crafter@gmail.com <br>
 * @since 0.2.0
 */
class FieldExtractor extends Extractor {

    private final Field field;

    private Method setterMethod;

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

    void setSetterMethod(Method setterMethod) {
        this.setterMethod = setterMethod;
    }

    Method getSetterMethod() {
        return setterMethod;
    }

    boolean isNotNull() {
        return notNull;
    }
}
