package us.codecraft.webmagic.annotation;

import us.codecraft.webmagic.selector.Selector;

import java.lang.reflect.Field;

/**
 * @author yihua.huang@dianping.com <br>
 * @date: 13-8-1 <br>
 * Time: 下午9:48 <br>
 */
class FieldFetcher {

    private final Field field;

    private final Selector selector;

    FieldFetcher(Field field, Selector selector) {
        this.field = field;
        this.selector = selector;
    }

    Field getField() {
        return field;
    }

    Selector getSelector() {
        return selector;
    }
}
