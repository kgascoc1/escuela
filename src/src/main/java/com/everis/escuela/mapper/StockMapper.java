package com.everis.escuela.mapper;

import com.everis.escuela.dto.SaveStockRequestDto;
import com.everis.escuela.dto.SaveStockResponseDto;
import com.everis.escuela.entity.Stock;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface StockMapper {

    StockMapper INSTANCE = Mappers.getMapper(StockMapper.class);

    public SaveStockResponseDto map (Stock stock);

    public Stock map(SaveStockRequestDto saveStockRequestDto);

    public List<Stock> toEntity(List<SaveStockRequestDto> saveStockRequestDtos);

    public List<SaveStockResponseDto> toSaveStockResponseDto(List<Stock> stocks);
}
