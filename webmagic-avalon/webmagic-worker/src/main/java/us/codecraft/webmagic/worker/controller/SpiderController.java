package us.codecraft.webmagic.worker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import us.codecraft.webmagic.worker.Worker;

import java.util.HashMap;
import java.util.Map;

/**
 * @author code4crafter@gmail.com
 */
@Controller
@RequestMapping("spider")
public class SpiderController {

    @Autowired
    private Worker worker;

    @RequestMapping("create")
    @ResponseBody
    public Map<String, Object> create(@RequestParam("id") String id) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("code", 200);
        return map;
    }

}
