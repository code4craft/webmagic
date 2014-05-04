package us.codecraft.webmagic.model;

import us.codecraft.webmagic.Page;

/**
 * @author code4crafer@gmail.com
 * @since 0.5.2
 */
public class PageMapper<T> {

    private Class<T> clazz;

    public PageMapper(Class<T> clazz) {
        this.clazz = clazz;
    }

    public T get(Page page){
        return null;
    }
}
