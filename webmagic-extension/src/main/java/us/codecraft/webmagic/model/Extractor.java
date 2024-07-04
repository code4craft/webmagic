package us.codecraft.webmagic.model;

import lombok.Getter;
import lombok.Setter;

import us.codecraft.webmagic.model.sources.Source;
import us.codecraft.webmagic.selector.Selector;

/**
 * The object contains 'ExtractBy' information.
 * @author code4crafter@gmail.com <br>
 * @since 0.2.0
 */
public class Extractor {

    @Getter @Setter
    protected Selector selector;

    @Getter
    protected final Source source;

    protected final boolean notNull;

    protected final boolean multi;
  
    public Extractor(Selector selector, Source source, boolean notNull, boolean multi) {
        this.selector = selector;
        this.source = source;
        this.notNull = notNull;
        this.multi = multi;
    }

    public boolean isNotNull() {
        return notNull;
    }

    public boolean isMulti() {
        return multi;
    }
}
