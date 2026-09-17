package com.example.foodify.dto;

import java.util.ArrayList;
import java.util.List;


import jakarta.validation.constraints.NotNull;

public class OrderDTO {
    @NotNull(message="Items should not be Empty")
private List<OrderItemDTO> items = new ArrayList<>();

    public OrderDTO(){}

    public OrderDTO(List<OrderItemDTO>items){
        this.items=items;
    }

    public List<OrderItemDTO> getItems(){
        return items;
    }

    public void setItems(List<OrderItemDTO> items){
        this.items=items;
    }


}
