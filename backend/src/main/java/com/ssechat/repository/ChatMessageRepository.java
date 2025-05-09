package com.ssechat.repository;

import com.ssechat.model.ChatMessage;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * This is mostly black magic - I used this to save some time.
 * Repository for chat messages with tailable cursor support.
 */

@Repository
public interface ChatMessageRepository extends ReactiveMongoRepository<ChatMessage, String> {
    @Tailable
    Flux<ChatMessage> findWithTailableCursorByChannelId(String channelId);
}