package com.ssechat.controller;

import com.ssechat.model.ChatMessage;
import com.ssechat.repository.ChatMessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class ChatMessageControllerTest {

    private static final String CHANNEL_1 = "1";
    private static final String CHANNEL_2 = "2";
    private static final String MESSAGE_CH1 = "Hello from channel 1";
    private static final String MESSAGE_CH2 = "Hello from channel 2";
    private static final String SENDER_CH1 = "test-sender-ch1";
    private static final String SENDER_CH2 = "test-sender-ch2";
    private static final String CHATS_URI = "/chats/stream?channelId=" + CHANNEL_1;

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:8"));

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @BeforeEach
    void setUp() {
        chatMessageRepository.deleteAll().block();
    }

    @Test
	void getChatStream() {
        ChatMessage testMessageChannel1 = new ChatMessage();
        testMessageChannel1.setChannelId(CHANNEL_1);
        testMessageChannel1.setMessage(MESSAGE_CH1);
        testMessageChannel1.setSender(SENDER_CH1);

        ChatMessage testMessageChannel2 = new ChatMessage();
        testMessageChannel2.setChannelId(CHANNEL_2);
        testMessageChannel2.setMessage(MESSAGE_CH2);
        testMessageChannel2.setSender(SENDER_CH2);

        chatMessageRepository.save(testMessageChannel1).block();
        chatMessageRepository.save(testMessageChannel2).block();

        Flux<ChatMessage> chatMessageFlux = webTestClient.get().uri(CHATS_URI)
                .accept(MediaType.TEXT_EVENT_STREAM)
                .exchange()
                .expectStatus().isOk()
                .returnResult(ChatMessage.class)
                .getResponseBody();

        StepVerifier.create(chatMessageFlux)
                .expectNextMatches(chatMessage ->
                    CHANNEL_1.equals(chatMessage.getChannelId()) &&
                    MESSAGE_CH1.equals(chatMessage.getMessage()) &&
                    SENDER_CH1.equals(chatMessage.getSender())
                )
                .thenCancel()
                .verify();
    }

	@Test
	void addStreamNoBody() {
		webTestClient.post().uri("/chats")
				.header("Content-Type","application/json")
				.exchange()
				.expectStatus().isBadRequest();
	}

	@Test
	void addStream() {
		webTestClient.post().uri("/chats")
				.header("Content-Type","application/json")
				.body(BodyInserters.fromValue(String.format("""
						{
						    "message": "%s",
						    "channelId": "%s",
						    "sender": "%s",
						    "recipient": "recipient2"
						}""", MESSAGE_CH1, CHANNEL_1, SENDER_CH1)))
				.exchange()
				.expectStatus().isCreated();
	}

}
