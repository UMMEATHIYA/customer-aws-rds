package com.example.customerservice1.exception;

public class CustomerNotFoundException extends RuntimeException{

    private String message;

    public CustomerNotFoundException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
