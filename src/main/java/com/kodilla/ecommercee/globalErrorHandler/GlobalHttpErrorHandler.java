package com.kodilla.ecommercee.globalErrorHandler;

import com.kodilla.ecommercee.exception.OrderNotFoundException;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ResponseStatus(HttpStatus.NOT_FOUND)
@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException exception) {
        return new ResponseEntity<>("User doesn't exist", HttpStatus.BAD_REQUEST);
    }
}