package com.ssechat.controller;

import com.ssechat.model.ChatMessage;
import com.ssechat.model.ChatMessageDTO;
import com.ssechat.repository.ChatMessageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import jakarta.validation.Valid;

@CrossOrigin(value = { "*" },
        maxAge = 900
)
/*
  get chat message event stream and allow adding of new messages
  crossdomain * open to allow react client  -
  i would remove this and run a nginx in the future serving both client and server
 */
@RestController
public class ChatMessageController {
    @Autowired
    private ChatMessageRepository chatMessageRepo;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/chats")
    @ResponseStatus(HttpStatus.CREATED)
    public void postChat(@Valid @RequestBody ChatMessageDTO chatMessageDTO) {
        ChatMessage chatMessage = modelMapper.map(chatMessageDTO, ChatMessage.class);
        chatMessageRepo.save(chatMessage).subscribe();
    }

    @GetMapping(value = "/chats/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ChatMessage> streamMessages(@RequestParam String channelId) {
        return chatMessageRepo.findWithTailableCursorByChannelId(channelId);
    }
}