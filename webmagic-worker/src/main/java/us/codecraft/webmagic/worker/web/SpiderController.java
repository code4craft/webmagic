package us.codecraft.webmagic.worker.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author code4crafter@gmail.com
 */
@Controller("spider")
@RequestMapping("spider")
public class SpiderController {

    @RequestMapping("create")
    @ResponseBody
    public ModelAndView create(){
        return null;
    }
}
