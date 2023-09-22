package com.grupofive.demo.feed.entities;

import java.util.ArrayList;
import java.util.List;

import com.grupofive.demo.post.dto.PostCreationDto;
import com.grupofive.demo.post.entities.Post;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_feeds")
public class Feed {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String feedId;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "feeds_posts",
            joinColumns = @JoinColumn(name = "feed_fk"),
            inverseJoinColumns = @JoinColumn(name = "post_fk"))
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

    public void addPostInFeedList(Post post) {
        this.posts.add(post);
    }

    public String getFeedId() {
        return feedId;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }

    
}
