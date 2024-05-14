package com.thanos.productmanagement.repository;

import com.thanos.productmanagement.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Priyansu Sahoo
 * @created 14-05-2024 - 11:45 pm
 */
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
