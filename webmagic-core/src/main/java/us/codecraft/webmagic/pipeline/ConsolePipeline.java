package us.codecraft.webmagic.pipeline;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.selector.Selectable;

import java.util.Map;

/**
 * User: cairne
 * Date: 13-4-21
 * Time: 下午1:45
 */
public class ConsolePipeline implements  Pipeline{

    @Override
    public void process(Page page,Site site) {
        System.out.println("get page: "+page.getUrl());
        for (Map.Entry<String, Selectable> entry : page.getFields().entrySet()) {
            System.out.println(entry.getKey()+":\t"+entry.getValue().toStrings());
        }
    }
}
