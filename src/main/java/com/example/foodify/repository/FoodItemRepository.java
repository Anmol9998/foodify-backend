package com.example.foodify.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.foodify.model.FoodItem;

public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {

List<FoodItem> findByRestaurantId(Long restaurantId);
    
}
