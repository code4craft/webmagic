package us.codecraft.webmagic.model;

import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Model主要逻辑类。将一个带注解的POJO转换为一个PageModelExtractor。<br>
 *
 * @author code4crafter@gmail.com <br>
 * @date: 13-8-1 <br>
 * Time: 下午9:33 <br>
 */
class PageModelExtractor {

    private List<Pattern> targetUrlPatterns = new ArrayList<Pattern>();

    private Selector targetUrlRegionSelector;

    private List<Pattern> helpUrlPatterns = new ArrayList<Pattern>();

    private Selector helpUrlRegionSelector;

    private Class clazz;

    private List<FieldExtractor> fieldExtractors;

    private Extractor extractor;

    public static PageModelExtractor create(Class clazz) {
        PageModelExtractor pageModelExtractor = new PageModelExtractor();
        pageModelExtractor.init(clazz);
        return pageModelExtractor;
    }

    private void init(Class clazz) {
        this.clazz = clazz;
        initClassExtractors();
        fieldExtractors = new ArrayList<FieldExtractor>();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            FieldExtractor fieldExtractor = getAnnotationExtractBy(clazz, field);
            FieldExtractor fieldExtractorTmp = getAnnotationExtractByRaw(clazz, field);
            if (fieldExtractor != null && fieldExtractorTmp != null) {
                throw new IllegalStateException("Only one of 'ExtractBy ExtractByRaw ExtractByUrl' can be added to a field!");
            } else if (fieldExtractor == null && fieldExtractorTmp != null) {
                fieldExtractor = fieldExtractorTmp;
            }
            // ExtractBy2 & ExtractBy3
            addAnnotationExtractBy2(clazz, fieldExtractor);
            addAnnotationExtractBy3(clazz, fieldExtractor);
            fieldExtractorTmp = getAnnotationExtractByUrl(clazz, field);
            if (fieldExtractor != null && fieldExtractorTmp != null) {
                throw new IllegalStateException("Only one of 'ExtractBy ExtractByRaw ExtractByUrl' can be added to a field!");
            } else if (fieldExtractor == null && fieldExtractorTmp != null) {
                fieldExtractor = fieldExtractorTmp;
            }
            if (fieldExtractor != null) {
                if (!fieldExtractor.isMulti() && !String.class.isAssignableFrom(field.getType())) {
                    throw new IllegalStateException("Field " + field.getName() + " must be string");
                } else if (fieldExtractor.isMulti() && !List.class.isAssignableFrom(field.getType())) {
                    throw new IllegalStateException("Field " + field.getName() + " must be list");
                }
            }

        }
    }

    private FieldExtractor getAnnotationExtractByUrl(Class clazz, Field field) {
        FieldExtractor fieldExtractor = null;
        ExtractByUrl extractByUrl = field.getAnnotation(ExtractByUrl.class);
        if (extractByUrl != null) {
            String regexPattern = extractByUrl.value();
            if (regexPattern.trim().equals("")) {
                regexPattern = ".*";
            }
            fieldExtractor = new FieldExtractor(field, new RegexSelector(regexPattern), FieldExtractor.Source.Url, extractByUrl.notNull(), extractByUrl.multi());
            Method setterMethod = getSetterMethod(clazz, field);
            if (setterMethod != null) {
                fieldExtractor.setSetterMethod(setterMethod);
            }
        }
        return fieldExtractor;
    }

    private FieldExtractor getAnnotationExtractBy(Class clazz, Field field) {
        FieldExtractor fieldExtractor = null;
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
                case XPath2:
                    selector = new Xpath2Selector(value);
                    break;
                default:
                    selector = new Xpath2Selector(value);
            }
            fieldExtractor = new FieldExtractor(field, selector, FieldExtractor.Source.Html, extractBy.notNull(), extractBy.multi());
            Method setterMethod = getSetterMethod(clazz, field);
            if (setterMethod != null) {
                fieldExtractor.setSetterMethod(setterMethod);
            }
        }
        return fieldExtractor;
    }

    private void addAnnotationExtractBy2(Class clazz, FieldExtractor fieldExtractor) {
        ExtractBy2 extractBy = fieldExtractor.getField().getAnnotation(ExtractBy2.class);
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
                case XPath2:
                    selector = new Xpath2Selector(value);
                    break;
                default:
                    selector = new Xpath2Selector(value);
            }
            fieldExtractor.setSelector(new AndSelector(fieldExtractor.getSelector(), selector));
        }
    }

    private void addAnnotationExtractBy3(Class clazz, FieldExtractor fieldExtractor) {
        ExtractBy3 extractBy = fieldExtractor.getField().getAnnotation(ExtractBy3.class);
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
                case XPath2:
                    selector = new Xpath2Selector(value);
                    break;
                default:
                    selector = new Xpath2Selector(value);
            }
            fieldExtractor.setSelector(new AndSelector(fieldExtractor.getSelector(), selector));
        }
    }

    private FieldExtractor getAnnotationExtractByRaw(Class clazz, Field field) {
        FieldExtractor fieldExtractor = null;
        ExtractByRaw extractByRaw = field.getAnnotation(ExtractByRaw.class);
        if (extractByRaw != null) {
            String value = extractByRaw.value();
            Selector selector;
            switch (extractByRaw.type()) {
                case Css:
                    selector = new CssSelector(value);
                    break;
                case Regex:
                    selector = new RegexSelector(value);
                    break;
                case XPath:
                    selector = new XpathSelector(value);
                    break;
                case XPath2:
                    selector = new Xpath2Selector(value);
                    break;
                default:
                    selector = new Xpath2Selector(value);
            }
            fieldExtractor = new FieldExtractor(field, selector, FieldExtractor.Source.RawHtml, extractByRaw.notNull(), extractByRaw.multi());
            Method setterMethod = getSetterMethod(clazz, field);
            if (setterMethod != null) {
                fieldExtractor.setSetterMethod(setterMethod);
            }
        }
        return fieldExtractor;
    }

    public static Method getSetterMethod(Class clazz, Field field) {
        String name = "set" + StringUtils.capitalize(field.getName());
        try {
            Method declaredMethod = clazz.getDeclaredMethod(name, field.getType());
            declaredMethod.setAccessible(true);
            return declaredMethod;
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    private void initClassExtractors() {
        Annotation annotation = clazz.getAnnotation(TargetUrl.class);
        if (annotation == null) {
            targetUrlPatterns.add(Pattern.compile(".*"));
        } else {
            TargetUrl targetUrl = (TargetUrl) annotation;
            String[] value = targetUrl.value();
            for (String s : value) {
                targetUrlPatterns.add(Pattern.compile("(" + s.replace(".", "\\.").replace("*", "[^\"'#]*") + ")"));
            }
            if (!targetUrl.sourceRegion().equals("")) {
                targetUrlRegionSelector = new Xpath2Selector(targetUrl.sourceRegion());
            }
        }
        annotation = clazz.getAnnotation(HelpUrl.class);
        if (annotation != null) {
            HelpUrl helpUrl = (HelpUrl) annotation;
            String[] value = helpUrl.value();
            for (String s : value) {
                helpUrlPatterns.add(Pattern.compile("(" + s.replace(".", "\\.").replace("*", "[^\"'#]*") + ")"));
            }
            if (!helpUrl.sourceRegion().equals("")) {
                helpUrlRegionSelector = new Xpath2Selector(helpUrl.sourceRegion());
            }
        }
        annotation = clazz.getAnnotation(ExtractBy.class);
        if (annotation != null) {
            ExtractBy extractBy = (ExtractBy) annotation;
            extractor = new Extractor(new Xpath2Selector(extractBy.value()), Extractor.Source.Html, extractBy.notNull(), extractBy.multi());
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
        if (extractor == null) {
            return processSingle(page, page.getHtml().toString());
        } else {
            if (extractor.multi) {
                List<Object> os = new ArrayList<Object>();
                List<String> list = extractor.getSelector().selectList(page.getHtml().toString());
                for (String s : list) {
                    Object o = processSingle(page, s);
                    if (o != null) {
                        os.add(o);
                    }
                }
                return os;
            } else {
                String select = extractor.getSelector().select(page.getHtml().toString());
                Object o = processSingle(page, select);
                return o;
            }
        }
    }

    private Object processSingle(Page page, String html) {
        Object o = null;
        try {
            o = clazz.newInstance();
            for (FieldExtractor fieldExtractor : fieldExtractors) {
                if (fieldExtractor.isMulti()) {
                    List<String> value;
                    switch (fieldExtractor.getSource()) {
                        case RawHtml:
                            value = fieldExtractor.getSelector().selectList(page.getHtml().toString());
                            break;
                        case Html:
                            value = fieldExtractor.getSelector().selectList(html);
                            break;
                        case Url:
                            value = fieldExtractor.getSelector().selectList(page.getUrl().toString());
                            break;
                        default:
                            value = fieldExtractor.getSelector().selectList(html);
                    }
                    if ((value == null || value.size() == 0) && fieldExtractor.isNotNull()) {
                        return null;
                    }
                    setField(o, fieldExtractor, value);
                } else {
                    String value;
                    switch (fieldExtractor.getSource()) {
                        case RawHtml:
                            value = fieldExtractor.getSelector().select(page.getHtml().toString());
                            break;
                        case Html:
                            value = fieldExtractor.getSelector().select(html);
                            break;
                        case Url:
                            value = fieldExtractor.getSelector().select(page.getUrl().toString());
                            break;
                        default:
                            value = fieldExtractor.getSelector().select(html);
                    }
                    if (value == null && fieldExtractor.isNotNull()) {
                        return null;
                    }
                    setField(o, fieldExtractor, value);
                }
            }
            if (AfterExtractor.class.isAssignableFrom(clazz)) {
                ((AfterExtractor) o).afterProcess(page);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return o;
    }

    private void setField(Object o, FieldExtractor fieldExtractor, Object value) throws IllegalAccessException, InvocationTargetException {
        if (fieldExtractor.getSetterMethod() != null) {
            fieldExtractor.getSetterMethod().invoke(o, value);
        }
        fieldExtractor.getField().set(o, value);
    }

    Class getClazz() {
        return clazz;
    }

    List<Pattern> getTargetUrlPatterns() {
        return targetUrlPatterns;
    }

    List<Pattern> getHelpUrlPatterns() {
        return helpUrlPatterns;
    }

    Selector getTargetUrlRegionSelector() {
        return targetUrlRegionSelector;
    }

    Selector getHelpUrlRegionSelector() {
        return helpUrlRegionSelector;
    }
}
