package com.grupofive.demo.post.dto.PostDto;

public class PostCreationDto {

    private String postMessage;

    public PostCreationDto() {
    }

    public PostCreationDto(String postMessage) {
        this.postMessage = postMessage;
    }

    public String getPostMessage() {

        return postMessage;
    }



}
