package com.marcelo.testes.cadastro.config;

import com.marcelo.testes.cadastro.resource.BaseError;
import com.marcelo.testes.cadastro.resource.BaseException;
import com.marcelo.testes.cadastro.resource.ErrorObject;
import com.marcelo.testes.cadastro.resource.ErrorResponse;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;

@RestControllerAdvice
public class ApplicationControllerAdvice {


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<BaseError> handle(Exception ex) {
        ex.printStackTrace();
        String message = BaseException.DEFAULT_MESSAGE;
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if(ex.getCause() instanceof BaseException) {
            message = ( (BaseException) ex.getCause()).getMessage();
        }
        BaseError error = new BaseError(message, 500);
        return new ResponseEntity<>(error, status);
    }

    @ExceptionHandler(BaseException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<BaseError> handleNegocioException(BaseException ex) {
        String message = ex.getMessage();
        String error = ex.getError();
        Integer status = ex.getStatus();
        Exception e = (Exception) ex.getCause();
        if(e instanceof BaseException) {
            message = ( (BaseException) e).getMessage();
            error = ( (BaseException) e).getError();
            status = ( (BaseException) e).getStatus();
        }
        BaseError baseError = new BaseError(error, message, status);
        return new ResponseEntity<BaseError>(baseError, HttpStatus.valueOf(status));
    }

//    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
//    public ResponseEntity<?> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex) {
//        ex.printStackTrace();
//        HttpStatus status = HttpStatus.UNSUPPORTED_MEDIA_TYPE;
//        BaseError error = new BaseError("API.type_media_not_supported", status.value());
//        return new ResponseEntity<>(error, status);
//    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity<?> handleInternalAuthenticationService(InternalAuthenticationServiceException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = "API.bad_credentials";
        Exception e = (Exception) ex.getCause();
        if(e instanceof BaseException) {
//			e.printStackTrace();
            message = ( (BaseException) e).getMessage();
            status = HttpStatus.valueOf(( (BaseException) e).getStatus());
        }
        BaseError error = new BaseError(message, status.value());
        return new ResponseEntity<>(error, status);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        List<ErrorObject> errors = getErrors(ex);
        ErrorResponse errorResponse = getErrorResponse(ex, status, errors);
        return new ResponseEntity<>(errorResponse, status);
    }

    private ErrorResponse getErrorResponse(MethodArgumentNotValidException ex, HttpStatus status, List<ErrorObject> errors) {
        return new ErrorResponse("API.fields_invalid", status.value(),
                status.getReasonPhrase(), ex.getBindingResult().getObjectName(), errors);
    }

    private List<ErrorObject> getErrors(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors().stream()
                .map(error -> new ErrorObject(error.getDefaultMessage(), error.getField(), error.getRejectedValue()))
                .collect(Collectors.toList());
    }

}
