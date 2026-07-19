package com.example.foodify.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     public ResponseEntity<Restaurant> addRestaurant(@RequestBody Restaurant restaurant) {

        Restaurant savedRestaurant = restaurantRepository.save(restaurant);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRestaurant);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable Long id, @RequestBody Restaurant updatedRestaurant ){
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
         if (restaurant.isEmpty()) {
        return ResponseEntity.notFound().build();
       }
       Restaurant existingRestaurant = restaurant.get();
    existingRestaurant.setName(updatedRestaurant.getName());
    existingRestaurant.setAddress(updatedRestaurant.getAddress());
    existingRestaurant.setCuisine(updatedRestaurant.getCuisine());
    existingRestaurant.setRating(updatedRestaurant.getRating());
    return ResponseEntity.ok(restaurantRepository.save(existingRestaurant));
}


    @GetMapping
    public ResponseEntity<List<Restaurant>> getAllRestaurants(){
        return ResponseEntity.ok(restaurantRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable Long id) {
    Optional<Restaurant> restaurant = restaurantRepository.findById(id);
    if (restaurant.isEmpty()) {
    return ResponseEntity.notFound().build();
}
    return ResponseEntity.ok(restaurant.get());
}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id){
         Optional<Restaurant> restaurant = restaurantRepository.findById(id);
         if (restaurant.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

       restaurantRepository.deleteById(id);
       return ResponseEntity.noContent().build();
    }

}
