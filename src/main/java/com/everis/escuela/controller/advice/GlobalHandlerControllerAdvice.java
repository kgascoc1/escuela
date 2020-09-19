package com.everis.escuela.controller.advice;

import com.everis.escuela.dto.ErrorDetailDto;
import com.everis.escuela.exception.BusinessException;
import com.everis.escuela.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalHandlerControllerAdvice {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException exception){
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorDetailDto> businessException(BusinessException exception){
        return new ResponseEntity<>(new ErrorDetailDto(exception.getMessage(),exception.getDate()),HttpStatus.CREATED);
    }
}
