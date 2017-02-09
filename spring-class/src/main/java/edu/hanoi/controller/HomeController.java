package edu.hanoi.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by trungdovan on 11/27/16.
 * for home page
 */
@Controller
public class HomeController {
    private static final Logger logger = Logger.getLogger(HomeController.class);

    @RequestMapping("/")
    private ModelAndView home() {
        logger.info("in Home Controller - home");

//        ModelAndView mv = new ModelAndView("index");
//        mv.setViewName("index");
//        mv.addObject("message", "Hello Java Clazz");
        ModelAndView mv = new ModelAndView("index", "message", "Hello Java Class");
        return mv;
    }

    @RequestMapping("/dang-nhap")
    private ModelAndView login(@RequestParam(value = "error", required = false) String error) {
        ModelAndView mv = new ModelAndView("login");
        if (error != null)
            mv.addObject("error", error);
        return mv;
    }

    @RequestMapping("/nguoi-dung")
    private ModelAndView forUser() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("message", "This is protected page!");
        mv.setViewName("index");
        return mv;
    }

    @RequestMapping("/json")
    private ModelAndView json() {
        logger.info("in Home Controller - json");

        ModelAndView mv = new ModelAndView("index");
        mv.setViewName("jsonView");
        mv.addObject("message", "Hello Java Clazz");
        return mv;
    }
}
