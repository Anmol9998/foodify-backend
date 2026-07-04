package com.example.foodify.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.foodify.repository.RestaurantRepository;


@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {
    
    private final RestaurantRepository restaurantRepository;

    public RestaurantController(RestaurantRepository restaurantRepository){
        this.restaurantRepository = restaurantRepository;
    }
}
