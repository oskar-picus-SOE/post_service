package com.example.post_service.service;

import com.example.post_service.entity.Post;
import com.example.post_service.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void addPost(Post post) {
        postRepository.addPost(post);
        // todo send notification to Kafka
    }

    public Collection<Post> getAll() {
        return postRepository.findAll();
    }
}
