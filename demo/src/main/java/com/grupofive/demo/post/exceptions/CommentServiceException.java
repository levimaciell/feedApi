package com.grupofive.demo.post.exceptions;

import org.springframework.http.HttpStatus;

public class CommentServiceException extends RuntimeException{

    private HttpStatus code;
    public CommentServiceException(String msg, HttpStatus code){

        super(msg);
        this.code = code;
    }

    public CommentServiceException(String msg) {
        super(msg);
    }

    public HttpStatus getCode() {
        return code;
    }
    
}
