package com.mykhaylenko.toorn.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by pavlo.mykhaylenko on 8/20/2015.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Message not found")
public class MessageNotFoundException extends RuntimeException {

    public MessageNotFoundException(String message) {
        super(message);
    }

    public MessageNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
