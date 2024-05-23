package com.example.product_service_1.models;


import com.example.product_service_1.repositories.CategoryRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel  {
    private String title;
    @OneToMany(mappedBy = "category")
    List<Product> products;
}
