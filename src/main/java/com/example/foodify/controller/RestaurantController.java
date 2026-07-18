package com.example.foodify.controller;

import java.util.Optional;
import java.util.List;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.foodify.model.Restaurant;
import com.example.foodify.repository.RestaurantRepository;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {
    
    private final RestaurantRepository restaurantRepository;

    public RestaurantController(RestaurantRepository restaurantRepository){
        this.restaurantRepository = restaurantRepository;
    }
    @PostMapping
     public Restaurant addRestaurant(@RequestBody Restaurant restaurant) {

        return restaurantRepository.save(restaurant);

    }
    @PutMapping("/{id}")
    public Restaurant updateRestaurant(@PathVariable Long id, @RequestBody Restaurant updatedRestaurant ){
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
         if (restaurant.isEmpty()) {
        return null;
       }
       Restaurant existingRestaurant = restaurant.get();
    existingRestaurant.setName(updatedRestaurant.getName());
    existingRestaurant.setAddress(updatedRestaurant.getAddress());
    existingRestaurant.setCuisine(updatedRestaurant.getCuisine());
    existingRestaurant.setRating(updatedRestaurant.getRating());
    return restaurantRepository.save(existingRestaurant);
}


    @GetMapping
    public List<Restaurant>getAllRestaurants(){
        return restaurantRepository.findAll();
    }
    @GetMapping("/{id}")
    public Restaurant getRestaurantById(@PathVariable Long id) {
    Optional<Restaurant> restaurant = restaurantRepository.findById(id);
    return restaurant.orElse(null);
}
}
