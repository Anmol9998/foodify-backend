package com.example.foodify.controller;

import java.util.List;

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
import com.example.foodify.service.RestaurantService;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {
    
    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService){
        this.restaurantService = restaurantService;
    }
    @PostMapping
     public ResponseEntity<Restaurant> addRestaurant(@RequestBody Restaurant restaurant) {

        Restaurant savedRestaurant = restaurantService.addRestaurant(restaurant);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRestaurant);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable Long id, @RequestBody Restaurant updatedRestaurant ){
       Restaurant restaurant =    restaurantService.updateRestaurant(id, updatedRestaurant);
            
        return ResponseEntity.ok(restaurant);
}


    @GetMapping
    public ResponseEntity<List<Restaurant>> getAllRestaurants(){
        return ResponseEntity.ok(restaurantService.getAllRestaurants());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable Long id) {
    Restaurant restaurant = restaurantService.getRestaurantById(id);

        return ResponseEntity.ok(restaurant);
}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id){

       restaurantService.deleteRestaurant(id);
       return ResponseEntity.noContent().build();
    }

}
