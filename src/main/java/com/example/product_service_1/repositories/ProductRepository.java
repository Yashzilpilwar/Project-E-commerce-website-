package com.example.product_service_1.repositories;

import com.example.product_service_1.models.Product;
import com.example.product_service_1.repositories.projections.ProductProjections;
import com.example.product_service_1.repositories.projections.ProductWithIdAndTitle;
import com.example.product_service_1.repositories.projections.ProductProjections;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Product save(Product product);

    List<Product> findAll();

    Page<Product> findAll(Pageable pageable);

    Product findByIdIs(Long id);

    List<Product> findAllByCategory_Title(String title);

    List<Product> findByCategory_TitleContaining(String title);

    @Query("select p from Product p where p.category.title = :categoryName")
    List<Product> getProductWithCategoryName(String categoryName);

    @Query("select p.title as title from Product p where p.category.title = :categoryName")
    List<String> someTitleMethod(@Param("categoryName") String categoryName);


    @Query("select p.id as id, p.title as title from Product p where p.category.title = :categoryName")
    List<ProductWithIdAndTitle> someMethod1(String categoryName);


    @Query("select p.id as id, p.title as title from Product p where p.category.title = :categoryName")
    List<ProductProjections> someMethod2(String categoryName);

    // Native SQL query

    @Query(value = "select * from product p where p.id = :id", nativeQuery = true)
    ProductProjections someNativeSql(Long id);

    @Query(value = "select id, title from product where id = :id", nativeQuery = true)
    ProductProjections someNativeSql2(Long id);

}