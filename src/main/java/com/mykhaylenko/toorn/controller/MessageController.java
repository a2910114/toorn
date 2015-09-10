package com.mykhaylenko.toorn.controller;

import com.mykhaylenko.toorn.model.Message;
import com.mykhaylenko.toorn.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by pavlo.mykhaylenko on 8/13/2015.
 */
@Controller
@RequestMapping("/messages")
public class MessageController {

    private static final String MAX_INT_AS_STRING = "300"; //TODO: remome hurd code

    @Autowired
    private MessageService messageService;

    @RequestMapping(method = RequestMethod.GET)
    public String messages(Model model) {
        List<Message> messages = messageService.findAllMessages();
        model.addAttribute("messages", messages);
        return "messages";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getOneMessage(Model model,
            @PathVariable("id") int id) {
        Message message = messageService.findOneById(id);
        model.addAttribute("message", message);
        return "message";
    }

    @RequestMapping(value = "/params", method = RequestMethod.GET)
    public String recentMessages(Model model,
            @RequestParam(value = "max", defaultValue = MAX_INT_AS_STRING) int max,
            @RequestParam(value = "count", defaultValue = "20") int count) {
        List<Message> messages = messageService.findMessages(max, count);
        model.addAttribute("messages", messages);
        return "messages";
    }
}
