package com.grupofive.demo.post.exceptions;

import org.springframework.http.HttpStatus;

public class PostServiceException extends RuntimeException{

    private HttpStatus code;
    public PostServiceException(String msg, HttpStatus code){

        super(msg);
        this.code = code;
    }

    public PostServiceException(String msg) {
        super(msg);
    }

    public HttpStatus getCode() {
        return code;
    }
}
