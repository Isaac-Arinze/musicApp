package com.zikan.MusicApp.project.exception;


import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus (HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public String handleSQLIntegrityViolation(SQLIntegrityConstraintViolationException exception){
        return exception.getMessage();
    }

    @ResponseStatus (HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        Map<String, String> errorMap = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> errorMap.put(error.getField(), error.getDefaultMessage()));

//        BindingResult bindingResult = exception.getBindingResult();
//        List<FieldError> errors = bindingResult.getFieldErrors();
//        for (FieldError error:errors){
//            errorMap.put(error.getField(), error.getDefaultMessage());
//        }
        return errorMap;
    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NoSuchElementException.class)
    public String handleNoSuchElementException(NoSuchElementException exception){
        return exception.getMessage();
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public String handleArgumentTypeMismatch(MethodArgumentTypeMismatchException exception){
        return exception.getMessage();
    }
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<String> handleSqlException (Exception exception){
        String erroMessage = "An error occured while procesing yur request due to duplicate entry ";
        return new ResponseEntity<>(erroMessage, HttpStatus.CONFLICT);

    }

}

