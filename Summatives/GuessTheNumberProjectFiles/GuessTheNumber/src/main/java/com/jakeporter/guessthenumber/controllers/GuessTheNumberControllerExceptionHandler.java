package com.jakeporter.guessthenumber.controllers;

import com.jakeporter.guessthenumber.service.GameInProgressException;
import java.sql.SQLException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
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
    
    @ExceptionHandler(GameInProgressException.class)
    public final ResponseEntity<Error> handleGameInProgressException(GameInProgressException e, WebRequest request){
        Error error = new Error();
        error.setMessage(e.getMessage());
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public final ResponseEntity<Error> handleEmptyResultDataAccessException(EmptyResultDataAccessException e, WebRequest request){
        Error error = new Error();
        error.setMessage("You're trying to access data that doesn't exist.");
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public final ResponseEntity<Error> handleHttpMessageNotReadableException(HttpMessageNotReadableException e, WebRequest request){
        Error error = new Error();
        error.setMessage("Your input is invalid.");
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(StringIndexOutOfBoundsException.class)
    public final ResponseEntity<Error> handleStringIndexOutOfBoundsException(StringIndexOutOfBoundsException e, WebRequest request){
        Error error = new Error();
        error.setMessage("Guess input is invalid. Please ensure it is four consecutive numbers and try again.");
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
}
