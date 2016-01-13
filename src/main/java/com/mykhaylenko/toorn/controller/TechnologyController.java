package com.mykhaylenko.toorn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by User on 15.11.2015.
 */
@Controller
@RequestMapping("/technology")
public class TechnologyController {

    @RequestMapping(method = RequestMethod.GET)
    public String getTechnologyPage(Model model) {
        return "technology";
    }
}
