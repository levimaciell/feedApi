package com.grupofive.demo.post.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class Handler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> genericError(Exception exception) {
        ApiError response = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, List.of("There was a internal server error"));

        return new ResponseEntity<ApiError>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(PostServiceException.class)
    public ResponseEntity<ApiError> postServiceException(PostServiceException exception) {
        ApiError response = new ApiError(exception.getCode(), List.of("It was not possible to insert a post.", "Reason: " + exception.getMessage()));

        return new ResponseEntity<ApiError>(response, exception.getCode());
    }

}