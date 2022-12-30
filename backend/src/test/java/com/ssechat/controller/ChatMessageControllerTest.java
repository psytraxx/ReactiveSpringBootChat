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
  an easy integration test - depends on a running mongodb which is not ideal and needs further work - use dedicated mongoconfig
  setup test collection - etc ...
 */
public class ChatMessageControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void getChatStream() throws Exception {
        webTestClient.get().uri("/chats/stream?channelId=1")
				.accept(MediaType.TEXT_EVENT_STREAM)
				.exchange()
				.expectStatus().isOk();
    }

	@Test
	public void addStreamNoBody() throws Exception {
		webTestClient.post().uri("/chats")
				.header("Content-Type","application/json")
				.exchange()
				.expectStatus().isBadRequest();
	}

	@Test
	public void addStream() throws Exception {
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
