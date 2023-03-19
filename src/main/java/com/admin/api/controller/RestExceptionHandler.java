package com.admin.api.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { Exception.class })
    protected ResponseEntity<Object> serviceFailure(Exception ex, WebRequest req) {
        String reason = ex.getLocalizedMessage();
        return handleExceptionInternal(ex, reason, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, req);
    }
}
