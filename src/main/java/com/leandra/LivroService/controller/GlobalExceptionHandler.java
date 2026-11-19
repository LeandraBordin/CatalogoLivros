package com.leandra.LivroService.controller;

import com.leandra.LivroService.infrastructure.exceptions.ConflictException;
import com.leandra.LivroService.infrastructure.exceptions.ResourceNotFoundException;
import com.leandra.LivroService.infrastructure.exceptions.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handlerResourceNotFoundException(ResourceNotFoundException resourceNotFoundException){
        return new ResponseEntity<>(resourceNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<String> handlerConflictException(ConflictException conflictException){
        return new ResponseEntity<>(conflictException.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<String> UnauthorizedException(UnauthorizedException unauthorizedException){
        return new ResponseEntity<>(unauthorizedException.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
