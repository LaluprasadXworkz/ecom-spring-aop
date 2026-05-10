package com.mycompany.ecom.Dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Data
public class ProductDto {
    private int id;
    private String name;
    private String description;
    private Double price;
    private LocalDate manufactureDate;
}
