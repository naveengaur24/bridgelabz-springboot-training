package com.example.shopsmart.service;
import com.example.shopsmart.entity.*;
import com.example.shopsmart.enums.OrderStatus;
import com.example.shopsmart.exception.*;
import com.example.shopsmart.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final NotificationService notificationService;

    @Autowired
    public OrderService(OrderRepository orderRepository,
                        ProductRepository productRepository,
                        CustomerRepository customerRepository,
                        NotificationService notificationService) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.notificationService = notificationService;
    }

    public Order createOrder(Long customerId, List<OrderItem> items, String paymentType) {

        // Customer exist karta hai..
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new InvalidOrderException("Customer not found: " + customerId));

        double total = 0;

        //  Har item ke liye product check kia
        for (OrderItem item : items) {

            // product database se laya (real product, poori info ke saath)
            Product product = productRepository.findById(item.getProduct().getId())
                    .orElseThrow(() -> new ProductNotFoundException(item.getProduct().getId()));

            // stock enough hai ya ni check
            if (product.getStockQuantity() < item.getQuantity()) {
                throw new InsufficientStockException(product.getName(), product.getStockQuantity());
            }

            // stock ghatao
            product.setStockQuantity(product.getStockQuantity() - item.getQuantity());
            productRepository.save(product);
            // purchase time ka price set karo
            item.setPriceAtPurchase(product.calculateFinalPrice());
            // total calculate karo
            total += product.calculateFinalPrice() * item.getQuantity();
        }
        //  Order banaya and save kia..
        Order order = Order.builder()
                .customer(customer)
                .items(items)
                .status(OrderStatus.PENDING)
                .totalAmount(total)
                .build();
        Order saved = orderRepository.save(order);
        // Async notification bheja..
        notificationService.sendOrderConfirmation(saved);
        return saved;
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new InvalidOrderException("Order not found: " + id));
    }

    // pagination ke saath — page number aur size lia..
    public Page<Order> getOrdersByCustomer(Long customerId, int page, int size) {
        return orderRepository.findByCustomerId(customerId, PageRequest.of(page, size));
    }

    public Order updateStatus(Long id, OrderStatus status) {
        Order order = getOrderById(id);
        order.setStatus(status);
        return orderRepository.save(order);
    }

    public Order cancelOrder(Long id) {
        Order order = getOrderById(id);
        // delivered order cancel nahi ho sakta
        if (order.getStatus() == OrderStatus.DELIVERED) {
            throw new InvalidOrderException("Cannot cancel a delivered order");
        }
        order.setStatus(OrderStatus.CANCELLED);
        return orderRepository.save(order);
    }

    public List<Order> getDailyOrders(LocalDate date) {
        return orderRepository.findByDateRange(date, date);
    }
}