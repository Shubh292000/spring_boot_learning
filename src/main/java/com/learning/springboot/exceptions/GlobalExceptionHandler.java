package com.learning.springboot.exceptions;

import com.learning.springboot.dto.ErrorResponseObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseObject> handleNotFoundError(ResourceNotFoundException ex)
    {
        ErrorResponseObject er = new ErrorResponseObject(HttpStatus.NOT_FOUND.value(),ex.getMessage());
        return new ResponseEntity<>(er,HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseObject> handleBadRequest(IllegalArgumentException ex) {
        ErrorResponseObject error = new ErrorResponseObject(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseObject> handleValidation(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        ErrorResponseObject error = new ErrorResponseObject(HttpStatus.BAD_REQUEST.value(), "Invalid Data: " + errorMessage);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
