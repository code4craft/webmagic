package us.codecraft.webmagic.model.sources;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.model.FieldExtractor;
import us.codecraft.webmagic.model.fields.MultipleField;
import us.codecraft.webmagic.model.fields.PageField;
import us.codecraft.webmagic.model.fields.SingleField;

public class SourceTextExtractor {
   public static PageField getText(Page page, String html, boolean isRaw, FieldExtractor fieldExtractor) {
      Source source = fieldExtractor.getSource();
      if (fieldExtractor.isMulti())
         return new MultipleField(source.getTextList(page, html, isRaw, fieldExtractor));
      else
         return new SingleField(source.getText(page, html, isRaw, fieldExtractor));
   }
}