package com.example.shopsmart.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private String errorCode;     // "PRODUCT_NOT_FOUND"
    private String message;       // "Product not found with id: 5"
    private LocalDateTime timestamp; // kab hua error
}