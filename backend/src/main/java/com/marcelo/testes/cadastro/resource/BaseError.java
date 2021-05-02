package com.marcelo.testes.cadastro.resource;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class BaseError {

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime timestamp = LocalDateTime.now();
    private int status;
    private String error;
    private String message;

    public BaseError(String error, int status) {
        this.error = error;
        this.message = error;
        this.status = status;
    }

    public BaseError(String error, String message, int status) {
        this.error = error;
        this.message = message;
        this.status = status;
    }

    public BaseError(String error) {
        this.error = error;
        this.message = error;
    }

}