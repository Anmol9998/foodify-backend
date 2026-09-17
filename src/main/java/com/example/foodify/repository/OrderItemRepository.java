package com.example.foodify.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.foodify.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}