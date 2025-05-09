package com.ssechat.controller;

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

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
/*
  Integration test using Testcontainers to spin up a MongoDB instance.
 */
class ChatMessageControllerTest {

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:8")); // Or your desired MongoDB version

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    private WebTestClient webTestClient;

    @Test
	void getChatStream() {
        webTestClient.get().uri("/chats/stream?channelId=1")
				.accept(MediaType.TEXT_EVENT_STREAM)
				.exchange()
				.expectStatus().isOk();
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
				.body(BodyInserters.fromValue("""
						{
						    "message": "xxxx",
						    "channelId": "1",
						    "sender": "sender2",
						    "recipient": "recipient2"
						}"""))
				.exchange()
				.expectStatus().isCreated();
	}

}
