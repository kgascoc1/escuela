package com.everis.escuela.service;

import com.everis.escuela.dto.FindByProductIdDto;
import com.everis.escuela.entity.Stock;
import com.everis.escuela.exception.BusinessException;
import com.everis.escuela.exception.ResourceNotFoundException;

import java.util.List;

public interface StockService {

    public FindByProductIdDto findByProductId(Long productId) throws ResourceNotFoundException;

    public List<Stock> save(List<Stock> stocks) throws BusinessException;
}
