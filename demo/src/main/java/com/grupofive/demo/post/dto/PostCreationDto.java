package com.grupofive.demo.post.dto;

public class PostCreationDto {

    private String postTitle;
    private String postMessage;

    public PostCreationDto() {
    }

    public PostCreationDto(String postTitle, String postMessage) {
        this.postTitle = postTitle;
        this.postMessage = postMessage;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public String getPostMessage() {

        return postMessage;
    }



}
