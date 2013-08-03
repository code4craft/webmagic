package us.codecraft.webmagic.oo;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;

/**
 * @author code4crafter@gmail.com <br>
 * @date: 13-8-3 <br>
 * Time: 上午9:51 <br>
 */
public class OOSpider extends Spider {

    /**
     * OOSpider只能由ObjectPageProcessor创建。
     *
     * @param pageProcessor 已定义的抽取规则
     */
    private ObjectPageProcessor objectPageProcessor;

    private ObjectPipeline objectPipeline;

    protected OOSpider(ObjectPageProcessor objectPageProcessor) {
        super(objectPageProcessor);
        this.objectPageProcessor = objectPageProcessor;
    }

    public OOSpider(Site site, PageModelPipeline pageModelPipeline, Class... pageModels) {
        this(ObjectPageProcessor.create(site, pageModels));
        this.objectPipeline = new ObjectPipeline();
        super.pipeline(objectPipeline);
        for (Class pageModel : pageModels) {
            this.objectPipeline.put(pageModel, pageModelPipeline);
        }
    }

    public static OOSpider create(Site site, Class... pageModels) {
        return new OOSpider(site, new ConsolePageModelPipeline(), pageModels);
    }

    public static OOSpider create(Site site, PageModelPipeline pageModelPipeline, Class... pageModels) {
        return new OOSpider(site, pageModelPipeline, pageModels);
    }

    public OOSpider addPageModel(PageModelPipeline pageModelPipeline, Class... pageModels) {
        for (Class pageModel : pageModels) {
            objectPageProcessor.addPageModel(pageModel);
            objectPipeline.put(pageModel, pageModelPipeline);
        }
        return this;
    }

}
