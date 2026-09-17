package com.example.foodify.service;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.foodify.dto.OrderDTO;
import com.example.foodify.dto.OrderItemDTO;
import com.example.foodify.dto.OrderItemResponseDTO;
import com.example.foodify.dto.OrderResponseDTO;
import com.example.foodify.dto.OrderStatusDTO;
import com.example.foodify.mapper.OrderItemMapper;
import com.example.foodify.model.FoodItem;
import com.example.foodify.model.Order;
import com.example.foodify.model.OrderItem;
import com.example.foodify.model.User;
import com.example.foodify.repository.FoodItemRepository;
import com.example.foodify.repository.OrderRepository;
import com.example.foodify.repository.UserRepository;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final FoodItemRepository foodItemRepository;
    
     private final UserRepository userRepository;


    public OrderService(
        OrderRepository orderRepository, 
        FoodItemRepository foodItemRepository,
    UserRepository userRepository )
        {
        this.orderRepository=orderRepository;
  
        this.foodItemRepository=foodItemRepository;
        this.userRepository=userRepository;
        }

    public OrderResponseDTO createOrder(OrderDTO dto) {

        String email = SecurityContextHolder
            .getContext()
            .getAuthentication()
            .getName();

    User user = userRepository.findByEmail(email)
        .orElseThrow(() ->
                new RuntimeException("User not found with email: " + email)
        );

    Order order = new Order();

    order.setUser(user);
    order.setStatus("PENDING");
    order.setTotalAmount(0.0);
    double totalAmount = 0.0;


for (OrderItemDTO itemDTO : dto.getItems()) {

    FoodItem foodItem = foodItemRepository
            .findById(itemDTO.getFoodItemId())
            .orElseThrow(() ->
                    new RuntimeException(
                            "Food item not found with id: "
                            + itemDTO.getFoodItemId()
                    )
            );

    totalAmount += foodItem.getPrice() * itemDTO.getQuantity();

    OrderItem orderItem = OrderItemMapper.toEntity(
        itemDTO,
        foodItem,
        order
);

order.getOrderItems().add(orderItem);

}

order.setTotalAmount(totalAmount);

Order savedOrder = orderRepository.save(order);

List<OrderItemResponseDTO> responseItems = savedOrder.getOrderItems()
        .stream()
        .map(OrderItemMapper::toResponseDTO)
        .toList();

return new OrderResponseDTO(
        savedOrder.getId(),
        savedOrder.getStatus(),
        savedOrder.getTotalAmount(),
        responseItems
);

}

public List<OrderResponseDTO> getMyOrders() {

    String email = SecurityContextHolder
            .getContext()
            .getAuthentication()
            .getName();

    User user = userRepository.findByEmail(email)
            .orElseThrow(() ->
                    new RuntimeException("User not found with email: " + email)
            );

    List<Order> orders = orderRepository.findByUser(user);

    return orders.stream()
            .map(order -> new OrderResponseDTO(
                    order.getId(),
                    order.getStatus(),
                    order.getTotalAmount(),
                    order.getOrderItems()
                            .stream()
                            .map(OrderItemMapper::toResponseDTO)
                            .toList()
            ))
            .toList();
}

public OrderResponseDTO getOrderById(Long id) {

    String email = SecurityContextHolder
            .getContext()
            .getAuthentication()
            .getName();

    User user = userRepository.findByEmail(email)
            .orElseThrow(() ->
                    new RuntimeException(
                            "User not found with email: " + email
                    )
            );

    Order order = orderRepository.findByIdAndUser(id, user)
            .orElseThrow(() ->
                    new RuntimeException(
                            "Order not found with id: " + id
                    )
            );

    return new OrderResponseDTO(
            order.getId(),
            order.getStatus(),
            order.getTotalAmount(),
            order.getOrderItems()
                    .stream()
                    .map(OrderItemMapper::toResponseDTO)
                    .toList()
    );
}


public OrderResponseDTO updateOrderStatus(Long id, OrderStatusDTO dto){


        Order order = orderRepository.findById(id)
        .orElseThrow(() ->
                new RuntimeException("Order not found with id: " + id)
        );
         order.setStatus(dto.getStatus());

         Order savedOrder=orderRepository.save(order);

         return new OrderResponseDTO(
                savedOrder.getId(),
                savedOrder.getStatus(),
                savedOrder.getTotalAmount(),
                savedOrder.getOrderItems()
                                        .stream()
                                        .map(OrderItemMapper::toResponseDTO)
                                        .toList()
         );

}

    
}
