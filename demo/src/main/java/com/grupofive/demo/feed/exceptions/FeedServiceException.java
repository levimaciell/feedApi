package com.grupofive.demo.feed.exceptions;

import org.springframework.http.HttpStatus;

public class FeedServiceException extends RuntimeException{
    
    private HttpStatus code;

    public FeedServiceException(String msg, HttpStatus code){
        super(msg);
        this.code = code;
    }

    public FeedServiceException(String msg){
        super(msg);
    }

    public HttpStatus getCode() {
        return code;
    }

}
