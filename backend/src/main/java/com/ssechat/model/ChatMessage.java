package com.ssechat.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "chatmessages")
public class ChatMessage {
    @Id
    private String id;
    private String message;
    private String sender;
    private String recipient;
    private String channelId;

    public ChatMessage(String message, String sender, String recipient, String channelId) {
        this.message = message;
        this.sender = sender;
        this.recipient = recipient;
        this.channelId = channelId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }
}
