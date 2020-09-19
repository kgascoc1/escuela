package com.everis.controller;

import com.everis.escuela.controller.StockController;
import com.everis.escuela.dto.FindByProductIdDto;
import com.everis.escuela.dto.SaveStockRequestDto;
import com.everis.escuela.dto.SaveStockResponseDto;
import com.everis.escuela.entity.Stock;
import com.everis.escuela.exception.BusinessException;
import com.everis.escuela.exception.ResourceNotFoundException;
import com.everis.escuela.service.StockService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestStockControllerShould {

	@InjectMocks
	private StockController controller;

	@Mock
	private StockService service;

	@Test
	public void testFindByProductId() throws ResourceNotFoundException {
		// Definimos las respuestas del mock
		FindByProductIdDto findByProductIdDto = new FindByProductIdDto();
		findByProductIdDto.setProductId(2L);
		findByProductIdDto.setTotal(10L);

		Stock stock = new Stock();
		stock.setId(1L);
		stock.setProductId(1L);
		stock.setQuantity(1L);
		stock.setWareHouseId(1L);
		when(service.findByProductId(1L)).thenReturn(findByProductIdDto);

		// Llamar al metodo que vamos a probar
		FindByProductIdDto result = controller.findByProductId(1L);

		// Comprobar los resultados
		assertNotNull(result);

	}

	@Test
	public void testSave() throws BusinessException {

		SaveStockRequestDto request1 = new SaveStockRequestDto();
		request1.setProductId(1L);
		request1.setQuantity(1L);
		request1.setWareHouseId(1L);

		SaveStockRequestDto request2 = new SaveStockRequestDto();
		request2.setProductId(2L);
		request2.setQuantity(2L);
		request2.setWareHouseId(2L);

		List<SaveStockRequestDto> requestList = new ArrayList<>();
		requestList.add(request1);
		requestList.add(request2);

		Stock saved1 = new Stock();

		saved1.setId(1L);
		saved1.setProductId(1L);
		saved1.setQuantity(1L);
		saved1.setWareHouseId(1L);

		Stock saved2 = new Stock();

		saved2.setId(2L);
		saved2.setProductId(2L);
		saved2.setQuantity(2L);
		saved2.setWareHouseId(2L);

		List<Stock> savedList = new ArrayList<>();
		savedList.add(saved1);
		savedList.add(saved2);

		when(service.save(savedList)).thenReturn(savedList);

		ResponseEntity<List<SaveStockResponseDto>> result =  controller.save(requestList);

		assertNotNull(result);
	}

	@Test(expected = ResourceNotFoundException.class)
	public void testFindByProductIdException() throws ResourceNotFoundException {
		// Definimos las respuestas del mock
		when(service.findByProductId(88L)).thenThrow(new ResourceNotFoundException());

		controller.findByProductId(88L);
	}

	@Test(expected = BusinessException.class)
	public void testSaveWhenException() throws BusinessException {

		SaveStockRequestDto request1 = new SaveStockRequestDto();
		request1.setProductId(1L);
		request1.setQuantity(1L);
		request1.setWareHouseId(1L);

		SaveStockRequestDto request2 = new SaveStockRequestDto();
		request2.setProductId(2L);
		request2.setQuantity(2L);
		request2.setWareHouseId(2L);

		List<SaveStockRequestDto> saveStockRequestDtoList = new ArrayList<>();
		saveStockRequestDtoList.add(request1);
		saveStockRequestDtoList.add(request2);

		Stock saved1 = new Stock();

		saved1.setId(1L);
		saved1.setProductId(1L);
		saved1.setQuantity(1L);
		saved1.setWareHouseId(1L);

		Stock saved2 = new Stock();

		saved2.setId(2L);
		saved2.setProductId(2L);
		saved2.setQuantity(2L);
		saved2.setWareHouseId(2L);

		List<Stock> savedList = new ArrayList<>();
		savedList.add(saved1);
		savedList.add(saved2);

		when(service.save(savedList)).thenThrow(new BusinessException("error"));

		controller.save(saveStockRequestDtoList);
	}
}
