package com.jakeporter.simplestspringmvcbootapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
/*
All of the above imports are Spring MVC types that come along with
the spring-boot-starter-web dependency
*/

/**
 *
 * @author jake
 */

/* This annotation notifies Spring MVC that this class should be registered
with the Spring application context and may contain methods that handle REST requests */
@RestController
/* This annotation determines if a given URL, HTTP method, HTTP header, or media type
triggers a specific controller method. Applying this annotation at the class level tells 
Spring MVC that this class can only handle URLs that begin with "/api"*/
@RequestMapping("/api")
public class SimplestController {

    /* this annotation says that helloWorld() will return a String[] to the 
        Spring MVC framework, which then serializes the results to JSON and
        includes it in the HTTP response body. Also signals the method can only
        handle HTTP requests using the GET method */
    @GetMapping
    public String[] helloWorld(){
        String[] result = {"Hello", "World", "!"};
        return result;
    }
    
    @PostMapping("/calculate") // /calculate will be appended to /api
    public String calculate(int operand1, String operator, int operand2){
        int result = 0;
        switch(operator){
            case"+":
                result = operand1 + operand2;
                break;
            case"-":
                result = operand1 - operand2;
                break;
            case"*":
                result = operand1 * operand2;
                break;
            case"/":
                result = operand1 / operand2;
                break;
            default:
                String message = String.format("operator '%s' is invalid", operator);
                throw new IllegalArgumentException(message);
        }
        return String.format("%s %s %s = %s", operand1, operator, operand2, result);
    }
    
    /* {} are delimiters that surround a variable chunk. Its value can be almost anything 
    */
    @DeleteMapping("/resource/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // this ovverrides the default reponsestatus and returns a "204 No Content" status for every request
    public void delete(@PathVariable int id){ // @PathVariable tells SpringMVC to find the parameter in the URL (the variable id)
        // this is where we would use the id passed in to delete a resource
    }
    // will return no HTTP reponse body since the return value is void
}
