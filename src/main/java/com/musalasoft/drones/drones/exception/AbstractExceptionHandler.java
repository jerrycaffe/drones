package com.musalasoft.drones.drones.exception;

import com.musalasoft.drones.drones.dto.Error;
import com.musalasoft.drones.drones.dto.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AbstractExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Response> constraintViolation(BadRequestException ex) {
        return new ResponseEntity<>(new Response(ex.getError()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<Response> constraintViolation(DuplicateException ex) {
        return new ResponseEntity<>(new Response(ex.getError()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Response> constraintViolation(NotFoundException ex) {

        return new ResponseEntity<>(new Response(ex.getError()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public  ResponseEntity<Response> constraintViolation(HttpMessageNotReadableException ex){
        return new ResponseEntity<>(new Response(new Error("405", "Incorrect input supplied to the system")), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public  ResponseEntity<Response> constraintViolation(MethodArgumentNotValidException ex){
        return new ResponseEntity<>(new Response(new Error("400", ex.getBindingResult().getFieldError().getDefaultMessage())), HttpStatus.BAD_REQUEST);
    }
}
