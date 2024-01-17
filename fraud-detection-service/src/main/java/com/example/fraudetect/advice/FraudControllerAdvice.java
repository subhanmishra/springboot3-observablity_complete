package com.example.fraudetect.advice;

import com.example.fraudetect.exception.CustomerExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class FraudControllerAdvice {
    @ExceptionHandler(CustomerExistsException.class)
    public ProblemDetail handleExceptions(CustomerExistsException ex) {
        return ProblemDetail
                .forStatusAndDetail(HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value()), ex.getMessage());
    }
}
