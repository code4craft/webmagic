package us.codecraft.webmagic.annotation;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.CssSelector;
import us.codecraft.webmagic.selector.RegexSelector;
import us.codecraft.webmagic.selector.Selector;
import us.codecraft.webmagic.selector.XpathSelector;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author yihua.huang@dianping.com <br>
 * @date: 13-8-1 <br>
 * Time: 下午9:33 <br>
 */
class PageModelExtractor {

    private List<Pattern> targetUrlPatterns;

    private Class clazz;

    private List<FieldExtractor> fieldExtractors;

    public static PageModelExtractor create(Class clazz) {
        PageModelExtractor pageModelExtractor = new PageModelExtractor();
        pageModelExtractor.init(clazz);
        return pageModelExtractor;
    }

    private void init(Class clazz) {
        this.clazz = clazz;
        initTargetUrlPatterns();
        fieldExtractors = new ArrayList<FieldExtractor>();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            ExtractBy extractBy = field.getAnnotation(ExtractBy.class);
            if (extractBy != null) {
                String value = extractBy.value();
                Selector selector;
                switch (extractBy.type()) {
                    case Css:
                        selector = new CssSelector(value);
                        break;
                    case Regex:
                        selector = new RegexSelector(value);
                        break;
                    case XPath:
                        selector = new XpathSelector(value);
                        break;
                    default:
                        selector = new XpathSelector(value);
                }
                fieldExtractors.add(new FieldExtractor(field, selector));
            }
            ExtractByUrl extractByUrl = field.getAnnotation(ExtractByUrl.class);
            if (extractByUrl != null) {
                String regexPattern = extractByUrl.value();
                if (regexPattern.trim().equals("")) {
                    regexPattern = ".*";
                }
                fieldExtractors.add(new FieldExtractor(field, new RegexSelector(regexPattern), FieldExtractor.Source.Url));
            }
        }
    }

    private void initTargetUrlPatterns() {
        targetUrlPatterns = new ArrayList<Pattern>();
        Annotation annotation = clazz.getAnnotation(TargetUrl.class);
        if (annotation == null) {
            targetUrlPatterns.add(Pattern.compile(".*"));
        } else {
            String[] value = ((TargetUrl) annotation).value();
            for (String s : value) {
                targetUrlPatterns.add(Pattern.compile(s.replace(".", "\\.").replace("*", "[^\"'#]*")));
            }
        }
    }

    public Object process(Page page) {
        boolean matched = false;
        for (Pattern targetPattern : targetUrlPatterns) {
            if (targetPattern.matcher(page.getUrl().toString()).matches()) {
                matched = true;
            }
        }
        if (!matched) {
            return null;
        }
        Object o = null;
        try {
            o = clazz.newInstance();
            for (FieldExtractor fieldExtractor : fieldExtractors) {
                switch (fieldExtractor.getSource()) {
                    case Html:
                        fieldExtractor.getField().set(o, fieldExtractor.getSelector().select(page.getHtml().toString()));
                        break;
                    case Url:
                        fieldExtractor.getField().set(o, fieldExtractor.getSelector().select(page.getUrl().toString()));
                        break;
                }

            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return o;
    }

    Class getClazz() {
        return clazz;
    }

    List<Pattern> getTargetUrlPatterns() {
        return targetUrlPatterns;
    }
}
