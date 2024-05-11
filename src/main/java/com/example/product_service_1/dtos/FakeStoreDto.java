package com.example.product_service_1.dtos;


import lombok.Getter;
import lombok.Setter;



@Getter
@Setter

public class FakeStoreDto {

    private int id;
    private String title;
    private String Description;
    private double price;
    private String image;
    private String category;


    public ProductResponseDto productResponseDto(){
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(id);
        productResponseDto.setTitle(title);
        productResponseDto.setDescription(Description);
        productResponseDto.setPrice(price);
        productResponseDto.setImage(image);
        productResponseDto.setCategory(category);

        return productResponseDto;

    }


}
