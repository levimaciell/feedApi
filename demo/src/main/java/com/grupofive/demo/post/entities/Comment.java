package com.grupofive.demo.post.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.grupofive.demo.Views;
import com.grupofive.demo.User.entities.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_Comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JsonView(Views.Public.class)
    private String commentId;

    @JsonView(Views.Public.class)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonView(Views.Public.class)
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    @JsonIgnore
    private Post post;

    public Comment() {
    }

    public Comment(String comment, Post post) {
        this.post= post;
        this.comment = comment;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
