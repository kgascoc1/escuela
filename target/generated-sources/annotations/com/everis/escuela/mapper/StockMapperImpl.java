package com.everis.escuela.mapper;

import com.everis.escuela.dto.SaveStockRequestDto;
import com.everis.escuela.dto.SaveStockResponseDto;
import com.everis.escuela.entity.Stock;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-09-19T04:23:22-0500",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_261 (Oracle Corporation)"
)
public class StockMapperImpl implements StockMapper {

    @Override
    public List<Stock> toEntity(List<SaveStockRequestDto> saveStockRequestDtos) {
        if ( saveStockRequestDtos == null ) {
            return null;
        }

        List<Stock> list = new ArrayList<Stock>( saveStockRequestDtos.size() );
        for ( SaveStockRequestDto saveStockRequestDto : saveStockRequestDtos ) {
            list.add( saveStockRequestDtoToStock( saveStockRequestDto ) );
        }

        return list;
    }

    @Override
    public List<SaveStockResponseDto> toSaveStockResponseDto(List<Stock> stocks) {
        if ( stocks == null ) {
            return null;
        }

        List<SaveStockResponseDto> list = new ArrayList<SaveStockResponseDto>( stocks.size() );
        for ( Stock stock : stocks ) {
            list.add( stockToSaveStockResponseDto( stock ) );
        }

        return list;
    }

    protected Stock saveStockRequestDtoToStock(SaveStockRequestDto saveStockRequestDto) {
        if ( saveStockRequestDto == null ) {
            return null;
        }

        Stock stock = new Stock();

        stock.setProductId( saveStockRequestDto.getProductId() );
        stock.setWareHouseId( saveStockRequestDto.getWareHouseId() );
        stock.setQuantity( saveStockRequestDto.getQuantity() );

        return stock;
    }

    protected SaveStockResponseDto stockToSaveStockResponseDto(Stock stock) {
        if ( stock == null ) {
            return null;
        }

        SaveStockResponseDto saveStockResponseDto = new SaveStockResponseDto();

        saveStockResponseDto.setId( stock.getId() );
        saveStockResponseDto.setProductId( stock.getProductId() );
        saveStockResponseDto.setWareHouseId( stock.getWareHouseId() );
        saveStockResponseDto.setQuantity( stock.getQuantity() );

        return saveStockResponseDto;
    }
}
