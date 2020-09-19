package com.everis.escuela.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "stock")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "ware_house_id")
    private Long wareHouseId;
    @Column
    private Long quantity;
}
