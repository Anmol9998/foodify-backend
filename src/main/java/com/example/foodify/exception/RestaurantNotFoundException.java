package com.example.foodify.exception;

public class RestaurantNotFoundException extends RuntimeException {
    
    public RestaurantNotFoundException(String message) {
    super(message);
}

}
