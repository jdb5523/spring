package com.appsdeveloperblog.app.ws.exceptions;

import java.util.Date;

import com.appsdeveloperblog.app.ws.ui.model.response.ErrorMessage;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Aspect
@ControllerAdvice
public class AppExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorMessage> handleAnyException(Exception ex, WebRequest request) {
        ErrorMessage err = new ErrorMessage(new Date(), ex.getLocalizedMessage());
        return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Before("execution(* com.appsdeveloperblog.app.ws.ui.controller..* (..))")
    public void test() {
        System.out.println("HELLO THERE");
    }
}
