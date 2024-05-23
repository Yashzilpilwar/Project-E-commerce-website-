package com.example.product_service_1;

import com.example.product_service_1.dtos.ProductResponseDto;
import com.example.product_service_1.models.Product;
import com.example.product_service_1.repositories.CategoryRepository;
import com.example.product_service_1.repositories.ProductRepository;
import com.example.product_service_1.repositories.projections.ProductProjections;
import com.example.product_service_1.repositories.projections.ProductWithIdAndTitle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProductService1ApplicationTests {

    // 3 ways to DI
    // constructor - use this
    // setter method
    // auto wired

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void testJpaDeclaredJoin() {
        List<Product> products = productRepository.findAllByCategory_Title("jaybb");
        for (Product product : products) {
            System.out.println(product.getTitle());

        }
    }

    @Test
    void testHQL() {
        List<Product> products = productRepository.getProductByCategoryName("jaybb");
        for (Product product : products) {
            System.out.println(product.getTitle());
            System.out.println(product.getCategory().getTitle());
            System.out.println(product.getPrice());
        }
    }

    @Test
    void testSpecificFields() {
        List<String> productTitles = productRepository.someTitleMethod("jaybb");
        for (String productTitle : productTitles) {
            System.out.println(productTitle);
        }
    }

    @Test
    void testProjections() {
        List<ProductWithIdAndTitle> products = productRepository.someMethod1("jaybb");
        for (ProductWithIdAndTitle product : products) {
            System.out.println(product.getTitle());
            System.out.println(product.getId());
        }


        List<ProductProjections> productProjections = productRepository.someMethod2("jaybb");
        for (ProductProjections p : productProjections) {
            System.out.println(p.getTitle());
            System.out.println(p.getId());

        }
    }

    @Test
    void testNativeSql(){
        Product product = productRepository.someNativeSql(1L);
        System.out.println(product.getTitle());

    }
}
