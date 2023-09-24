package com.grupofive.demo.post.dto;

public class PostUpdateDto {
    
    private String changeId;
    private String changeTitle;
    private String changeMessage;
    
    public PostUpdateDto() {
    }

    public PostUpdateDto(String changeId, String changeTitle,String changeMessage) {
        this.changeId = changeId;
        this.changeTitle = changeTitle;
        this.changeMessage = changeMessage;
    }

    public String getChangeId() {
        return changeId;
    }

    public String getChangeTitle() {
        return changeTitle;
    }

    public String getChangeMessage() {
        return changeMessage;
    }

}
