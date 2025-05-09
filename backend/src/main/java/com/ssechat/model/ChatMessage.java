package com.ssechat.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

/**
 * message entity - you can find the same contract in the typescript types.d.ts definition
 */
@Document(collection = "chatmessages")
@Getter
@Setter
@NoArgsConstructor
public class ChatMessage {
    @Id
    private String id;
    private String message;
    private String sender;
    @CreatedDate
    private Instant createdDate = Instant.now();
    private String channelId;
}
