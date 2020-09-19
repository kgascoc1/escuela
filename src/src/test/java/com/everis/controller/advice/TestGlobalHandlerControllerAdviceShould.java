package com.everis.controller.advice;

import com.everis.escuela.controller.advice.GlobalHandlerControllerAdvice;
import com.everis.escuela.dto.ErrorDetailDto;
import com.everis.escuela.exception.BusinessException;
import com.everis.escuela.exception.ResourceNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class TestGlobalHandlerControllerAdviceShould {

    @InjectMocks
    private GlobalHandlerControllerAdvice handler;

    @Test
    public void testResourceNotFoundException(){
        assertNotNull(handler.resourceNotFoundException(new ResourceNotFoundException()));
    }

    @Test
    public void testBusinessException(){
        BusinessException exception = new BusinessException("error");
        ResponseEntity<ErrorDetailDto> result = handler.businessException(exception);
        assertNotNull(result);
        assertEquals(exception.getMessage(),result.getBody().getMessage());
        assertEquals(exception.getDate(),result.getBody().getDate());
    }
}
