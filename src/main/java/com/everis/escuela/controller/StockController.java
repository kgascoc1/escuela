package com.everis.escuela.controller;

import com.everis.escuela.dto.FindByProductIdDto;
import com.everis.escuela.dto.SaveStockRequestDto;
import com.everis.escuela.dto.SaveStockResponseDto;
import com.everis.escuela.exception.BusinessException;
import com.everis.escuela.exception.ResourceNotFoundException;
import com.everis.escuela.mapper.StockMapper;
import com.everis.escuela.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping("stock/{productId}")
    public FindByProductIdDto findByProductId(@PathVariable("productId") Long productId) throws ResourceNotFoundException {
        return stockService.findByProductId(productId);
    }

    @PostMapping("stock")
    public ResponseEntity<List<SaveStockResponseDto>> save (@RequestBody List<SaveStockRequestDto> saveStockRequestDtoList) throws BusinessException {
        return new ResponseEntity<>(StockMapper.INSTANCE
                .toSaveStockResponseDto(stockService
                        .save(StockMapper.INSTANCE.toEntity(saveStockRequestDtoList))), HttpStatus.CREATED);
    }
}
