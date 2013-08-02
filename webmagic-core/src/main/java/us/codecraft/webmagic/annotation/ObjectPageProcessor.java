package us.codecraft.webmagic.annotation;

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
 * @author yihua.huang@dianping.com <br>
 * @date: 13-8-1 <br>
 * Time: 下午8:46 <br>
 */
public class ObjectPageProcessor implements PageProcessor {

    private List<PageModelExtractor> pageModelExtractorList;

    private Site site;

    private Set<Pattern> targetUrlPatterns;

    public static ObjectPageProcessor create(Site site, Class... clazzs) {
        List<PageModelExtractor> pageModelExtractorList = new ArrayList<PageModelExtractor>();
        for (Class clazz : clazzs) {
            PageModelExtractor pageModelExtractor = PageModelExtractor.create(clazz);
            pageModelExtractorList.add(pageModelExtractor);
        }
        ObjectPageProcessor objectPageProcessor = new ObjectPageProcessor(site, pageModelExtractorList);
        return objectPageProcessor;
    }

    private ObjectPageProcessor(Site site, List<PageModelExtractor> pageModelExtractorList) {
        this.site = site;
        this.pageModelExtractorList = pageModelExtractorList;
        targetUrlPatterns = new HashSet<Pattern>();
        for (PageModelExtractor pageModelExtractor : pageModelExtractorList) {
            targetUrlPatterns.addAll(pageModelExtractor.getTargetUrlPatterns());
            targetUrlPatterns.addAll(pageModelExtractor.getHelpUrlPatterns());
        }
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
