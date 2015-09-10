package com.mykhaylenko.toorn.database;

import com.mykhaylenko.toorn.model.Message;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pavlo.mykhaylenko on 8/13/2015.
 */
@Component
public class MessageTable {

    private static List<Message> messages = new ArrayList<>();

    static {
        messages.add(new Message("message one"));
        messages.add(new Message("message two"));
        messages.add(new Message("message three"));
        messages.add(new Message("message four"));
    }

    public static List<Message> getMessages() {
        return messages;
    }

    public static void setMessages(List<Message> messages) {
        MessageTable.messages = messages;
    }
}
