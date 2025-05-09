package com.ssechat;

import com.ssechat.model.ChatMessage;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.Disposable;

/**
 * Launch Spring Boot app and create capped collection for chat message collection.
 */
@SpringBootApplication
public class Application {

    final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * Ensures the capped collection for chat messages exists on startup.
     */
    @Bean
    ApplicationRunner onStart(ReactiveMongoTemplate template) {

        return args -> {

            // Mongo will create a new collection when a document gets persisted and doesn't exist.
            // In this case, we need to create it on startup because it needs to be a capped collection.

            CollectionOptions options = CollectionOptions.empty()
                    .capped().size(5000000)
                    .maxDocuments(10000);

            try {
                Disposable result = template.createCollection(ChatMessage.class, options).subscribe();
                result.dispose();
            } catch (Exception e) {
                // Not a fan of generic catch-all, but didn't want to lose too much time here.
                logger.info(e.getMessage());
            }

        };
    }

    /**
     * Provides a ModelMapper bean for DTO/entity mapping.
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
