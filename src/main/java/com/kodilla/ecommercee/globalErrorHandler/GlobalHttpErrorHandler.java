package com.kodilla.ecommercee.globalErrorHandler;

import com.kodilla.ecommercee.exception.OrderNotFoundException;
<<<<<<< HEAD
import com.kodilla.ecommercee.exception.UserNotFoundException;
=======
>>>>>>> f2d3c4ec2066c82f96428bd68ecd291e2b8b1760
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
<<<<<<< HEAD
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
=======
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<Object> handleOrderNotFoundException(OrderNotFoundException exception) {
        return new ResponseEntity<>("Order doesn't exist", HttpStatus.BAD_REQUEST);
    }
}
>>>>>>> f2d3c4ec2066c82f96428bd68ecd291e2b8b1760
