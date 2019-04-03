package com.invillia.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.invillia.models.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

	Optional<OrderItem> findById(Long id);
}
