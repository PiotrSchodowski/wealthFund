package com.example.wealthFund.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class Advice {

    @ExceptionHandler(WealthFundException.class)
    public ResponseEntity<String> handleException(WealthFundException wealthFundException) {
        return new ResponseEntity<String>("Exception: " + wealthFundException.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }
}
