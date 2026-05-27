package com.example.shopsmart.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequest {

    @NotNull(message = "Customer ID required")
    private Long customerId;

    @NotNull
    @Size(min = 1, message = "At least 1 item required")
    private List<OrderItemRequest> items;

    @NotBlank(message = "Payment type required")
    private String paymentType; // CREDIT_CARD, UPI, CASH_ON_DELIVERY

    // inner class — order ke andar item ka structure
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderItemRequest {

        @NotNull(message = "Product ID required")
        private Long productId;

        @Min(value = 1, message = "Quantity must be at least 1")
        private int quantity;
    }
}