package us.codecraft.webmagic.avalon.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author code4crafter@gmail.com
 */
@Controller("spider")
@RequestMapping("spider")
public class SpiderController {

    @RequestMapping("create")
    @ResponseBody
    public Map<String, Object> create() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("code", 200);
        return map;
    }
}
