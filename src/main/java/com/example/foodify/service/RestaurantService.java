package com.example.foodify.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.foodify.exception.RestaurantNotFoundException;
import com.example.foodify.model.Restaurant;
import com.example.foodify.repository.RestaurantRepository;


@Service

public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
     public RestaurantService(RestaurantRepository restaurantRepository){
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant addRestaurant(Restaurant restaurant){
        return restaurantRepository.save(restaurant);
    }

    public List<Restaurant> getAllRestaurants() {
          return restaurantRepository.findAll();
}
public Restaurant getRestaurantById(Long id){
      Optional<Restaurant> restaurant = restaurantRepository.findById(id);
    if(restaurant.isEmpty()){
       throw new RestaurantNotFoundException("Restaurant not found with id: " + id);
    }
    return restaurant.get();
    //   Short methode
    //   return restaurantRepository.findById(id).orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found with id: " + id));

}
 public void deleteRestaurant( Long id){
     getRestaurantById(id);

    restaurantRepository.deleteById(id);
 }

 public Optional<Restaurant>updateRestaurant(Long id,   Restaurant updatedRestaurant){
    Optional<Restaurant> restaurant = restaurantRepository.findById(id);
    if(restaurant.isEmpty()){
        return Optional.empty();
    }
    Restaurant existingRestaurant = restaurant.get();
    existingRestaurant.setName(updatedRestaurant.getName());
    existingRestaurant.setAddress(updatedRestaurant.getAddress());
    existingRestaurant.setCuisine(updatedRestaurant.getCuisine());
    existingRestaurant.setRating(updatedRestaurant.getRating());

    return Optional.of(existingRestaurant);

 }
}


