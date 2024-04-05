package us.codecraft.webmagic.model.selections;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.model.FieldExtractor;
import us.codecraft.webmagic.model.fields.SingleField;

public class SingleSelection implements Selection {
    public SingleField extractField(Page page, String html, boolean isRaw, FieldExtractor fieldExtractor) {
        String field;
        switch (fieldExtractor.getSource()) {
            case RawHtml:
                field = page.getHtml().selectDocument(fieldExtractor.getSelector());
                break;
            case Html:
                if (isRaw)
                    field = page.getHtml().selectDocument(fieldExtractor.getSelector());
                else
                    field = fieldExtractor.getSelector().select(html);
                break;
            case Url:
                field = fieldExtractor.getSelector().select(page.getUrl().toString());
                break;
            case RawText:
                field = fieldExtractor.getSelector().select(page.getRawText());
                break;
            default:
                field = fieldExtractor.getSelector().select(html);
        }
        if (field == null && fieldExtractor.isNotNull())
            return null;
        return new SingleField(field);
    }
}
