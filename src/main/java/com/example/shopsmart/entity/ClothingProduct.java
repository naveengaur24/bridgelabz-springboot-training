package com.example.shopsmart.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CLOTHING")
public class ClothingProduct extends Product {

    private String size;

    private String color;

    @Override
    public double calculateFinalPrice() {
        return getBasePrice();
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
