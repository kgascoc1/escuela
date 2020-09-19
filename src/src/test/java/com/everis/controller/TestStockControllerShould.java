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
		//DEFINIMOS LAS RESPUESTAS DEL MOCK
		FindByProductIdDto findByProductIdDto = new FindByProductIdDto();
		findByProductIdDto.setProductId(2L);
		findByProductIdDto.setTotal(10L);

		Stock stock = new Stock();
		stock.setId(1L);
		stock.setProductId(1L);
		stock.setQuantity(1L);
		stock.setWareHouseId(1L);
		when(service.findByProductId(1L)).thenReturn(findByProductIdDto);

		//LLAMAR AL METODO QUE VAMOS A PROBAR
		FindByProductIdDto result = controller.findByProductId(1L);

		//COMPROBAR LOS RESULTADOS
		assertNotNull(result);
		assertEquals(2L,result.getProductId());
		assertEquals(10L,result.getTotal());

	}

	@Test
	public void testSave() throws BusinessException {

		//DEFINIMOS LAS RESPUESTAS DEL MOCK
		SaveStockRequestDto request1 = new SaveStockRequestDto();
		request1.setProductId(1L);
		request1.setWareHouseId(1L);
		request1.setQuantity(1L);

		SaveStockRequestDto request2 = new SaveStockRequestDto();
		request2.setProductId(2L);
		request2.setWareHouseId(2L);
		request2.setQuantity(2L);

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

		when(service.save(any(ArrayList.class))).thenReturn(savedList);

		//LLAMAR AL METODO QUE VAMOS A PROBAR
		List<SaveStockResponseDto> result =  controller.save(requestList);

		//COMPROBAR LOS RESULTADOS
		assertNotNull(result);
		assertEquals(2, result.size());

		//STOCK PRODUCTO 01
		assertEquals(1L, result.get(0).getId());
		assertEquals(1L, result.get(0).getProductId());
		assertEquals(1L, result.get(0).getQuantity());
		assertEquals(1L, result.get(0).getWareHouseId());

		//STOCK PRODUCTO 02
		assertEquals(2L, result.get(1).getId());
		assertEquals(2L, result.get(1).getProductId());
		assertEquals(2L, result.get(1).getQuantity());
		assertEquals(2L, result.get(1).getWareHouseId());
	}

	@Test(expected = ResourceNotFoundException.class)
	public void testFindByProductIdException() throws ResourceNotFoundException {
		//DEFINIMOS LAS RESPUESTAS DEL MOCK
		when(service.findByProductId(88L)).thenThrow(new ResourceNotFoundException());

		controller.findByProductId(88L);
	}

	@Test(expected = BusinessException.class)
	public void testSaveWhenException() throws BusinessException {

		//DEFINIMOS LAS RESPUESTAS DEL MOCK
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

		//LLAMAR AL METODO QUE VAMOS A PROBAR
		controller.save(saveStockRequestDtoList);
	}
}
