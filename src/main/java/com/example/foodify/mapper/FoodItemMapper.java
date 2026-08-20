package com.example.foodify.mapper;

import com.example.foodify.dto.FoodItemDTO;
import com.example.foodify.model.FoodItem;
import com.example.foodify.model.Restaurant;

public class FoodItemMapper {

    public static FoodItemDTO toDTO(FoodItem foodItem) {

        return new FoodItemDTO(
                foodItem.getName(),
                foodItem.getDescription(),
                foodItem.getPrice(),
                foodItem.getCategory(),
                foodItem.getRestaurant().getId()
        );
    }

    public static FoodItem toEntity(
            FoodItemDTO dto,
            Restaurant restaurant) {

        FoodItem foodItem = new FoodItem(
                dto.getName(),
                dto.getDescription(),
                dto.getPrice(),
                dto.getCategory()
        );

        foodItem.setRestaurant(restaurant);

        return foodItem;
    }
}