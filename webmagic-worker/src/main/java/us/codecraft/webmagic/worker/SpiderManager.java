package us.codecraft.webmagic.worker;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.express.WebServer;
import us.codecraft.express.controller.AjaxController;
import us.codecraft.express.controller.ParamMap;
import us.codecraft.express.controller.ResultMap;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.scripts.Language;
import us.codecraft.webmagic.scripts.ScriptProcessor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author code4crafter@gmail.com
 */
@Component
public class SpiderManager implements InitializingBean {

    @Autowired
    private WebServer webServer;

    private Map<String, Spider> spiderMap = new ConcurrentHashMap<String, Spider>();

    public Spider newSpider(ParamMap params) {
        Spider spider = Spider
                .create(new ScriptProcessor(Language.JavaScript, params.get("script"), params.getInt("thread")))
                .thread(params.getInt("thread")).addUrl(params.get("url"));
        spider.start();
        return spider;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        AjaxController newController = new AjaxController() {
            @Override
            public Object ajax(ParamMap params) {
                try {
                    Spider spider = newSpider(params);
                    spiderMap.put(params.get("uuid"), spider);
                    return ResultMap.create().put("code", 200).put("msg", "success");
                } catch (Exception e) {
                    // If you provide worker to user, DO NOT return
                    // e.getMessage()!
                    return ResultMap.create().put("code", 500).put("msg", e.getMessage());
                }
            }
        };
        webServer.post("/new/${uuid}", newController);
        webServer.get("/new/${uuid}", newController);
        webServer.get("/status/${uuid}", new AjaxController() {
            @Override
            public Object ajax(ParamMap params) {
                Spider spider = spiderMap.get(params.get("uuid"));
                ResultMap put = ResultMap.create().put("pageCount", spider.getPageCount())
                        .put("status", spider.getStatus().name()).put("thread", spider.getThreadAlive());
                return put;
            }
        });
    }
}
