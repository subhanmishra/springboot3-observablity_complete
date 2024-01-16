package com.example.loans.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class LoanControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleExceptions(Exception ex) {
        return switch (ex) {

            case HttpClientErrorException hce -> ProblemDetail
                    .forStatusAndDetail(HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value()), hce.getMessage());

            case RuntimeException re when re.getMessage().contains("Forbidden") -> ProblemDetail
                    .forStatusAndDetail(HttpStatusCode.valueOf(HttpStatus.FORBIDDEN.value()), re.getMessage());


            case RuntimeException re -> ProblemDetail
                    .forStatusAndDetail(HttpStatusCode.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), re.getMessage());

            default ->
                ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(HttpStatus.SERVICE_UNAVAILABLE.value()), ex.getMessage());

        };
    }


}
