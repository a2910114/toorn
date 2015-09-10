package com.mykhaylenko.toorn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by pavlo.mykhaylenko on 8/12/2015.
 */
@Controller
@RequestMapping({ "/", "/home" })
public class HomeController {

    @RequestMapping(method = RequestMethod.GET)
    public String home() {
        return "home";
    }
}
