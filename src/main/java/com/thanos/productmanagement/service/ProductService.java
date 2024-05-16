package com.thanos.productmanagement.service;

import com.thanos.productmanagement.model.Product;
import com.thanos.productmanagement.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    @Qualifier("productRepository")
    private ProductRepository productRepository;

    public ResponseEntity<List<Product>> getAllProducts() {
        try {
            List<Product> products = productRepository.findAll();
            if(products.isEmpty()){
                log.error("No Products Found!!");
                return ResponseEntity.ok(null);
            } else {
                log.info("Product list retrieved");
                return ResponseEntity.ok(productRepository.findAll());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<Optional<?>> getProductById(Long id) {
        try {
            Optional<Product> product = productRepository.findById(id);
            if (product.isEmpty()){
                log.error("No product with id = {} found", id);
                return ResponseEntity.status(HttpStatus.OK).body(Optional.of("No product found with the specified id: " + id));
            } else {
                log.info("Product with id = {} retrieved", id);
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
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Product());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(product);
        }
    }

    public ResponseEntity<Product> updateProduct(Long id, Product product) {
        try {
            Optional<Product> optionalProduct = productRepository.findById(id);

            if (optionalProduct.isPresent()){
                optionalProduct.get().setName(product.getName());
                optionalProduct.get().setDescription(product.getDescription());
                optionalProduct.get().setPrice(product.getPrice());
//                Product existingProduct = Product.builder()
//                        .name(product.getName())
//                        .description(product.getDescription())
//                        .price(product.getPrice())
//                        .build();
                productRepository.save(optionalProduct.get());
                log.info("Product with id = {} updated successfully", optionalProduct.get().getId());
                return ResponseEntity.status(HttpStatus.CREATED).body(optionalProduct.get());
            } else {
                log.error("No product with id = {} found", id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Product());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<Boolean> deleteProduct(Long id) {
        try {
            Optional<Product> optionalProduct = productRepository.findById(id);

            if (optionalProduct.isPresent()) {
                productRepository.deleteById(id);
                log.info("Product with id = {} deleted successfully", optionalProduct.get().getId());
                return ResponseEntity.status(HttpStatus.OK).body(Boolean.TRUE);
            } else {
                log.error("Product Doesn't exist");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Boolean.FALSE);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
