package com.example.foodify.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.foodify.dto.OrderDTO;
import com.example.foodify.dto.OrderResponseDTO;
import com.example.foodify.dto.OrderStatusDTO;
import com.example.foodify.service.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(
        @Valid @RequestBody OrderDTO dto) {

        OrderResponseDTO order = orderService.createOrder(dto);
        return ResponseEntity
                    .status(201)
                    .body(order);
}

@GetMapping

public ResponseEntity<List<OrderResponseDTO>> getMyOrders(){


List<OrderResponseDTO> orders = orderService.getMyOrders();

return ResponseEntity.ok(orders);

}

@GetMapping("/{id}")
public ResponseEntity<OrderResponseDTO> getOrderById(
        @PathVariable Long id) {

    OrderResponseDTO order = orderService.getOrderById(id);

    return ResponseEntity.ok(order);
}



@PatchMapping("/{id}/status")
public ResponseEntity<OrderResponseDTO> updateOrderStatus(
        @PathVariable Long id,
        @RequestBody OrderStatusDTO dto){
            
            OrderResponseDTO order=orderService.updateOrderStatus(id, dto);


            return ResponseEntity.ok(order);

        }




}
