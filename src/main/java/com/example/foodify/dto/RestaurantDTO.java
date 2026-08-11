package com.example.foodify.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;



public class RestaurantDTO {
     @NotBlank(message = "Restaurant name is required")
    private String name;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "Cuisine is required")
    private String cuisine;

    @NotNull(message = "Rating is required")
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating cannot be greater than 5")
    private Double rating;

    public RestaurantDTO() {
}

    public RestaurantDTO(String name, String address, String cuisine, Double rating) {
    this.name = name;
    this.address = address;
    this.cuisine = cuisine;
    this.rating = rating;
}


public void setName(String name){
    this.name=name;
}

public void setCuisine(String cuisine){
    this.cuisine=cuisine;
}

public void setRating(Double rating){
 this.rating=rating;
}

public void setAddress(String address){
 this.address=address;
}


public String getName() {
    return name;
}

public String getCuisine() {
    return cuisine;
}

public String getAddress() {
    return address;
}


public double getRating() {
    return rating;
}
    



}
