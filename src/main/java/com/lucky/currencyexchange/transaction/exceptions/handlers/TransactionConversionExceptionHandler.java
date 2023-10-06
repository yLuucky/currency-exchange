package com.lucky.currencyexchange.transaction.exceptions.handlers;

import com.lucky.currencyexchange.transaction.exceptions.TransactionConversionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TransactionConversionExceptionHandler extends RuntimeException{

    @ExceptionHandler(value = TransactionConversionException.class)
    public ResponseEntity<Object> exception(TransactionConversionException exception) {
        return new ResponseEntity<>("Could not process your request", HttpStatus.BAD_REQUEST);
    }
}
