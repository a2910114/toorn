package com.mykhaylenko.toorn.controller;

import com.mykhaylenko.toorn.exception.MessageNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by pavlo.mykhaylenko on 8/20/2015.
 */
@ControllerAdvice
public class ExeptionHandler {

    /**
     * Handle an exception over all Controllers and if {@link MessageNotFoundException} throw
     * user will be redirected to the error/notfound page
     *
     * @return logical name of the view error/notfound
     */
    @ExceptionHandler(MessageNotFoundException.class)
    public String handleMessageNotFoundException() {
        return "error/notfound";
    }
}
