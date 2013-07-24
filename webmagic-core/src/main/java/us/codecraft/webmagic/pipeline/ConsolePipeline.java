package us.codecraft.webmagic.pipeline;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.selector.Selectable;

import java.util.Map;

/**
 * 命令行输出抽取结果。可用于测试。<br>
 * @author code4crafter@gmail.com <br>
 * Date: 13-4-21
 * Time: 下午1:45
 */
public class ConsolePipeline implements  Pipeline{

    @Override
    public void process(Page page,Task task) {
        System.out.println("get page: "+page.getUrl());
        for (Map.Entry<String, Selectable> entry : page.getFields().entrySet()) {
            System.out.println(entry.getKey()+":\t"+entry.getValue().toStrings());
        }
        if (page.getExtra()!=null){
            System.out.println(page.getExtra());
        }
    }
}
