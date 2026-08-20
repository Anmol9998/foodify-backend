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

import com.example.foodify.dto.FoodItemDTO;
import com.example.foodify.model.FoodItem;
import com.example.foodify.service.FoodItemService;

import jakarta.validation.Valid;




@RestController
@RequestMapping("/api/restaurants")
public class FoodItemController {

    private final FoodItemService foodItemService;

    public FoodItemController(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }

    @PostMapping("/{restaurantId}/foods")
    public ResponseEntity<FoodItem> addFoodItem(
            @PathVariable Long restaurantId,
            @Valid @RequestBody FoodItemDTO dto) {

        dto.setRestaurantId(restaurantId);

        FoodItem foodItem = foodItemService.addFoodItem(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(foodItem);
    }


    
    @GetMapping("/{restaurantId}/foods")
        public ResponseEntity<List<FoodItemDTO>> getFoodItemsByRestaurant(
        @PathVariable Long restaurantId) {

    List<FoodItemDTO> foodItems =
            foodItemService.getFoodItemsByRestaurant(restaurantId);

        return ResponseEntity.ok(foodItems);
}

@GetMapping("/foods/{id}")
public ResponseEntity<FoodItemDTO> getFoodItemById(
        @PathVariable Long id) {

    FoodItemDTO foodItem = foodItemService.getFoodItemById(id);

    return ResponseEntity.ok(foodItem);
}

@PutMapping("/foods/{id}")
public ResponseEntity<FoodItemDTO>  updateFoodItem(
    @PathVariable Long id,
    @Valid @RequestBody FoodItemDTO dto) {
    
    FoodItemDTO updatedFoodItem =
            foodItemService.updateFoodItem(id, dto);
             
    return ResponseEntity.ok(updatedFoodItem);
    
    }

@DeleteMapping("/foods/{id}")
public ResponseEntity <Void> deleteFoodItem(@PathVariable Long id) {
    // call service
    // return appropriate response

    foodItemService.deleteFoodItem(id);

        return ResponseEntity.noContent().build();
    }


}