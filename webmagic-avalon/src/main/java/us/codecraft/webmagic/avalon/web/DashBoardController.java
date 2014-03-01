package us.codecraft.webmagic.avalon.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author code4crafter@gmail.com
 */
@Controller("dashboard")
@RequestMapping("/")
public class DashBoardController {

    @RequestMapping
    public ModelAndView index() {
        ModelAndView map = new ModelAndView("dashboard");
        return map;
    }

}
