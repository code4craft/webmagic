package us.codecraft.webmagic.annotation;

import us.codecraft.webmagic.selector.Selector;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author yihua.huang@dianping.com <br>
 * @date: 13-8-1 <br>
 * Time: 下午9:48 <br>
 */
class FieldExtractor {

    private final Field field;

    private final Selector selector;

    private final Source source;

    private Method setterMethod;

    static enum Source {Html, Url}

    public FieldExtractor(Field field, Selector selector) {
        this.field = field;
        this.selector = selector;
        this.source = Source.Html;
    }

    public FieldExtractor(Field field, Selector selector, Source source) {
        this.field = field;
        this.selector = selector;
        this.source = source;
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
}
