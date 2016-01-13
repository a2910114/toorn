package com.mykhaylenko.toorn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by User on 15.11.2015.
 */
@Controller
@RequestMapping("/contacts")
public class ContactsController {

    @RequestMapping(method = RequestMethod.GET)
    public String getContactsPage(Model model) {
        return "contacts";
    }
}
