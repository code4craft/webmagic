package us.codecraft.webmagic.annotation;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @author code4crafter@gmail.com <br>
 * @date: 13-8-3 <br>
 * Time: 上午9:51 <br>
 */
public class OOSpider extends Spider{

    /**
     * 使用已定义的抽取规则新建一个Spider。
     *
     * @param pageProcessor 已定义的抽取规则
     */
    public OOSpider(PageProcessor pageProcessor) {
        super(pageProcessor);
    }

    public static OOSpider create(Site site,Class... pageModels) {
        OOSpider ooSpider = new OOSpider(ObjectPageProcessor.create(site, pageModels));
        ooSpider.pipeline(new ObjectPipeline());
        return ooSpider;
    }

}
