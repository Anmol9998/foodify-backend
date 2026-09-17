package com.example.foodify.dto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class OrderItemDTO {
    @NotNull(message="food item ID is required")
    private Long foodItemId;

    @NotNull(message="Quantity is requaired")
    @Positive(message="Quantity must be greater than 0")
    private Integer quantity;

    public OrderItemDTO(){}

    public OrderItemDTO(Long foodItemId, Integer quantity){
        this.foodItemId=foodItemId;
        this.quantity=quantity;
    }

  public  Long getFoodItemId(){
        return foodItemId;
    }
  public Integer getQuantity(){
    return quantity;
  }  
  public void setFoodItemId(Long foodItemId){
    this.foodItemId=foodItemId;
  }

    public void setQuantity(Integer quantity){
    this.quantity=quantity;
  }



}
