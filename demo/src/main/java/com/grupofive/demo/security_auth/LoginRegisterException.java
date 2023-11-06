package com.grupofive.demo.security_auth;

import org.springframework.http.HttpStatus;

public class LoginRegisterException extends RuntimeException{
    private HttpStatus code;

    public LoginRegisterException(String msg, HttpStatus code){
        super(msg);
        this.code = code;
    }

    public LoginRegisterException(String message) {
        super(message);
    }

    public HttpStatus getCode() {
        return code;
    }

}
