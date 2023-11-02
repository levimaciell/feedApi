package com.grupofive.demo.post.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import com.grupofive.demo.Views;
import com.grupofive.demo.User.entities.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JsonView(Views.Public.class)
    private String id;

    @Column(columnDefinition = "TEXT")
    @JsonView(Views.Public.class)
    private String message;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonView(Views.Public.class)
    private User user;

    //Many comments to one post. One post to many comments. One to Many!
    //De post chego em comentário, mas de comentário chego em post?
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    @JsonView(Views.Public.class)
    private List<Comment> comments;
    
    public Post(){

    }

    public Post(String id, String message){
        this.id = id;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    
}
