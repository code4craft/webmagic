package us.codecraft.webmagic.model.selections;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.model.FieldExtractor;
import us.codecraft.webmagic.model.fields.PageField;

public interface Selection {
   public PageField extractField(Page page, String html, boolean isRaw, FieldExtractor fieldExtractor);
}
