package com.example.shopsmart.exception;
import lombok.*;
import java.time.LocalDateTime;

@Getter
public class ShopSmartException extends RuntimeException {

    private final String errorCode;
    private final LocalDateTime timestamp = LocalDateTime.now();

    public ShopSmartException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}