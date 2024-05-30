package com.challenge.forum.exceptions;

import com.challenge.forum.exceptions.businessExceptions.UserAlreadyExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleDataIntegrityViolationException(MethodArgumentNotValidException exception) {
        var errors = exception.getFieldErrors().stream().map(ErrorValidation::new).toList();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    public record ErrorField(String message) {
    }

    private record ErrorValidation(
        String field,
        String message
    ) {
        public ErrorValidation(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}

