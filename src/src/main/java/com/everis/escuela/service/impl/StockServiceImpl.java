package com.everis.escuela.service.impl;

import com.everis.escuela.dto.FindByProductIdDto;
import com.everis.escuela.entity.Stock;
import com.everis.escuela.exception.BusinessException;
import com.everis.escuela.exception.ResourceNotFoundException;
import com.everis.escuela.repository.StockRepository;
import com.everis.escuela.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockRepository stockRepository;

    @Override
    public FindByProductIdDto findByProductId(Long productId) throws ResourceNotFoundException {
        Long totalStock = stockRepository.findByProductId(productId);
        if(totalStock == null){
            throw new ResourceNotFoundException();
        }

        FindByProductIdDto findByProductIdDto = new FindByProductIdDto();
        findByProductIdDto.setProductId(productId);
        findByProductIdDto.setTotal(totalStock);

        return findByProductIdDto;
    }

    @Override
    public List<Stock> save(List<Stock> stocks) throws BusinessException {

        for (Stock stock : stocks) {
            if(stock.getQuantity() == 0L){
                throw new BusinessException("Quantity of product cannot be 0");
            }
        }
        return stockRepository.saveAll(stocks);
    }


}
