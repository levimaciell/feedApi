package com.grupofive.demo.post.dto;

public class PostUpdateDto {
    
    private Long changeId;
    private String changeMessage;
    
    public PostUpdateDto() {
    }

    public PostUpdateDto(Long changeId, String changeMessage) {
        this.changeId = changeId;
        this.changeMessage = changeMessage;
    }

    public Long getChangeId() {
        return changeId;
    }

    public String getChangeMessage() {
        return changeMessage;
    }

}
