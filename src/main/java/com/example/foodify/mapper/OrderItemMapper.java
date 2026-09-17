package com.example.foodify.mapper;

import com.example.foodify.dto.OrderItemDTO;
import com.example.foodify.dto.OrderItemResponseDTO;
import com.example.foodify.model.FoodItem;
import com.example.foodify.model.Order;
import com.example.foodify.model.OrderItem;

public class OrderItemMapper {

    public static OrderItem toEntity(
            OrderItemDTO dto,
            FoodItem foodItem,
            Order order) {

        OrderItem orderItem = new OrderItem(
                null,
                foodItem,
                order,
                dto.getQuantity(),
                foodItem.getPrice()
        );

        return orderItem;
    }

    public static OrderItemResponseDTO toResponseDTO(OrderItem orderItem){
        return new OrderItemResponseDTO(
            orderItem.getFoodItem().getId(),
            orderItem.getQuantity(),
            orderItem.getPrice()
        );
    }
}