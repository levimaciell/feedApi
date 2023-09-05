package com.grupofive.demo.post.exceptions;

public class PostServiceException extends RuntimeException{
    public PostServiceException(String msg){
        super(msg);
    }
}
