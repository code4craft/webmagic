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
class PageModelFetcher {

    private List<Pattern> targetUrlPatterns;

    private Class clazz;

    private List<FieldFetcher> fieldFetchers;

    public static PageModelFetcher create(Class clazz) {
        PageModelFetcher pageModelFetcher = new PageModelFetcher();
        pageModelFetcher.init(clazz);
        return pageModelFetcher;
    }

    private void init(Class clazz) {
        this.clazz = clazz;
        initTargetUrlPatterns();
        fieldFetchers = new ArrayList<FieldFetcher>();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            Fetcher fetcher = field.getAnnotation(Fetcher.class);
            String value = fetcher.value();
            Selector selector;
            switch (fetcher.type()) {
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
            fieldFetchers.add(new FieldFetcher(field, selector));
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
                targetUrlPatterns.add(Pattern.compile(s.replace(".","\\.").replace("*","[^\"'#]*")));
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
            for (FieldFetcher fieldFetcher : fieldFetchers) {
                fieldFetcher.getField().set(o, fieldFetcher.getSelector().select(page.getHtml().toString()));
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
