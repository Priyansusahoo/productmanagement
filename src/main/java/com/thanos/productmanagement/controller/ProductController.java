package com.thanos.productmanagement.controller;

import com.thanos.productmanagement.model.Product;
import com.thanos.productmanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Priyansu Sahoo
 * @created 14-05-2024 - 11:44 pm
 */
@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/getAllProduct")
    public ResponseEntity<List<Product>> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/getProductById")
    public ResponseEntity<Optional<?>> getProductById(@RequestParam Long id){
        return productService.getProductById(id);
    }

    @PostMapping("/createProduct")
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }

    @PutMapping("/updateProduct")
    public ResponseEntity<Product> updateProduct(@RequestParam Long id, @RequestBody Product product){
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/deleteProduct")
    public ResponseEntity<Boolean> deleteProduct(@RequestParam Long id){
        return productService.deleteProduct(id);
    }
}
