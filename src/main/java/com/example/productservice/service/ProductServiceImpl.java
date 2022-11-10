package com.example.productservice.service;

import com.example.productservice.dto.ProductRequest;
import com.example.productservice.dto.ProductResponse;
import com.example.productservice.model.Product;
import com.example.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    @Override
    public void createProduct(ProductRequest productRequest) {

        Product product = Product.builder()
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .name(productRequest.getName())
                .build();
        productRepository.save(product);
        log.info("product {} created successfully",product.getId());

    }

    @Override
    public List<ProductResponse> getAllProducts() {
        var products = productRepository.findAll();
        return products.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .description(product.getDescription())
                .price(product.getPrice())
                .name(product.getName())
                .build();
    }
}
