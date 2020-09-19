package com.everis.service;

import com.everis.escuela.dto.FindByProductIdDto;
import com.everis.escuela.entity.Stock;
import com.everis.escuela.exception.BusinessException;
import com.everis.escuela.exception.ResourceNotFoundException;
import com.everis.escuela.repository.StockRepository;
import com.everis.escuela.service.impl.StockServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestStockServiceImplShould {

    @InjectMocks
    private StockServiceImpl serviceImpl;

    @Mock
    private StockRepository repository;

    @Test
    public void testFindByProductId() throws ResourceNotFoundException {
        // Definimos las respuestas del mock
        Stock stock = new Stock();
        stock.setId(1L);
        stock.setProductId(1L);
        stock.setQuantity(1L);
        stock.setWareHouseId(1L);
        when(repository.findByProductId(1L)).thenReturn(1L);

        // Llamar al metodo que vamos a probar
        FindByProductIdDto result = serviceImpl.findByProductId(1L);

        // Comprobar los resultados
        assertNotNull(result);
        System.out.println(result);
    }

    @Test
    public void testSave() throws BusinessException {

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

        when(repository.saveAll(savedList)).thenReturn(savedList);

        List<Stock> result = serviceImpl.save(savedList);

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

}
