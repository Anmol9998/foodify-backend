package com.example.foodify.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.foodify.dto.FoodItemDTO;
import com.example.foodify.exception.FoodItemNotFoundException;
import com.example.foodify.exception.RestaurantNotFoundException;
import com.example.foodify.mapper.FoodItemMapper;
import com.example.foodify.model.FoodItem;
import com.example.foodify.model.Restaurant;
import com.example.foodify.repository.FoodItemRepository;
import com.example.foodify.repository.RestaurantRepository;


@Service
public class FoodItemService {
    private final FoodItemRepository foodItemRepository;
private final RestaurantRepository restaurantRepository;

public FoodItemService(
        FoodItemRepository foodItemRepository,
        RestaurantRepository restaurantRepository) {

    this.foodItemRepository = foodItemRepository;
    this.restaurantRepository = restaurantRepository;

}

public FoodItem addFoodItem(FoodItemDTO dto) {

    Restaurant restaurant = restaurantRepository
            .findById(dto.getRestaurantId())
            .orElseThrow(() ->
                new RestaurantNotFoundException(
                    "Restaurant not found with id: "
                    + dto.getRestaurantId()
                )
            );

    FoodItem foodItem = FoodItemMapper.toEntity(dto, restaurant);

    return foodItemRepository.save(foodItem);
}

public List <FoodItem> getAllFoodItems(){

    return foodItemRepository.findAll();

}

public FoodItemDTO getFoodItemById(Long id) {

    FoodItem foodItem = foodItemRepository
            .findById(id)
            .orElseThrow(() ->
                    new FoodItemNotFoundException(
                            "Food Item is not found with id: " + id
                    ));

    return FoodItemMapper.toDTO(foodItem);
}

public List<FoodItemDTO> getFoodItemsByRestaurant(Long restaurantId){

    return foodItemRepository.findByRestaurantId(restaurantId)
                             .stream()
                             .map(FoodItemMapper::toDTO)
                             .toList();

}


public FoodItemDTO updateFoodItem(Long id, FoodItemDTO dto) {

    FoodItem foodItem = foodItemRepository
            .findById(id)
            .orElseThrow(() ->
                    new FoodItemNotFoundException(
                            "Food Item is not found with id: " + id
                    ));

    foodItem.setName(dto.getName());
    foodItem.setDescription(dto.getDescription());
    foodItem.setPrice(dto.getPrice());
    foodItem.setCategory(dto.getCategory());

    FoodItem updatedFoodItem = foodItemRepository.save(foodItem);

    return FoodItemMapper.toDTO(updatedFoodItem);
}



public void deleteFoodItem(Long id) {
    // your code
    getFoodItemById(id);
    foodItemRepository.deleteById(id);
}



    
}
