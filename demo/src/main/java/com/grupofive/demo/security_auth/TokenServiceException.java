package com.grupofive.demo.security_auth;

import org.springframework.http.HttpStatus;

public class TokenServiceException extends RuntimeException{
    private HttpStatus code;

    public TokenServiceException(String msg, HttpStatus code){
        super(msg);
        this.code = code;
    }

    public HttpStatus getCode() {
        return code;
    }

    
}
