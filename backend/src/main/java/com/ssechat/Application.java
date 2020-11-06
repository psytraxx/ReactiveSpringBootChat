package com.ssechat;

import com.ssechat.model.ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.Disposable;

@SpringBootApplication
public class Application {

    Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    ApplicationRunner onStart(ReactiveMongoTemplate template) {

        return args -> {

            // mongo will create a new collection a document gets persisted and doesnt exits -
            // in this case i had to create it on startup because it needs to be a capped collection

            CollectionOptions options = CollectionOptions.empty()
                    .capped().size(5000000)
                    .maxDocuments(10000);

            try {
                Disposable result = template.createCollection(ChatMessage.class, options).subscribe();
                result.dispose();
            } catch (Exception e) {
                // i am not a fan of generic catchall - still - i didnt want to loose to much time here
                logger.info(e.getMessage());
            }

        };
    }

}
