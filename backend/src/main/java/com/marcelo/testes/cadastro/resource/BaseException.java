package com.marcelo.testes.cadastro.resource;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private Integer status;
    private String error;


    public static final String DEFAULT_MESSAGE = "API.internal_error";
    public static final Integer DEFAULT_CODE = 500;

    public BaseException() {
        super(DEFAULT_MESSAGE);
        this.error = DEFAULT_MESSAGE;
        this.status = DEFAULT_CODE;
    }

    public BaseException(Exception ex) {
        super(DEFAULT_MESSAGE, ex);
        this.error = DEFAULT_MESSAGE;
        this.status = DEFAULT_CODE;
        ex.printStackTrace();
    }

    public BaseException(BaseException e) {
        super(e);
        this.status = e.getStatus();
        this.error = e.getError();
    }

    public BaseException(String message, Exception e) {
        super(message, e);
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Integer status) {
        super(message);
        this.status = status;
        this.error = message;
    }

    public BaseException(String error, String message, Integer status) {
        super(message);
        this.status = status;
        this.error = error;
    }

}