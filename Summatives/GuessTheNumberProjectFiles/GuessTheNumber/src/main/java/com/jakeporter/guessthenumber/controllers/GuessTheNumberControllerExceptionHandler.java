package com.jakeporter.guessthenumber.controllers;

import java.sql.SQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

/**
 *
 * @author jake
 */

@ControllerAdvice
@RestController
public class GuessTheNumberControllerExceptionHandler {
    private static final String ERROR_MESSAGE = "Could not process your submission. "
            + "Please ensure that it's valid and try again.";
    
    @ExceptionHandler(SQLException.class)
    // final keyword means the method can't be overridden by subclasses
    public final ResponseEntity<Error> handleSqlException(SQLException e, WebRequest request){
        Error error = new Error();
        error.setMessage(ERROR_MESSAGE);
        
        // return a response that contains an error w/ a message and status code
        return new ResponseEntity(error, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
