package us.codecraft.webmagic.model;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selector;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author code4crafter@gmail.com <br>
 * @date: 13-8-1 <br>
 * Time: 下午8:46 <br>
 */
public class ModelPageProcessor implements PageProcessor {

    private List<PageModelExtractor> pageModelExtractorList = new ArrayList<PageModelExtractor>();

    private Site site;

    private Set<Pattern> targetUrlPatterns = new HashSet<Pattern>();

    public static ModelPageProcessor create(Site site, Class... clazzs) {
        ModelPageProcessor modelPageProcessor = new ModelPageProcessor(site);
        for (Class clazz : clazzs) {
            modelPageProcessor.addPageModel(clazz);
        }
        return modelPageProcessor;
    }


    public ModelPageProcessor addPageModel(Class clazz) {
        PageModelExtractor pageModelExtractor = PageModelExtractor.create(clazz);
        targetUrlPatterns.addAll(pageModelExtractor.getTargetUrlPatterns());
        targetUrlPatterns.addAll(pageModelExtractor.getHelpUrlPatterns());
        pageModelExtractorList.add(pageModelExtractor);
        return this;
    }

    private ModelPageProcessor(Site site) {
        this.site = site;
    }

    @Override
    public void process(Page page) {
        for (PageModelExtractor pageModelExtractor : pageModelExtractorList) {
            Object process = pageModelExtractor.process(page);
            if (process == null || (process instanceof List && ((List) process).size() == 0)) {
                page.getResultItems().setSkip(true);
            }
            postProcessPageModel(pageModelExtractor.getClazz(), process);
            page.putField(pageModelExtractor.getClazz().getCanonicalName(), process);
            extractLinks(page, pageModelExtractor.getHelpUrlRegionSelector(), pageModelExtractor.getHelpUrlPatterns());
            extractLinks(page, pageModelExtractor.getTargetUrlRegionSelector(), pageModelExtractor.getTargetUrlPatterns());
        }
    }

    private void extractLinks(Page page, Selector urlRegionSelector, List<Pattern> urlPatterns) {
        List<String> links;
        if (urlRegionSelector == null) {
            links = page.getHtml().links().all();
        } else {
            links = urlRegionSelector.selectList(page.getHtml().toString());
        }
        for (String link : links) {
            for (Pattern targetUrlPattern : urlPatterns) {
                Matcher matcher = targetUrlPattern.matcher(link);
                if (matcher.find()) {
                    page.addTargetRequest(new Request(matcher.group(1)));
                }
            }
        }
    }

    protected void postProcessPageModel(Class clazz, Object object) {
    }

    @Override
    public Site getSite() {
        return site;
    }
}
