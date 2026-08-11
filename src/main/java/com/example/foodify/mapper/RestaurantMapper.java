package com.example.foodify.mapper;

import com.example.foodify.dto.RestaurantDTO;
import com.example.foodify.model.Restaurant;

public class RestaurantMapper {
    
    public static RestaurantDTO toDTO(Restaurant restaurant){
             return new RestaurantDTO(
             restaurant.getName(),
            restaurant.getAddress(),
            restaurant.getCuisine(),
            restaurant.getRating()
    );
    }



public static Restaurant toEntity(RestaurantDTO dto) {

    return new Restaurant(
            dto.getName(),
            dto.getAddress(),
            dto.getCuisine(),
            dto.getRating()
    );
}

//   public static Restaurant toEntity(RestaurantDTO dto){
//       Restaurant restaurant = new Restaurant();

//     restaurant.setName(dto.getName());
//     restaurant.setCuisine(dto.getCuisine());
//     restaurant.setRating(dto.getRating());
//     restaurant.setAddress(dto.getAddress());

//     return restaurant;
// }
}
