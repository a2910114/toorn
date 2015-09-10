package com.mykhaylenko.toorn.service.impl;

import com.mykhaylenko.toorn.database.MessageTable;
import com.mykhaylenko.toorn.exception.MessageNotFoundException;
import com.mykhaylenko.toorn.model.Message;
import com.mykhaylenko.toorn.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pavlo.mykhaylenko on 8/14/2015.
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageTable messageTable;

    @Override
    public List<Message> findMessages(int max, int count) {
        List<Message> messages = messageTable.getMessages();

        if (max <= messages.size()) {
            return messages.subList(getFromIndex(max, count), max);
        }

        return messages.subList(getFromIndex(messages.size(), count), messages.size());
    }

    @Override
    public List<Message> findAllMessages() {
        return messageTable.getMessages();
    }

    @Override
    public Message findOneById(int id) {
        List<Message> messages = messageTable.getMessages();

        if (id <= 0 || id >= messages.size()) {
            throw new MessageNotFoundException("Cannot find specified message.");
        }

        return messages.get(id);
    }

    private int getFromIndex(int max, int count) {
        if (max - count < 0) {
            return 0;
        }
        return max - count;
    }
}
