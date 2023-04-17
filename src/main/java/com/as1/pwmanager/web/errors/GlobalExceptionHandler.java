package com.as1.pwmanager.web.errors;

import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

class Animal {
    String name;
    Integer age;

    public Animal(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // handle wrong [Accept: media-type] values for a given mapped request
    // TODO: Find out how to return a proper JSON response
    @Override
    public ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Animal dog = new Animal("Dog", 1);
        return new ResponseEntity<>(ex.getBody(), HttpStatus.NOT_ACCEPTABLE);

    }

    @ExceptionHandler(DataRetrievalFailureException.class)
    public ResponseEntity<String> handleDataRetrievalException(DataRetrievalFailureException ex) {
        return new ResponseEntity<String>("Exception retrieving data: " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    /*
    TODO: Check this
 A way to handle 404 exceptions programmatically, but it would be better to just customize the error message...
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handleResponseStatusException(ResponseStatusException ex) {
        return new ResponseEntity<>("Exception 404 not found message: " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }
*/
}
