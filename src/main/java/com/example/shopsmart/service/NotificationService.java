package com.example.shopsmart.service;

import com.example.shopsmart.entity.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.concurrent.CompletableFuture;

@Service
public class NotificationService {

    // @Async matlab ye method alag thread mein chalega
    // main thread wait nahi karega — order quickly return hoga
    @Async
    public CompletableFuture<String> sendOrderConfirmation(Order order) {
        try {
            // simulate karo jaise email/SMS bhej rahe hain
            Thread.sleep(1000);
            System.out.println("[NOTIFICATION] Order #" + order.getId()
                    + " confirm hua! Customer: " + order.getCustomer().getEmail());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return CompletableFuture.completedFuture("Sent for order: " + order.getId());
    }
}