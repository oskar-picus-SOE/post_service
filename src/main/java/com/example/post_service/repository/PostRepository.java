package com.example.post_service.repository;

import com.example.post_service.entity.Post;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PostRepository {
    private final List<Post> posts = new ArrayList<>();

    public void addPost(Post post) {
        posts.add(post);
    }
}
