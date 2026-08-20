package com.example.foodify.exception;

public class FoodItemNotFoundException extends RuntimeException{
    
    public FoodItemNotFoundException(String message){
        super(message);
    }

}
