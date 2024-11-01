package com.ijse.springintro.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ijse.springintro.dto.OrderDTO;
import com.ijse.springintro.entity.Order;
import com.ijse.springintro.entity.Product;
import com.ijse.springintro.service.OrderService;
import com.ijse.springintro.service.ProductService;

@RestController
@CrossOrigin(origins = "*")
public class OrderController {
    
@Autowired
private OrderService orderService;

@Autowired
private ProductService productService;

@GetMapping("/order")
public ResponseEntity<List<Order>> getAllOrders() {
    List<Order> orders = orderService.getAllOrders();
    return ResponseEntity.status(200).body(orders);
}

@PostMapping("/orders")
public ResponseEntity<Order> createOrder(OrderDTO orderDTO){
    Order order = new Order();

    List<Long> productIds = orderDTO.getProductIds();

    order.setTotalPrice(0.0);

    List<Product> orderedproducts = new ArrayList<>();
    productIds.forEach(productId -> {
        Product product = productService.getProductById(productId);
    
        if(product != null) {
            order.getOrderedproducts().add(product);
            order.setTotalPrice(order.getTotalPrice() + product.getPrice());
        }
    
    
    });

    order.setOrderedproducts(orderedproducts);

    orderService.createOrder(order);

    return ResponseEntity.status(200).body(order);
}

}
