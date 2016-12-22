package com.example;

import javax.persistence.*;

/**
 * Created by noelaniekan on 12/21/16.
 */
@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String text;

    public Message(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public Message() {
    }

    public Message(String text) {
        this.text = text;
    }
}
