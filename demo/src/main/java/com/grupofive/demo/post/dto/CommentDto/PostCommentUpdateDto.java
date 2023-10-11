package com.grupofive.demo.post.dto.CommentDto;

public class PostCommentUpdateDto {
    
    private String commentId;
    private String message;
    
    public PostCommentUpdateDto(String commentId, String message) {
        this.commentId = commentId;
        this.message = message;
    }

    public String getCommentId() {
        return commentId;
    }

    public String getMessage() {
        return message;
    }


}
