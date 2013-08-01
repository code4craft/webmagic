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

    private List<PageModelFetcher> pageModelFetcherList;

    private Site site;

    private Set<Pattern> targetUrlPatterns;

    public static ObjectPageProcessor create(Site site, Class... clazzs) {
        List<PageModelFetcher> pageModelFetcherList = new ArrayList<PageModelFetcher>();
        for (Class clazz : clazzs) {
            PageModelFetcher pageModelFetcher = PageModelFetcher.create(clazz);
            pageModelFetcherList.add(pageModelFetcher);
        }
        ObjectPageProcessor objectPageProcessor = new ObjectPageProcessor(site, pageModelFetcherList);
        return objectPageProcessor;
    }

    private ObjectPageProcessor(Site site, List<PageModelFetcher> pageModelFetcherList) {
        this.site = site;
        this.pageModelFetcherList = pageModelFetcherList;
        targetUrlPatterns = new HashSet<Pattern>();
        for (PageModelFetcher pageModelFetcher : pageModelFetcherList) {
            targetUrlPatterns.addAll(pageModelFetcher.getTargetUrlPatterns());
        }
    }

    @Override
    public void process(Page page) {
        for (PageModelFetcher pageModelFetcher : pageModelFetcherList) {
            Object process = pageModelFetcher.process(page);
            page.putField(pageModelFetcher.getClazz().getCanonicalName(), process);
        }
        for (String link : page.getHtml().links().all()) {
            for (Pattern targetUrlPattern : targetUrlPatterns) {
                if (targetUrlPattern.matcher(link).matches()){
                    page.addTargetRequest(new Request(link));
                }
            }
        }
    }

    @Override
    public Site getSite() {
        return site;
    }
}
