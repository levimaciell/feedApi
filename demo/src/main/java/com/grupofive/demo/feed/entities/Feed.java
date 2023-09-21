package com.grupofive.demo.feed.entities;

import java.util.ArrayList;
import java.util.List;

import com.grupofive.demo.post.entities.Post;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_feeds")
public class Feed {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String feedId;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Post> posts;

    public Feed() {
        posts = new ArrayList<>();
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public String getFeedId() {
        return feedId;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }

    

    
}
