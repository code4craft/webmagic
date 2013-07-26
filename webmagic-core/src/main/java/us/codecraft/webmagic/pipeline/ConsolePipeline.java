package us.codecraft.webmagic.pipeline;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;

import java.util.Map;

/**
 * 命令行输出抽取结果。可用于测试。<br>
 * @author code4crafter@gmail.com <br>
 * Date: 13-4-21
 * Time: 下午1:45
 */
public class ConsolePipeline implements  Pipeline{

    @Override
    public void process(ResultItems resultItems,Task task) {
        if (resultItems.isSkip()){
            return;
        }
        System.out.println("get page: "+resultItems.getRequest().getUrl());
        for (Map.Entry<String, Object> entry : resultItems.getAll().entrySet()) {
            System.out.println(entry.getKey()+":\t"+entry.getValue());
        }
    }
}
