package com.example.product_service_1;

import com.example.product_service_1.dtos.ProductResponseDto;
import com.example.product_service_1.models.Category;
import com.example.product_service_1.models.Product;
import com.example.product_service_1.repositories.CategoryRepository;
import com.example.product_service_1.repositories.ProductRepository;
import com.example.product_service_1.repositories.projections.ProductProjections;
import com.example.product_service_1.repositories.projections.ProductWithIdAndTitle;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
        List<Product> products = productRepository.findAllByCategory_Title("new electronics");
        for (Product product : products) {
            System.out.println(product.getTitle());
        }
    }

    @Test
    void testHQL() {
        List<Product> products = productRepository.getProductWithCategoryName("new electronics");
        for (Product product : products) {
            System.out.println(product.getTitle());
            System.out.println(product.getCategory().getTitle());
            System.out.println(product.getPrice());
        }
    }

    @Test
    void testSpecificFields() {
        List<String> productTitles = productRepository.someTitleMethod("new electronics");
        for (String productTitle : productTitles) {
            System.out.println(productTitle);
        }
    }

    @Test
    void testProjections() {
        List<ProductWithIdAndTitle> products = productRepository.someMethod1("new electronics");
        for (ProductWithIdAndTitle product : products) {
            System.out.println(product.getTitle());
            System.out.println(product.getId());
        }

        List<ProductProjections> productProjections = productRepository.someMethod2("new electronics");
        for(ProductProjections p : productProjections) {
            System.out.println(p.getTitle());
            System.out.println(p.getId());
        }
    }

    @Test
    void testNativeSql() {
        //Product product = productRepository.someNativeSql(1L);
//        ProductProjection product = productRepository.someNativeSql(1L);
//        System.out.println(product.getTitle());

        ProductProjections product2 = productRepository.someNativeSql2(5L);
        System.out.println(product2.getTitle());
        System.out.println(product2.getId());
    }

    @Test
    @Transactional
    void testFetchType() {
        Optional<Category> category = categoryRepository.findById(3L);
        if (category.isPresent()) {
            System.out.println(category.get().getTitle());
            List<Product> products = category.get().getProducts();
            for (Product product : products) {
                System.out.println(product.getTitle());
            }
        }
    }

    @Test
    @Transactional
    void testFetchMode() {
        List<Category> categories = categoryRepository.findByTitleEndingWith("electronics");
        for (Category category : categories) {
            System.out.println(category.getTitle());
            List<Product> products = category.getProducts();
            for (Product product : products) {
                System.out.println(product.getTitle());
            }
        }
    }
    // Break till 8:35 AM
    @Test
    void addManyProducts() {
        double basePrice = 2000;
        String productName = "toy";
        String productDescription = "toy number";
        String imageUrl = "www.imgur.com/";
        String categoryName = "toys";

        for (int i = 1; i <= 100; i++) {
            Product product = new Product();
            product.setTitle(productName + "-" + i);
            product.setDescription( productDescription + " # " + i);
            product.setPrice(basePrice + i);
            Category category = new Category();
            category.setTitle(categoryName);
            product.setCategory(category);
            product.setImageUrl(imageUrl + i);
            productRepository.save(product);
        }


    }
}