package com.grupofive.demo.feed.entities;

import java.util.HashMap;
import java.util.Map;

import com.grupofive.demo.post.entities.Post;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_feeds")
public class Feed {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedId;
    
    private Map<String, Post> posts;

    public Feed() {
        posts = new HashMap<>();
    }

    public Map<String, Post> getPosts() {
        return posts;
    }

    public void setPosts(Map<String, Post> posts) {
        this.posts = posts;
    }

    
}
