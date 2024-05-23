package com.example.product_service_1.repositories.projections;

import com.example.product_service_1.models.Category;

import java.math.BigDecimal;

public interface ProductProjections {
    Long getId();
    String getTitle();
    String getDescription();
    BigDecimal getPrice();
    Category getCategory();
    String getImageUrl();

}
