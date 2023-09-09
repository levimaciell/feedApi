package com.grupofive.demo.post.exceptions;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ApiError {

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime timestamp;
    private Integer code;
    private String status;
    private List<String> errors;

    public ApiError() {
    }

    public ApiError(LocalDateTime timestamp, Integer code, String status, List<String> errors) {
        this.timestamp = timestamp;
        this.code = code;
        this.status = status;
        this.errors = errors;
    }

    public ApiError(HttpStatus status, List<String> list){
        timestamp = LocalDateTime.now();
        code = status.value();
        this.status = status.name();
        errors = list;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

}
