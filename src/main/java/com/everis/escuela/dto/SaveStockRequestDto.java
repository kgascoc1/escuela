package com.everis.escuela.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveStockRequestDto {

	private Long productId;

	private Long wareHouseId;

	private Long quantity;

}
