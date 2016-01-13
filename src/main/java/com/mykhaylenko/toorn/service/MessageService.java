package com.mykhaylenko.toorn.service;

import com.mykhaylenko.toorn.model.Message;

import java.util.List;

/**
 * Created by pavlo.mykhaylenko on 8/13/2015.
 */
public interface MessageService {
    //TODO: delete this

    List<Message> findMessages(int max, int count);

    List<Message> findAllMessages();

    Message findOneById(int id);

}
