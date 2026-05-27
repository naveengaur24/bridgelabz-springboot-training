package com.example.shopsmart.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequest {

    @NotBlank(message = "Name required")
    private String name;

    @Min(value = 0, message = "Price cannot be negative")
    private double basePrice;

    @NotBlank(message = "Category required")
    private String category;

    @Min(value = 0, message = "Stock cannot be negative")
    private int stockQuantity;

    // "ELECTRONICS" ya "CLOTHING"
    @NotBlank(message = "Type required")
    private String type;

    // Electronics ke liye
    private int warrantyMonths;

    // Clothing ke liye
    private String size;   // S, M, L, XL
    private String color;
}