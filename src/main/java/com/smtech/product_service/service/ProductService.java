package com.smtech.product_service.service;

import com.smtech.product_service.dto.ProductRequest;
import com.smtech.product_service.dto.ProductResponse;
import com.smtech.product_service.model.Product;
import com.smtech.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct (ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .type(productRequest.getType())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);
        log.info("Product {} is Saved",product.getId());
    }

    public List<ProductResponse> getAllproducts() {
        List<Product> products = productRepository.findAll();

        return products.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .type(product.getType())
                .price(product.getPrice())
                .build();
    }
}
