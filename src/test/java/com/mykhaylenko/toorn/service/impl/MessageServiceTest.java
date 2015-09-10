package com.mykhaylenko.toorn.service.impl;

import com.mykhaylenko.toorn.config.ContextConfig;
import com.mykhaylenko.toorn.model.Message;
import com.mykhaylenko.toorn.service.MessageService;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by pavlo.mykhaylenko on 8/14/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ContextConfig.class })
public class MessageServiceTest extends TestCase {

    @Autowired
    private MessageService messageService;

    @Test
    public void testFindMessages() throws Exception {
        List<Message> messages = messageService.findMessages(4, 4);
        int expResult = 4;
        assertEquals(expResult, messages.size());
    }

    @Test
    public void testFindMessagesTwoElements() throws Exception {
        List<Message> messages = messageService.findMessages(3, 10);
        int expResult = 3;
        assertEquals(expResult, messages.size());
    }

    @Test
    public void testFindMessagesBadIndexes() throws Exception {
        List<Message> messages = messageService.findMessages(1000, 50);
        int expResult = 4;
        assertEquals(expResult, messages.size());
    }

    @Test
    public void testFindAllMessages() throws Exception {
        List<Message> messages = messageService.findAllMessages();
        int expResult = 4;
        assertEquals(expResult, messages.size());
    }
}