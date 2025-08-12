package com.example.inventory_dash_backend.domain.model;

import java.util.UUID;

/*
 * Generates a products id*/
public final class ProductId {
    
    private final UUID value; 

    // Constructor that ensures every product has an ID
    private ProductId(UUID value){
        if(value == null) {
            throw new IllegalArgumentException("ProductId must have a number!");
        }

        this.value = value;
    }

    // Factory method to create a product ID
    public static ProductId createNew() {
        return new ProductId( UUID.randomUUID() );
    }

    // Factory method to get existing ID
    public static ProductId fromString(String id) {
        return new ProductId(UUID.fromString(id));
    }

    // CHhange the id to a String
    public String toString() {
        return value.toString();
    }

    // Check if two ids are the same
    public boolean equals(Object o){
        if (this == o) return true;
        if(!(o instanceof ProductId)) return false;
        ProductId other = (ProductId) o;
        return value.equals(other.value);
    }

    // Takes value and gives out hashed value
    public int hashCode() {
        return value.hashCode();
    }

    // Getter for interation with other layers
    public UUID getValue() {
        return value;
    }
}
