package com.example.product_service_1.controllers;

import com.example.product_service_1.dtos.ProductRequestDto;
import com.example.product_service_1.dtos.ProductResponseDto;
import com.example.product_service_1.models.Product;
import com.example.product_service_1.services.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController

public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping( "/products/{id}")
    public ProductResponseDto getProductDetails(@PathVariable("id") int productId ){
          return productService.getSingleproduct(productId);

    }


    @PostMapping("/Products")
    public ProductResponseDto createNewProduct(@RequestBody ProductRequestDto productRequestDto){

        return productService.addProduct(
                productRequestDto.getTitle(),
                productRequestDto.getDescription(),
                productRequestDto.getImage(),
                productRequestDto.getCategory(),
                productRequestDto.getPrice()
        );
    }

}
