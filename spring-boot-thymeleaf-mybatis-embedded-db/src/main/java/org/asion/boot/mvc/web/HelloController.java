package org.asion.boot.mvc.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Asion.
 * @since 16/6/28.
 */
@Controller
public class HelloController {

    @RequestMapping("/hello/{name}")
    public ModelAndView hello(@PathVariable("name") String name) {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("name", name);
        return modelAndView;
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
