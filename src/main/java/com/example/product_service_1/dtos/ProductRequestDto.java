package com.example.product_service_1.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.service.annotation.GetExchange;

@Getter
@Setter
public class ProductRequestDto {
    //private int id;
    private String title;
    private String description;
    private double price;
    private String imageUrl;
    private String category;
}