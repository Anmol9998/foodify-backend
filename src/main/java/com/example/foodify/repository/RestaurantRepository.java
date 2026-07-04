package com.example.foodify.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.foodify.model.Restaurant;

public interface  RestaurantRepository extends JpaRepository<Restaurant, Long> {
    
}
