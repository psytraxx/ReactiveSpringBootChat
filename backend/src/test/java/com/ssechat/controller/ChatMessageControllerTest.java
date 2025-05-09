package com.ssechat.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
/*
  An easy integration test - depends on a running MongoDB which is not ideal and needs further work.
  Use dedicated Mongo config, setup test collection, etc.
 */
class ChatMessageControllerTest {

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
