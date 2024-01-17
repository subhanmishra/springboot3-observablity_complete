package com.example.fraudetect.exception;

public class CustomerExistsException extends RuntimeException {

    public CustomerExistsException(String msg) {
        super(msg);
    }
}
