package com.ssechat.controller;

import com.ssechat.model.ChatMessage;
import com.ssechat.model.ChatMessageDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ModelMapperTest {

    private static final String MESSAGE = "test-message";
    private static final String SENDER = "test-sender";
    private static final String CHANNEL_ID = "42";
    private static final String ID = "id-123";

    private ModelMapper mapper;

    @BeforeEach
    void setup() {
        this.mapper = new ModelMapper();
    }

    @Test
    void whenMapDTOWithExactMatch_thenConvertsToEntity() {
        ChatMessageDTO input = new ChatMessageDTO();
        input.setMessage(MESSAGE);
        input.setSender(SENDER);
        input.setChannelId(CHANNEL_ID);
        input.setId(ID);

        ChatMessage chatMessage = this.mapper.map(input, ChatMessage.class);

        assertEquals(SENDER, chatMessage.getSender());
        assertEquals(MESSAGE, chatMessage.getMessage());
        assertEquals(CHANNEL_ID, chatMessage.getChannelId());
        assertEquals(ID, chatMessage.getId());
    }
}
