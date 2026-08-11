package com.example.foodify.controller;

import java.util.ArrayList;
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

import com.example.foodify.dto.RestaurantDTO;
import com.example.foodify.mapper.RestaurantMapper;
import com.example.foodify.model.Restaurant;
import com.example.foodify.service.RestaurantService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<RestaurantDTO> addRestaurant(@Valid @RequestBody RestaurantDTO dto) {

        Restaurant restaurant = RestaurantMapper.toEntity(dto);

        Restaurant savedRestaurant = restaurantService.addRestaurant(restaurant);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(RestaurantMapper.toDTO(savedRestaurant));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<RestaurantDTO> updateRestaurant(
            @PathVariable Long id,
            @RequestBody RestaurantDTO dto) {

        Restaurant restaurant = RestaurantMapper.toEntity(dto);

        Restaurant updatedRestaurant =
                restaurantService.updateRestaurant(id, restaurant);

        return ResponseEntity.ok(
                RestaurantMapper.toDTO(updatedRestaurant));
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<RestaurantDTO>> getAllRestaurants() {

        List<Restaurant> restaurants = restaurantService.getAllRestaurants();

        List<RestaurantDTO> dtos = new ArrayList<>();

        for (Restaurant restaurant : restaurants) {
            dtos.add(RestaurantMapper.toDTO(restaurant));
        }

        return ResponseEntity.ok(dtos);
    }




    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDTO> getRestaurantById(@PathVariable Long id) {

        Restaurant restaurant = restaurantService.getRestaurantById(id);

        return ResponseEntity.ok(
                RestaurantMapper.toDTO(restaurant));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id) {

        restaurantService.deleteRestaurant(id);

        return ResponseEntity.noContent().build();
    }
}