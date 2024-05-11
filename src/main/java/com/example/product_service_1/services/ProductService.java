package com.example.product_service_1.services;

import com.example.product_service_1.dtos.FakeStoreDto;
import com.example.product_service_1.dtos.ProductResponseDto;
import com.example.product_service_1.models.Product;

public interface ProductService {

    public ProductResponseDto getSingleproduct(int productId);

    public ProductResponseDto addProduct(

            String title,
            String description,
            String imageUrl,
            String category,
            double price
    );

}
