package com.thanos.productmanagement.repository;

import com.thanos.productmanagement.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Priyansu Sahoo
 * @created 14-05-2024 - 11:46 pm
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
}
