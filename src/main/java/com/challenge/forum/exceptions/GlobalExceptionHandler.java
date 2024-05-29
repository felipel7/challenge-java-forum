package com.challenge.forum.exceptions;

import com.challenge.forum.exceptions.businessExceptions.UserAlreadyExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorField> handleNotFoundException(EntityNotFoundException e) {
        var error = new ErrorField(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorField> handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        var error = new ErrorField(e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    public record ErrorField(String message) {
    }
}

