package com.everis.escuela.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
public class ErrorDetailDto {

    private String message;
    private Instant date;
}
