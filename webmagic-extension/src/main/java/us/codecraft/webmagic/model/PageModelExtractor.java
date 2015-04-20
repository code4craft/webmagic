package us.codecraft.webmagic.model;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.model.annotation.ComboExtract;
import us.codecraft.webmagic.model.annotation.Configurable;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.ExtractByUrl;
import us.codecraft.webmagic.model.annotation.Formatter;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;
import us.codecraft.webmagic.model.formatter.BasicTypeFormatter;
import us.codecraft.webmagic.model.formatter.ObjectFormatter;
import us.codecraft.webmagic.model.formatter.ObjectFormatters;
import us.codecraft.webmagic.selector.AndSelector;
import us.codecraft.webmagic.selector.OrSelector;
import us.codecraft.webmagic.selector.RegexSelector;
import us.codecraft.webmagic.selector.Selector;
import us.codecraft.webmagic.selector.XpathSelector;
import us.codecraft.webmagic.utils.ClassUtils;
import us.codecraft.webmagic.utils.ExtractorUtils;

/**
 * The main internal logic of page model extractor.
 *
 * @author code4crafter@gmail.com <br>
 * @since 0.2.0
 */
class PageModelExtractor {
	
	private static Map<String, Properties> properties = new HashMap<String, Properties>();

    private List<Pattern> targetUrlPatterns = new ArrayList<Pattern>();

    private Selector targetUrlRegionSelector;

    private List<Pattern> helpUrlPatterns = new ArrayList<Pattern>();

    private Selector helpUrlRegionSelector;

    private Class clazz;

    private List<FieldExtractor> fieldExtractors;

    private Extractor objectExtractor;

    private Logger logger = LoggerFactory.getLogger(getClass());

    public static PageModelExtractor create(Class clazz) {
    	
        PageModelExtractor pageModelExtractor = new PageModelExtractor();
        pageModelExtractor.init(clazz);
        return pageModelExtractor;
    }

    private void init(Class clazz) {
    	
        this.clazz = clazz;
        //before class extractors.
        //initialize configurable annotation.
        initConfigAnnotation(clazz);
        
        initClassExtractors();
        
        fieldExtractors = new ArrayList<FieldExtractor>();
        for (Field field : ClassUtils.getFieldsIncludeSuperClass(clazz)) {
            field.setAccessible(true);
            FieldExtractor fieldExtractor = getAnnotationExtractBy(clazz, field);
            FieldExtractor fieldExtractorTmp = getAnnotationExtractCombo(clazz, field);
            if (fieldExtractor != null && fieldExtractorTmp != null) {
                throw new IllegalStateException("Only one of 'ExtractBy ComboExtract ExtractByUrl' can be added to a field!");
            } else if (fieldExtractor == null && fieldExtractorTmp != null) {
                fieldExtractor = fieldExtractorTmp;
            }
            fieldExtractorTmp = getAnnotationExtractByUrl(clazz, field);
            if (fieldExtractor != null && fieldExtractorTmp != null) {
                throw new IllegalStateException("Only one of 'ExtractBy ComboExtract ExtractByUrl' can be added to a field!");
            } else if (fieldExtractor == null && fieldExtractorTmp != null) {
                fieldExtractor = fieldExtractorTmp;
            }
            if (fieldExtractor != null) {
                checkFormat(field, fieldExtractor);
                fieldExtractors.add(fieldExtractor);
            }
        }
    }

    private void checkFormat(Field field, FieldExtractor fieldExtractor) {
        //check custom formatter
        Formatter formatter = field.getAnnotation(Formatter.class);
        if (formatter != null && !formatter.formatter().equals(ObjectFormatter.class)) {
            if (formatter != null) {
                if (!formatter.formatter().equals(ObjectFormatter.class)) {
                    ObjectFormatter objectFormatter = initFormatter(formatter.formatter());
                    objectFormatter.initParam(formatter.value());
                    fieldExtractor.setObjectFormatter(objectFormatter);
                    return;
                }
            }
        }
        if (!fieldExtractor.isMulti() && !String.class.isAssignableFrom(field.getType())) {
            Class<?> fieldClazz = BasicTypeFormatter.detectBasicClass(field.getType());
            ObjectFormatter objectFormatter = getObjectFormatter(field, fieldClazz, formatter);
            if (objectFormatter == null) {
                throw new IllegalStateException("Can't find formatter for field " + field.getName() + " of type " + fieldClazz);
            } else {
                fieldExtractor.setObjectFormatter(objectFormatter);
            }
        } else if (fieldExtractor.isMulti()) {
            if (!List.class.isAssignableFrom(field.getType())) {
                throw new IllegalStateException("Field " + field.getName() + " must be list");
            }
            if (formatter != null) {
                if (!formatter.subClazz().equals(Void.class)) {
                    ObjectFormatter objectFormatter = getObjectFormatter(field, formatter.subClazz(), formatter);
                    if (objectFormatter == null) {
                        throw new IllegalStateException("Can't find formatter for field " + field.getName() + " of type " + formatter.subClazz());
                    } else {
                        fieldExtractor.setObjectFormatter(objectFormatter);
                    }
                }
            }
        }
    }

    private ObjectFormatter getObjectFormatter(Field field, Class<?> fieldClazz, Formatter formatter) {
        return initFormatter(ObjectFormatters.get(fieldClazz));
    }

    private ObjectFormatter initFormatter(Class<? extends ObjectFormatter> formatterClazz) {
        try {
            return formatterClazz.newInstance();
        } catch (InstantiationException e) {
            logger.error("init ObjectFormatter fail", e);
        } catch (IllegalAccessException e) {
            logger.error("init ObjectFormatter fail", e);
        }
        return null;
    }

    private FieldExtractor getAnnotationExtractByUrl(Class clazz, Field field) {
        FieldExtractor fieldExtractor = null;
        ExtractByUrl extractByUrl = field.getAnnotation(ExtractByUrl.class);
        if (extractByUrl != null) {
            String regexPattern = extractByUrl.value();
            if (regexPattern.trim().equals("")) {
                regexPattern = ".*";
            }
            fieldExtractor = new FieldExtractor(field,
                    new RegexSelector(regexPattern), FieldExtractor.Source.Url, extractByUrl.notNull(),
                    extractByUrl.multi() || List.class.isAssignableFrom(field.getType()));
            Method setterMethod = getSetterMethod(clazz, field);
            if (setterMethod != null) {
                fieldExtractor.setSetterMethod(setterMethod);
            }
        }
        return fieldExtractor;
    }

    private FieldExtractor getAnnotationExtractCombo(Class clazz, Field field) {
        FieldExtractor fieldExtractor = null;
        ComboExtract comboExtract = field.getAnnotation(ComboExtract.class);
        
        //TODO
        fieldExtractor = confFieldExtractCombo(clazz, field);
        
        if(fieldExtractor != null){
        	return fieldExtractor;
        }
        
        if (comboExtract != null) {
            ExtractBy[] extractBies = comboExtract.value();
            Selector selector;
            switch (comboExtract.op()) {
                case And:
                    selector = new AndSelector(ExtractorUtils.getSelectors(extractBies));
                    break;
                case Or:
                    selector = new OrSelector(ExtractorUtils.getSelectors(extractBies));
                    break;
                default:
                    selector = new AndSelector(ExtractorUtils.getSelectors(extractBies));
            }
            fieldExtractor = new FieldExtractor(field, selector, comboExtract.source() == ComboExtract.Source.RawHtml ? FieldExtractor.Source.RawHtml : FieldExtractor.Source.Html,
                    comboExtract.notNull(), comboExtract.multi() || List.class.isAssignableFrom(field.getType()));
            Method setterMethod = getSetterMethod(clazz, field);
            if (setterMethod != null) {
                fieldExtractor.setSetterMethod(setterMethod);
            }
        }
        return fieldExtractor;
    }

    private FieldExtractor confFieldExtractCombo(Class clazz, Field field) {
    	//TODO
        Properties p = properties.get(clazz.getName());
        String conf = null;
        
        if(p != null){
        	conf = p.getProperty(clazz.getName()+".field."+field.getName()+".ExtractCombo");
        }
        
        if(conf != null){
        }
		return null;
	}

	private FieldExtractor getAnnotationExtractBy(Class clazz, Field field) {
        FieldExtractor fieldExtractor = null;
        ExtractBy extractBy = field.getAnnotation(ExtractBy.class);
        
        fieldExtractor = confFieldExtractBy(clazz, field);
        
        if(fieldExtractor != null){
        	return fieldExtractor;
        }else if (extractBy != null) {
            Selector selector = ExtractorUtils.getSelector(extractBy);
            fieldExtractor = new FieldExtractor(field, selector, extractBy.source() == ExtractBy.Source.RawHtml ? FieldExtractor.Source.RawHtml : FieldExtractor.Source.Html,
                    extractBy.notNull(), extractBy.multi() || List.class.isAssignableFrom(field.getType()));
            Method setterMethod = getSetterMethod(clazz, field);
            if (setterMethod != null) {
                fieldExtractor.setSetterMethod(setterMethod);
            }
        }
        return fieldExtractor;
    }

    private FieldExtractor confFieldExtractBy(Class clazz, Field field) {
    	
    	Properties p = properties.get(clazz.getName());
        String conf = null;
        
        if(p != null){
        	conf = p.getProperty(clazz.getName()+".field."+field.getName()+".ExtractBy");
        }
        
        if(conf != null){
        	
        }
		return null;
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

    /**
     * Initial class annotation.
     */
    private void initClassExtractors() {
    	
    	initClassTargetUrl();
    	initClassHelpUrl();
    	initClassExtractBy();
    }
    
    /**
     * Initial class targetURL.
     */
    private void initClassTargetUrl(){
    	
        boolean configured = confClassTargetUrl(clazz);
        if(configured){
        	return;
        }
        
        Annotation annotation = clazz.getAnnotation(TargetUrl.class);
        if (annotation == null) {
            targetUrlPatterns.add(Pattern.compile("(.*)"));
        } else {
            TargetUrl targetUrl = (TargetUrl) annotation;
            String[] value = targetUrl.value();
            for (String s : value) {
                targetUrlPatterns.add(Pattern.compile("(" + s.replace(".", "\\.").replace("*", "[^\"'#]*") + ")"));
            }
            if (!targetUrl.sourceRegion().equals("")) {
                targetUrlRegionSelector = new XpathSelector(targetUrl.sourceRegion());
            }
        }
    }
    
    private void initClassHelpUrl(){
    	
        boolean configured = confClassHelpUrl(clazz);
        if(configured){
        	return;
        }
        
        Annotation annotation = clazz.getAnnotation(HelpUrl.class);
        if (annotation != null) {
            HelpUrl helpUrl = (HelpUrl) annotation;
            String[] value = helpUrl.value();
            for (String s : value) {
                helpUrlPatterns.add(Pattern.compile("(" + s.replace(".", "\\.").replace("*", "[^\"'#]*") + ")"));
            }
            if (!helpUrl.sourceRegion().equals("")) {
                helpUrlRegionSelector = new XpathSelector(helpUrl.sourceRegion());
            }
        }
    }
    
    private boolean confClassHelpUrl(Class<?> clazz) {
    	 Properties p = properties.get(clazz.getName());
         if(p == null){
        	 return false;
         }
         
         String conf = p.getProperty(clazz.getName()+".HelpUrl.value");
         if(conf == null){
        	 return false;
         }
         
         String[] value = conf.replaceAll("[{|}|\"]", "").split(",");
         if(value == null || value.length < 1){
        	 return false;
         }
         
         for (String s : value) {
        	 helpUrlPatterns.add(Pattern.compile("(" + s.replace(".", "\\.").replace("*", "[^\"'#]*") + ")"));
         }
         
         String sourceRegion = p.getProperty(clazz.getName()+".HelpUrl.sourceRegion");
         if (!sourceRegion.equals("")) {
             helpUrlRegionSelector = new XpathSelector(sourceRegion);
         }
         
		return true;
	}
    
    
    private boolean confClassTargetUrl(Class<?> clazz) {
    	
   	 	Properties p = properties.get(clazz.getName());
        if(p == null){
       	 return false;
        }
        
        String conf = p.getProperty(clazz.getName()+".TargetUrl.value");
        if(conf == null){
       	 return false;
        }
        
        String[] value = conf.replaceAll("[{|}|\"]", "").split(",");
        if(value == null || value.length < 1){
       	 return false;
        }
        
        for (String s : value) {
       	 helpUrlPatterns.add(Pattern.compile("(" + s.replace(".", "\\.").replace("*", "[^\"'#]*") + ")"));
        }
        
        String sourceRegion = p.getProperty(clazz.getName()+".TargetUrl.sourceRegion");
        if (!sourceRegion.equals("")) {
            helpUrlRegionSelector = new XpathSelector(sourceRegion);
        }
        
		return true;
	}

	private void initClassExtractBy(){
    	
		boolean configured = confClassExtractBy();
		if(configured){
			return;
		}
		
    	Annotation annotation = clazz.getAnnotation(ExtractBy.class);
        if (annotation != null) {
            ExtractBy extractBy = (ExtractBy) annotation;
            objectExtractor = new Extractor(new XpathSelector(extractBy.value()), Extractor.Source.Html, extractBy.notNull(), extractBy.multi());
        }
    }
	
	/**
	 * Load class ExtractBy from configuration file.
	 * @return true if configured, otherwise false.
	 */
	private boolean confClassExtractBy() {

		Properties p = properties.get(clazz.getName());
		String conf = null;
		if (p == null) {
			return false;
		}
		
		conf = p.getProperty(clazz.getName() + ".ExtractBy.value");
		if (conf == null) {
			return false;
		}
		
		String value = conf.replaceAll("[\"]", "");
		boolean notNull = Boolean.valueOf(p.getProperty(clazz.getName() + ".ExtractBy.notNull", "false"));
		boolean multi = Boolean.valueOf(p.getProperty(clazz.getName() + ".ExtractBy.multi", "false"));
		objectExtractor = new Extractor(new XpathSelector(value),Extractor.Source.Html, notNull, multi);
		return true;
	}

//    private static Properties getConfProperty(String conf){
//    	
//		StringBuilder builder = new StringBuilder(conf.trim());
//		int index = conf.lastIndexOf("=", conf.length());
//		if(index <= 0) return null;
//		
//		while(index > 0){
//			index = conf.lastIndexOf(",", index);
//			if(index > 0){
//				builder.setCharAt(index, '\n');
//				index = conf.lastIndexOf("=",index);
//			}
//		}
//		
//		Reader reader = new StringReader(builder.toString());
//    	Properties pp = new Properties();
//    	
//    	try {
//			pp.load(reader);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//    	
//    	return pp;
//    }
    
    /**
     * Initial Class annotation by classname.
     */
    private void initConfigAnnotation(Class clazz){
    	//check parameter.
    	if(clazz == null){
    		return;
    	}
    	
    	//recursive invocation to get class annotation.
    	initConfigAnnotation(clazz.getSuperclass());
    	Annotation annotation = clazz.getAnnotation(Configurable.class);
    	//Check if annotation is null;
    	if(annotation == null){
    		return;
    	}
    	
    	Configurable configurable = (Configurable) annotation;
        String path = configurable.value();
        //check path.
        if(StringUtils.isEmpty(path)||!path.endsWith(".properties")){
        	return;
        }
        
        Properties thisp = properties.get(path);
        
        if(null != thisp){
        	properties.put(clazz.getName(), thisp);
        	return;
        }
        
        //add properties.
        try {
        	InputStream in = ClassLoader.getSystemResourceAsStream(path);
            Properties p = new Properties();
			p.load(in);
			properties.put(path, p);
			properties.put(clazz.getName(), p);
		} catch (Exception e) {
			return;
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
        if (objectExtractor == null) {
            return processSingle(page, null, true);
        } else {
            if (objectExtractor.multi) {
                List<Object> os = new ArrayList<Object>();
                List<String> list = objectExtractor.getSelector().selectList(page.getRawText());
                for (String s : list) {
                    Object o = processSingle(page, s, false);
                    if (o != null) {
                        os.add(o);
                    }
                }
                return os;
            } else {
                String select = objectExtractor.getSelector().select(page.getRawText());
                Object o = processSingle(page, select, false);
                return o;
            }
        }
    }

    private Object processSingle(Page page, String html, boolean isRaw) {
        Object o = null;
        try {
            o = clazz.newInstance();
            for (FieldExtractor fieldExtractor : fieldExtractors) {
                if (fieldExtractor.isMulti()) {
                    List<String> value;
                    switch (fieldExtractor.getSource()) {
                        case RawHtml:
                            value = page.getHtml().selectDocumentForList(fieldExtractor.getSelector());
                            break;
                        case Html:
                            if (isRaw) {
                                value = page.getHtml().selectDocumentForList(fieldExtractor.getSelector());
                            } else {
                                value = fieldExtractor.getSelector().selectList(html);
                            }
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
                    if (fieldExtractor.getObjectFormatter() != null) {
                        List<Object> converted = convert(value, fieldExtractor.getObjectFormatter());
                        setField(o, fieldExtractor, converted);
                    } else {
                        setField(o, fieldExtractor, value);
                    }
                } else {
                    String value;
                    switch (fieldExtractor.getSource()) {
                        case RawHtml:
                            value = page.getHtml().selectDocument(fieldExtractor.getSelector());
                            break;
                        case Html:
                            if (isRaw) {
                                value = page.getHtml().selectDocument(fieldExtractor.getSelector());
                            } else {
                                value = fieldExtractor.getSelector().select(html);
                            }
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
                    if (fieldExtractor.getObjectFormatter() != null) {
                        Object converted = convert(value, fieldExtractor.getObjectFormatter());
                        if (converted == null && fieldExtractor.isNotNull()) {
                            return null;
                        }
                        setField(o, fieldExtractor, converted);
                    } else {
                        setField(o, fieldExtractor, value);
                    }
                }
            }
            if (AfterExtractor.class.isAssignableFrom(clazz)) {
                ((AfterExtractor) o).afterProcess(page);
            }
        } catch (InstantiationException e) {
            logger.error("extract fail", e);
        } catch (IllegalAccessException e) {
            logger.error("extract fail", e);
        } catch (InvocationTargetException e) {
            logger.error("extract fail", e);
        }
        return o;
    }

    private Object convert(String value, ObjectFormatter objectFormatter) {
        try {
            Object format = objectFormatter.format(value);
            logger.debug("String {} is converted to {}", value, format);
            return format;
        } catch (Exception e) {
            logger.error("convert " + value + " to " + objectFormatter.clazz() + " error!", e);
        }
        return null;
    }

    private List<Object> convert(List<String> values, ObjectFormatter objectFormatter) {
        List<Object> objects = new ArrayList<Object>();
        for (String value : values) {
            Object converted = convert(value, objectFormatter);
            if (converted != null) {
                objects.add(converted);
            }
        }
        return objects;
    }

    private void setField(Object o, FieldExtractor fieldExtractor, Object value) throws IllegalAccessException, InvocationTargetException {
        if (value == null) {
            return;
        }
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
