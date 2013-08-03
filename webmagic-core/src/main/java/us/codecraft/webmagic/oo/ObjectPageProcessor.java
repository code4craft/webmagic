package us.codecraft.webmagic.oo;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * @author code4crafter@gmail.com <br>
 * @date: 13-8-1 <br>
 * Time: 下午8:46 <br>
 */
public class ObjectPageProcessor implements PageProcessor {

    private List<PageModelExtractor> pageModelExtractorList = new ArrayList<PageModelExtractor>();

    private Site site;

    private Set<Pattern> targetUrlPatterns = new HashSet<Pattern>();

    public static ObjectPageProcessor create(Site site, Class... clazzs) {
        ObjectPageProcessor objectPageProcessor = new ObjectPageProcessor(site);
        for (Class clazz : clazzs) {
            objectPageProcessor.addPageModel(clazz);
        }
        return objectPageProcessor;
    }


    public ObjectPageProcessor addPageModel(Class clazz){
        PageModelExtractor pageModelExtractor = PageModelExtractor.create(clazz);
        targetUrlPatterns.addAll(pageModelExtractor.getTargetUrlPatterns());
        targetUrlPatterns.addAll(pageModelExtractor.getHelpUrlPatterns());
        pageModelExtractorList.add(pageModelExtractor);
        return this;
    }

    private ObjectPageProcessor(Site site) {
        this.site = site;
    }

    @Override
    public void process(Page page) {
        for (PageModelExtractor pageModelExtractor : pageModelExtractorList) {
            Object process = pageModelExtractor.process(page);
            if (process==null){
                page.getResultItems().setSkip(true);
            }
            postProcessPageModel(pageModelExtractor.getClazz(), process);
            page.putField(pageModelExtractor.getClazz().getCanonicalName(), process);
        }
        for (String link : page.getHtml().links().all()) {
            for (Pattern targetUrlPattern : targetUrlPatterns) {
                if (targetUrlPattern.matcher(link).matches()){
                    page.addTargetRequest(new Request(link));
                }
            }
        }
    }

    protected void postProcessPageModel(Class clazz, Object object){
    }

    @Override
    public Site getSite() {
        return site;
    }
}
