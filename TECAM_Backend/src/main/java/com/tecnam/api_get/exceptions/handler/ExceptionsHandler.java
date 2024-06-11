package com.tecnam.api_get.exceptions.handler;

import com.tecnam.api_get.entity.Error;
import com.tecnam.api_get.exceptions.CoursesNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(CoursesNotFoundException.class)
    ResponseEntity<Error> handlerCoursesNotFoundException(CoursesNotFoundException ex) {
        Error errorDetail = new Error(
                "Elementos no encontrados",
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage()
        );
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        return new ResponseEntity<>(errorDetail,headers,HttpStatus.NOT_FOUND);
    }
}