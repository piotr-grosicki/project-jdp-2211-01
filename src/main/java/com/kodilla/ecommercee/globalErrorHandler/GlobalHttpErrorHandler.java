package com.kodilla.ecommercee.globalErrorHandler;

import com.kodilla.ecommercee.exception.GroupNotFoundException;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Object> handleProductNotFoundException(ProductNotFoundException e) {
        return new ResponseEntity<>("Product not found", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException exception) {
        return new ResponseEntity<>("User doesn't exist", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(GroupNotFoundException.class)
    public ResponseEntity<Object> handleTaskNotFoundException(GroupNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}