package com.grupofive.demo.post.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_Comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String commentId;
    private String postId;
    private String comment;
    
    public Comment() {
    }

    public Comment(String postId, String comment) {
        this.postId = postId;
        this.comment = comment;
    }


    public Comment(String commentId, String postId, String comment) {
        this.commentId = commentId;
        this.postId = postId;
        this.comment = comment;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    

}
