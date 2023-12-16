package com.example.post_service.consumer;

import com.example.post_service.entity.Post;
import com.example.post_service.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqConsumer.class);
    private final PostService postService;

    @Autowired
    public RabbitMqConsumer(PostService postService) {
        this.postService = postService;
    }

    @RabbitListener(queues = "${post.service.queue.name}")
    public void receiveAddPostRequest(Post post) {
        LOGGER.info("Received {}", post);
        postService.addPost(post);
    }
}
