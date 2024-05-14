package com.thanos.productmanagement.service;

import com.thanos.productmanagement.model.Product;
import com.thanos.productmanagement.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Priyansu Sahoo
 * @created 14-05-2024 - 11:46 pm
 */
@Service
@Slf4j
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public ResponseEntity<List<Product>> getAllProducts() {
        try {
            List<Product> products = productRepository.findAll();
            if(products.isEmpty()){
                log.error("No Products Found!!");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            } else {
                log.info("Product list retrieved");
                return ResponseEntity.ok(productRepository.findAll());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<Optional<Product>> getProductById(Long id) {
        try {
            Optional<Product> product = productRepository.findById(id);
            if (product.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            } else {
                return ResponseEntity.ok(productRepository.findById(id));
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<Product> createProduct(Product product) {
        try {
            if (!product.getName().isEmpty()) {
                Product newProduct = Product.builder()
                        .name(product.getName())
                        .description(product.getDescription())
                        .price(product.getPrice())
                        .build();

                productRepository.save(newProduct);
                log.info("Product with id = {} created Successfully", newProduct.getId());
                return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
            } else {
                log.error("Unable to create Product");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(product);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<Product> updateProduct(Long id, Product product) {
    }

    public ResponseEntity<Boolean> deleteProduct(Long id) {
    }
}
