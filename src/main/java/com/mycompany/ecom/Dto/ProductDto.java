package com.mycompany.ecom.Dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProductDto {

    private Long id;

    private String name;

    private String description;

    private Double price;

    private LocalDate manufactureDate;
}