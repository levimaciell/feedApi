package com.grupofive.demo.post.dto;

public class PostCommentDto {
    
    private String postID;
    private String comment;

    public PostCommentDto(String postID, String comment) {
        this.postID = postID;
        this.comment = comment;
    }

    public String getPostID() {
        return postID;
    }

    public String getComment() {
        return comment;
    }

}
