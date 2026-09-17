package com.example.foodify.dto;

public class OrderItemResponseDTO {

    private Long foodItemId;
    private Integer quantity;
    private Double price;

    public OrderItemResponseDTO() {
    }

    public OrderItemResponseDTO(
            Long foodItemId,
            Integer quantity,
            Double price) {

        this.foodItemId = foodItemId;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getFoodItemId() {
        return foodItemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setFoodItemId(Long foodItemId) {
        this.foodItemId = foodItemId;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}