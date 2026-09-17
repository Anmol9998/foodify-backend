package com.example.foodify.dto;

public class OrderStatusDTO {
    private String status;

    public OrderStatusDTO(){}

    public OrderStatusDTO(String status){
        this.status=status;
    }
    
    public String getStatus(){
        return status;
    }
    public void setStatus(String status){
        this.status=status;
    }
    
}
