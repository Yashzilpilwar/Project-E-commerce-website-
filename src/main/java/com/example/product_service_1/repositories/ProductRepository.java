package com.example.product_service_1.repositories;

import com.example.product_service_1.models.Product;
import com.example.product_service_1.repositories.projections.ProductProjections;
import com.example.product_service_1.repositories.projections.ProductWithIdAndTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Product save(Product product);
    List<Product> findAll();
    Product findByIdIs(Long id);

    List<Product> findAllByCategory_Title(String title);


    @Query("select p from Product p where p.category.title = :categoryName")
    List<Product> getProductByCategoryName(String categoryName);

    @Query("select p.title as title from Product p where p.category.title = :categoryName")
    List<String> someTitleMethod(String categoryName);

    @Query("select p.id as id from Product p where p.category.title = :categoryName")
    List<ProductWithIdAndTitle> someMethod1(String categoryName);


    @Query("select p.id as id from Product p where p.category.title = :categoryName")
    List<ProductProjections> someMethod2(String categoryName);


    // Native SQL
    @Query(value = "select * from product p where p.id = :id", nativeQuery = true)
    Product someNativeSql(Long id);
}
