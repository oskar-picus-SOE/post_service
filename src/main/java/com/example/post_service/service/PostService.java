package com.example.post_service.service;

import com.example.post_service.entity.Post;
import com.example.post_service.repository.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PostService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PostService.class);
    private final KafkaTemplate<String, Post> kafkaTemplate;
    private final PostRepository postRepository;
    @Value("${kafka.producer.topic.name}")
    private String topicName;

    @Autowired
    public PostService(KafkaTemplate<String, Post> kafkaTemplate, PostRepository postRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.postRepository = postRepository;
    }

    public void addPost(Post post) {
        postRepository.addPost(post);

        // publish notification to Kafka
        LOGGER.info("Sending notification content {} to Kafka", post);
        Message<Post> message = MessageBuilder.withPayload(post)
                .setHeader(KafkaHeaders.TOPIC, topicName)
                .build();
        kafkaTemplate.send(message);
    }

    public Collection<Post> getAll() {
        return postRepository.findAll();
    }
}
