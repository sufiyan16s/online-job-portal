package com.labouriq.model;

import java.time.LocalDateTime;

public class Message {

    private int id;
    private int senderId;
    private int receiverId;
    private String messageText;
    private LocalDateTime sentAt;

    public Message(int senderId, int receiverId, String messageText) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.messageText = messageText;
    }

    public Message(int id, int senderId, int receiverId, String messageText, LocalDateTime sentAt) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.messageText = messageText;
        this.sentAt = sentAt;
    }

    public int getSenderId() { return senderId; }
    public int getReceiverId() { return receiverId; }
    public String getMessageText() { return messageText; }
    public LocalDateTime getSentAt() { return sentAt; }
}
