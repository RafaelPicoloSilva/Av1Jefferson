package com.av1.vendas.resources.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.av1.vendas.services.exceptions.DataIntegrityViolationException;
import com.av1.vendas.services.exceptions.ObjectNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException ex, HttpServletRequest request){

        StandardError error = new StandardError(System.currentTimeMillis(),HttpStatus.NOT_FOUND.value(), "Object not found",
        ex.getMessage(),request.getRequestURI());
    
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<StandardError> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex,
    HttpServletRequest request){

        StandardError error = new StandardError(System.currentTimeMillis(),HttpStatus.BAD_REQUEST.value(), "Bad Request",
    ex.getMessage(),request.getRequestURI());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException ex, HttpServletRequest request){

        StandardError error = new StandardError(System.currentTimeMillis(),HttpStatus.BAD_REQUEST.value(), "Data Integrity Violation",
        ex.getMessage(),request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
