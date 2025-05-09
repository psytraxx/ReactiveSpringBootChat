package com.ssechat.controller;

import com.ssechat.model.ChatMessage;
import com.ssechat.model.ChatMessageDTO;
import com.ssechat.repository.ChatMessageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import jakarta.validation.Valid;

@CrossOrigin(value = {"*"},
        maxAge = 900
)
/*
  Get chat message event stream and allow adding of new messages.
  Cross-domain * open to allow React client.
  I would remove this and run an Nginx in the future serving both client and server.
 */
@RestController
public class ChatMessageController {

    private final ChatMessageRepository chatMessageRepo;

    private final ModelMapper modelMapper;

    /**
     * Constructor for dependency injection.
     */
    public ChatMessageController(ChatMessageRepository chatMessageRepo, ModelMapper modelMapper) {
        this.chatMessageRepo = chatMessageRepo;
        this.modelMapper = modelMapper;
    }

    /**
     * Add a new chat message.
     */
    @PostMapping("/chats")
    @ResponseStatus(HttpStatus.CREATED)
    public void postChat(@Valid @RequestBody ChatMessageDTO chatMessageDTO) {
        ChatMessage chatMessage = modelMapper.map(chatMessageDTO, ChatMessage.class);
        chatMessageRepo.save(chatMessage).subscribe();
    }

    /**
     * Stream chat messages for a specific channel.
     */
    @GetMapping(value = "/chats/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ChatMessage> streamMessages(@RequestParam String channelId) {
        return chatMessageRepo.findWithTailableCursorByChannelId(channelId);
    }
}