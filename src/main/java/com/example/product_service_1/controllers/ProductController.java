package com.example.product_service_1.controllers;

import com.example.product_service_1.dtos.ProductRequestDto;
import com.example.product_service_1.dtos.ProductResponseDto;
import com.example.product_service_1.exceptions.ProductNotFoundException;
import com.example.product_service_1.models.Product;
import com.example.product_service_1.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/prodcuts")
public class ProductController {

    private ProductService productService;
    private ModelMapper modelMapper;

    public ProductController(@Qualifier("fakeStoreProductService") ProductService productService,
                             ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    // e.g: localhost:8080/products/5
    @GetMapping("{id}")
    public ProductResponseDto getProductDetails(@PathVariable("id") Long productId)
            throws ProductNotFoundException {
        Product product =  productService.getSingleProduct(productId);
        // return modelMapper.map(product, ProductResponseDto.class);
        return convertToProductResponseDto(product);
    }

//    @GetMapping("/products")
//    public List<ProductResponseDto> getAllProducts() {
//        List<Product> products = productService.getAllProducts();
//        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
//        for (Product product : products) {
//            productResponseDtos.add(convertToProductResponseDto(product));
//        }
//        return productResponseDtos;
//    }

    @GetMapping()
    public ResponseEntity<List<ProductResponseDto>> getAllProducts(
            @RequestParam("pageNumber") int pageNumber,
            @RequestParam("pageSize") int pageSize,
            @RequestParam("sortBy") String sortParam) {
        Page<Product> products = (Page<Product>) productService.getAllProducts();
        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        products.forEach(product -> productResponseDtos.add(convertToProductResponseDto(product)));
        return new ResponseEntity<>(productResponseDtos, HttpStatus.OK);
    }


    @PostMapping()
    public ResponseEntity<ProductResponseDto> createNewProduct(@RequestBody ProductRequestDto productRequestDto) {
        Product product = productService.addProduct(
                productRequestDto.getTitle(),
                productRequestDto.getDescription(),
                productRequestDto.getImageUrl(),
                productRequestDto.getCategory(),
                productRequestDto.getPrice() );
        // return convertToProductResponseDto(product);
        ProductResponseDto productResponseDto = convertToProductResponseDto(product);
        return new ResponseEntity<>(productResponseDto, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ProductResponseDto> deleteProduct(@PathVariable("id") Long productId)
            throws ProductNotFoundException {
        Product product = productService.deleteProduct(productId);
        ProductResponseDto productResponseDto = convertToProductResponseDto(product);
        return new ResponseEntity<>(productResponseDto, HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable("id") Long productId,
                                                            @RequestBody ProductRequestDto productRequestDto)
            throws ProductNotFoundException {
        Product product = productService.updateProduct(productId,
                productRequestDto.getTitle(),
                productRequestDto.getDescription(),
                productRequestDto.getImageUrl(),
                productRequestDto.getCategory(),
                productRequestDto.getPrice() );
        ProductResponseDto productResponseDto = convertToProductResponseDto(product);
        return new ResponseEntity<>(productResponseDto, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<ProductResponseDto> replaceProduct(@PathVariable("id") Long productId,
                                                             @RequestBody ProductRequestDto productRequestDto)
            throws ProductNotFoundException {
        Product product = productService.replaceProduct(productId,
                productRequestDto.getTitle(),
                productRequestDto.getDescription(),
                productRequestDto.getImageUrl(),
                productRequestDto.getCategory(),
                productRequestDto.getPrice() );
        ProductResponseDto productResponseDto = convertToProductResponseDto(product);
        return new ResponseEntity<>(productResponseDto, HttpStatus.OK);
    }
    private ProductResponseDto convertToProductResponseDto(Product product) {
        String categoryTitle = product.getCategory().getTitle();
        ProductResponseDto productResponseDto = modelMapper.map(product, ProductResponseDto.class);
        productResponseDto.setCategory(categoryTitle);
        return productResponseDto;
    }

//    // Add Exception Handler
//    @ExceptionHandler(ProductNotFoundException.class)
//    public ResponseEntity<ErrorDto> handleProductNotFoundException(ProductNotFoundException productNotFoundException) {
//        ErrorDto errorDto = new ErrorDto();
//        errorDto.setMessage(productNotFoundException.getMessage());
//        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
//    }
}