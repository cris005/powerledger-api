package com.powerledger.api.controller;

import com.powerledger.api.exception.ErrorMessage;
import com.powerledger.api.exception.ResourceNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorMessage> resourceValidationFailed(ConstraintViolationException e) {
        ErrorMessage message = new ErrorMessage(
            new Date(),
            "Validation failed, please review your inputs.",
            e.getMessage()
        );

        return new ResponseEntity<ErrorMessage>(message, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException e) {
        ErrorMessage message = new ErrorMessage(
                new Date(),
                "Unable to find the requested resource",
                e.getMessage()
        );

        return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_FOUND);
    }
}