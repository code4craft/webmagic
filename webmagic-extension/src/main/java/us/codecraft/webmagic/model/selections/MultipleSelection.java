package us.codecraft.webmagic.model.selections;

import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.model.FieldExtractor;
import us.codecraft.webmagic.model.fields.MultipleField;

public class MultipleSelection implements Selection {
   public MultipleField extractField(Page page, String html, boolean isRaw, FieldExtractor fieldExtractor) {
      List<String> fieldsName;
      switch (fieldExtractor.getSource()) {
         case RawHtml:
            fieldsName = page.getHtml().selectDocumentForList(fieldExtractor.getSelector());
            break;
         case Html:
            if (isRaw)
               fieldsName = page.getHtml().selectDocumentForList(fieldExtractor.getSelector());
            else
               fieldsName = fieldExtractor.getSelector().selectList(html);
            break;
         case Url:
            fieldsName = fieldExtractor.getSelector().selectList(page.getUrl().toString());
            break;
         case RawText:
            fieldsName = fieldExtractor.getSelector().selectList(page.getRawText());
            break;
         default:
            fieldsName = fieldExtractor.getSelector().selectList(html);
      }
      if ((fieldsName == null || fieldsName.size() == 0) && fieldExtractor.isNotNull()) {
         return null;
      }
      return new MultipleField(fieldsName);
   }
}