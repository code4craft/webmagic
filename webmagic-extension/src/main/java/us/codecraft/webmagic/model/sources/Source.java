package us.codecraft.webmagic.model.sources;

import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.model.FieldExtractor;

public interface Source {
   public String getText(Page page, String html, boolean isRaw, FieldExtractor fieldExtractor);
   public List<String> getTextList(Page page, String html, boolean isRaw, FieldExtractor fieldExtractor);

   public class RawHtml implements Source {
      public String getText(Page page, String html, boolean isRaw, FieldExtractor fieldExtractor) {
         return page.getHtml().selectDocument(fieldExtractor.getSelector());
      }
   
      public List<String> getTextList(Page page, String html, boolean isRaw, FieldExtractor fieldExtractor) {
         return page.getHtml().selectDocumentForList(fieldExtractor.getSelector());
      }
   }
   
   public class SelectedHtml implements Source {
      public String getText(Page page, String html, boolean isRaw, FieldExtractor fieldExtractor) {
         if (isRaw)
            return page.getHtml().selectDocument(fieldExtractor.getSelector());
         else
            return fieldExtractor.getSelector().select(html);
      }
   
      public List<String> getTextList(Page page, String html, boolean isRaw, FieldExtractor fieldExtractor) {
         if (isRaw)
            return page.getHtml().selectDocumentForList(fieldExtractor.getSelector());
         else
            return fieldExtractor.getSelector().selectList(html);
      }
   }
   
   public class Url implements Source {
      public String getText(Page page, String html, boolean isRaw, FieldExtractor fieldExtractor) {
         return fieldExtractor.getSelector().select(page.getUrl().toString());
      }
   
      public List<String> getTextList(Page page, String html, boolean isRaw, FieldExtractor fieldExtractor) {
         return fieldExtractor.getSelector().selectList(page.getUrl().toString());
      }
   }
   
   public class RawText implements Source {
      public String getText(Page page, String html, boolean isRaw, FieldExtractor fieldExtractor) {
         return fieldExtractor.getSelector().select(page.getRawText());
      }
   
      public List<String> getTextList(Page page, String html, boolean isRaw, FieldExtractor fieldExtractor) {
         return fieldExtractor.getSelector().selectList(page.getRawText());
      }
   }
   
   public class DefaultSource implements Source {
      public String getText(Page page, String html, boolean isRaw, FieldExtractor fieldExtractor) {
         return fieldExtractor.getSelector().select(html);
      }
   
      public List<String> getTextList(Page page, String html, boolean isRaw, FieldExtractor fieldExtractor) {
         return fieldExtractor.getSelector().selectList(html);
      }
   }
}

