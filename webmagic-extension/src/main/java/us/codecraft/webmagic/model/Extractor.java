package us.codecraft.webmagic.model;

import us.codecraft.webmagic.selector.Selector;

/**
 * The object contains 'ExtractBy' information.
 *
 * @author code4crafter@gmail.com <br>
 * @since 0.2.0
 */
class Extractor {

    protected final Source source;
    protected final boolean notNull;
    protected final boolean multi;
    protected Selector selector;

    public Extractor(Selector selector, Source source, boolean notNull, boolean multi) {
        this.selector = selector;
        this.source = source;
        this.notNull = notNull;
        this.multi = multi;
    }

    Selector getSelector() {
        return selector;
    }

    void setSelector(Selector selector) {
        this.selector = selector;
    }

    Source getSource() {
        return source;
    }

    boolean isNotNull() {
        return notNull;
    }

    boolean isMulti() {
        return multi;
    }

    enum Source {
        Html,
        Url,
        RawHtml,
        RawText
    }
}
