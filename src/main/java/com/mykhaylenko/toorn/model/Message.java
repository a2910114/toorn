package com.mykhaylenko.toorn.model;

import java.util.Date;

/**
 * Created by pavlo.mykhaylenko on 8/13/2015.
 */
public class Message {

    private int id;

    private String message;

    private Date creationDate;

    public Message(String message) {
        this.message = message;
        this.creationDate = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
