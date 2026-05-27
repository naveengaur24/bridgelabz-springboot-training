package com.example.shopsmart.controller;

import com.example.shopsmart.dto.CreateOrderRequest;
import com.example.shopsmart.entity.*;
import com.example.shopsmart.enums.OrderStatus;
import com.example.shopsmart.service.OrderService;
import com.example.shopsmart.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final ProductRepository productRepository;

    @Autowired
    public OrderController(OrderService orderService,
                           ProductRepository productRepository) {
        this.orderService = orderService;
        this.productRepository = productRepository;
    }

    // POST /api/orders
    @PostMapping
    public ResponseEntity<Order> createOrder(@Valid @RequestBody CreateOrderRequest req) {

        // Request se OrderItem list banao
        List<OrderItem> items = req.getItems().stream().map(i -> {
            // Sirf ID set karo — OrderService mein puri info fetch hogi
            Product p = productRepository.findById(i.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found: " + i.getProductId()));

            return OrderItem.builder()
                    .product(p)
                    .quantity(i.getQuantity())
                    .build();
        }).collect(Collectors.toList());

        Order created = orderService.createOrder(req.getCustomerId(), items, req.getPaymentType());
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // GET /api/orders/5
    @GetMapping("/{id}")
    public ResponseEntity<Order> getById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    // GET /api/orders/customer/3?page=0&size=5
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<Page<Order>> getByCustomer(
            @PathVariable Long customerId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(orderService.getOrdersByCustomer(customerId, page, size));
    }

    // PUT /api/orders/5/status?status=CONFIRMED
    @PutMapping("/{id}/status")
    public ResponseEntity<Order> updateStatus(@PathVariable Long id,
                                              @RequestParam String status) {
        return ResponseEntity.ok(orderService.updateStatus(id, OrderStatus.valueOf(status.toUpperCase())));
    }

    // POST /api/orders/5/cancel
    @PostMapping("/{id}/cancel")
    public ResponseEntity<Order> cancel(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.cancelOrder(id));
    }

    // GET /api/orders/reports/daily?date=2024-01-15
    @GetMapping("/reports/daily")
    public ResponseEntity<List<Order>> dailyReport(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(orderService.getDailyOrders(date));
    }
}