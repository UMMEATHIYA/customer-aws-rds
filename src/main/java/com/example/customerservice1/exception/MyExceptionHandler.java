package com.example.customerservice1.exception;

import com.example.customerservice1.shared.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleCustomerNotFoundException(CustomerNotFoundException e)
    {
        ErrorResponse response=new ErrorResponse();
        response.setErrorMessage(e.getMessage());
        response.setErrorReportTime(System.currentTimeMillis());
        response.setStatusCode(HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleAnyException(Exception e)
    {
        ErrorResponse response=new ErrorResponse();
        response.setErrorMessage("something wrong");
        response.setErrorReportTime(System.currentTimeMillis());
        response.setStatusCode(HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
