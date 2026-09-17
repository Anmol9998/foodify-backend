package com.example.foodify.model;

import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.*;


@Entity
@Table(name="oorders")
public class Order {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    private String status;

    private Double totalAmount;

    public Order(){}

    public Order( Long id,User user,List<OrderItem> orderItems,String status,Double totalAmount){

        this.id=id;
        this.user = user;
        this.orderItems=orderItems;
        this.status=status;
        this.totalAmount=totalAmount;
    }

       public Long getId(){
        return id;
    }

    public List<OrderItem> getOrderItems(){
        return orderItems;
    }

    public String getStatus(){
        return status;
    }

    public Double getTotalAmount(){
        return totalAmount;
    }

   public User getUser() {
    return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    
}
