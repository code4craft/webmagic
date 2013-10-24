package us.codecraft.webmagic.model;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.direct.Param;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.Collection;

/**
 * The spider for page model extractor.<br>
 * In webmagic, we call a POJO containing extract result as "page model". <br>
 * You can customize a crawler by write a page model with annotations. <br>
 * Such as:
 * <pre>
 * {@literal @}TargetUrl("http://my.oschina.net/flashsword/blog/\\d+")
 *  public class OschinaBlog{
 *
 *      {@literal @}ExtractBy("//title")
 *      private String title;
 *
 *      {@literal @}ExtractBy(value = "div.BlogContent",type = ExtractBy.Type.Css)
 *      private String content;
 *
 *      {@literal @}ExtractBy(value = "//div[@class='BlogTags']/a/text()", multi = true)
 *      private List<String> tags;
 * }
 * </pre>
 * And start the spider by:
 * <pre>
 *   OOSpider.create(Site.me().addStartUrl("http://my.oschina.net/flashsword/blog")
 *        ,new JsonFilePageModelPipeline(), OschinaBlog.class).run();
 * }
 * </pre>
 *
 * @author code4crafter@gmail.com <br>
 * @since 0.2.0
 */
public class OOSpider extends Spider {

    private ModelPageProcessor modelPageProcessor;

    private ModelPipeline modelPipeline;

    protected OOSpider(ModelPageProcessor modelPageProcessor) {
        super(modelPageProcessor);
        this.modelPageProcessor = modelPageProcessor;
    }

    public OOSpider(PageProcessor pageProcessor) {
        super(pageProcessor);
    }

    /**
     * create a spider
     *
     * @param site
     * @param pageModelPipeline
     * @param pageModels
     */
    public OOSpider(Site site, PageModelPipeline pageModelPipeline, Class... pageModels) {
        this(ModelPageProcessor.create(site, pageModels));
        this.modelPipeline = new ModelPipeline();
        super.addPipeline(modelPipeline);
        if (pageModelPipeline != null) {
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

    /**
     * @since 0.3.3
     * NO implement yet!
     */
    public static OOSpider direct(Site site, PageModelPipeline pageModelPipeline, Class... pageModels) {
        return new OOSpider(site, pageModelPipeline, pageModels);
    }

    /**
     * @since 0.3.3
     * NO implement yet!
     */
    public static OOSpider direct(PageModelPipeline pageModelPipeline, Class... pageModels) {
        return new OOSpider(null, pageModelPipeline, pageModels);
    }

    /**
     * @since 0.3.3
     * NO implement yet!
     */
    public static OOSpider direct(Class... pageModels) {
        return new OOSpider(null, null, pageModels);
    }

    public static OOSpider direct(Collection<Param> params, Class... pageModels) {
        return new OOSpider(null, null, pageModels);
    }

    public OOSpider addPageModel(PageModelPipeline pageModelPipeline, Class... pageModels) {
        for (Class pageModel : pageModels) {
            modelPageProcessor.addPageModel(pageModel);
            modelPipeline.put(pageModel, pageModelPipeline);
        }
        return this;
    }

}
