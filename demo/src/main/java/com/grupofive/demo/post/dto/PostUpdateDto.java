package com.grupofive.demo.post.dto;

public class PostUpdateDto {
    
    private String changeId;
    private String changeMessage;
    
    public PostUpdateDto() {
    }

    public PostUpdateDto(String changeId, String changeMessage) {
        this.changeId = changeId;
        this.changeMessage = changeMessage;
    }

    public String getChangeId() {
        return changeId;
    }

    public String getChangeMessage() {
        return changeMessage;
    }

}
