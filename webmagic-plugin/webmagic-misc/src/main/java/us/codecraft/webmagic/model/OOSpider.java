package us.codecraft.webmagic.model;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;

/**
 * 基于Model的Spider，封装后的入口类。<br>
 * @author code4crafter@gmail.com <br>
 * @date: 13-8-3 <br>
 * Time: 上午9:51 <br>
 */
public class OOSpider extends Spider {

    private ModelPageProcessor modelPageProcessor;

    private ModelPipeline modelPipeline;

    protected OOSpider(ModelPageProcessor modelPageProcessor) {
        super(modelPageProcessor);
        this.modelPageProcessor = modelPageProcessor;
    }

    /**
     * 创建一个爬虫。<br>
     * @param site
     * @param pageModelPipeline
     * @param pageModels
     */
    public OOSpider(Site site, PageModelPipeline pageModelPipeline, Class... pageModels) {
        this(ModelPageProcessor.create(site, pageModels));
        this.modelPipeline = new ModelPipeline();
        super.pipeline(modelPipeline);
        if (pageModelPipeline!=null){
            for (Class pageModel : pageModels) {
                this.modelPipeline.put(pageModel, pageModelPipeline);
            }
        }
    }

    public static OOSpider create(Site site, Class... pageModels) {
        return new OOSpider(site, null, pageModels);
    }

    public static OOSpider create(Site site, PageModelPipeline pageModelPipeline, Class... pageModels) {
        return new OOSpider(site, pageModelPipeline, pageModels);
    }

    public OOSpider addPageModel(PageModelPipeline pageModelPipeline, Class... pageModels) {
        for (Class pageModel : pageModels) {
            modelPageProcessor.addPageModel(pageModel);
            modelPipeline.put(pageModel, pageModelPipeline);
        }
        return this;
    }

}
