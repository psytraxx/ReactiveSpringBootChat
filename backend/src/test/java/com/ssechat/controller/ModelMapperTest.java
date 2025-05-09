package com.ssechat.controller;

import com.ssechat.model.ChatMessage;
import com.ssechat.model.ChatMessageDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ModelMapperTest {

    private ModelMapper mapper;

    @BeforeEach
    public void setup() {
        this.mapper = new ModelMapper();
    }

    @Test
    void whenMapDTOWithExactMatch_thenConvertsToEntity() {
        // when similar source object is provided
        ChatMessageDTO input = new ChatMessageDTO();
        ChatMessage chatMessage = this.mapper.map(input, ChatMessage.class);

        // then it maps by default
        assertEquals(input.getSender(), chatMessage.getSender());
        assertEquals(input.getMessage(), chatMessage.getMessage());
        assertEquals(input.getChannelId(), chatMessage.getChannelId());
    }
}
