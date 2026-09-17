package com.example.foodify.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="order_items")

public class OrderItem {

    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="food_item_id")
    private FoodItem foodItem;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    private  Integer quantity;

    private  Double price;

    public OrderItem(){}

    public OrderItem(
        Long id,
        FoodItem foodItem,
        Order order,
        Integer quantity,
        Double price) {

    this.id = id;
    this.foodItem = foodItem;
    this.order = order;
    this.quantity = quantity;
    this.price = price;
}

      public Long getId(){
        return id;
    }

    public FoodItem getFoodItem(){
        return foodItem;
    }

    public Integer getQuantity(){
        return quantity;
    }

    public Double getPrice(){
        return price;
    }

   public Order getOrder() {
    return order;
}

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFoodItem(FoodItem foodItem) {
        this.foodItem = foodItem;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setPrice(Double price) {
        this.price = price;
    }



}
