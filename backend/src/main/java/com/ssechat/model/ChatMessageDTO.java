package com.ssechat.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChatMessageDTO {
    private String message;
    private String channelId;
    private String sender;

    private String id;
}
