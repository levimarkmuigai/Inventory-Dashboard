package com.example.inventory_dash_backend.domain.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Product class which represents an item
 * in the inventory, owning its identity,
 * stock level and price
 */
public class Product {
    
    private final ProductId id;
    private String name;
    private BigDecimal price;
    private int quantity;

    /**
     * Private constructor that performs assignment and validation
     */
    private Product(ProductId id, String name,
            BigDecimal price, int quantity) {

        this.id = Objects.requireNonNull(id, "Product id cannot be empty.");

        if(name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;

        if( price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price should be more than zero!");
        }
        this.price = price;
        if( quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative!");
        }
        this.quantity = quantity;
    }

    /**
     * Returns unique identifier of the product 
     */  
    public ProductId getId() {
        return this.id;
    }

    /**
     * Returns products name
     */ 
    public String getName() {
        return this.name;
    }

    /**
     * Returns products price
     */
    public BigDecimal getPrice() {
        return this.price;
    }

    /**
     * Returns quantity of products
     */
    public int getQuantity() {
        return this.quantity;
    }

    /**
     * Factory method that creates new (@code Product)
     * with generated id.
     */
    public static Product createNew(String name,BigDecimal price, 
            int quantity) {
        ProductId id = ProductId.createNew();  
        return new Product(id, name, price,quantity);
    }

    /**
     * Factory method that rebuilds (@code Product) from 
     * exsiting stored data after a restart
     */
    public static Product rehydrate(ProductId id,String name, 
            BigDecimal price,int quantity) {

        return new Product(id,name,price,quantity);
    }

    /**
     * Renames the product 
     * @throws IllegalArgumentException if name is null or empty
     */
    public void rename(String newName) {
         if(newName == null || newName.trim().isEmpty()){
            throw new IllegalArgumentException("Name cannot be empty");
        }
        name = newName;
    }

    /**
     * Increases stock quantity
     * @throws IllegalArgumentException if amount is less than or equal to zero
     */
    public void increaseQuantity(int amount) {
        if(amount <= 0){
           throw new IllegalArgumentException("Increase amount should be greater than zero");
        }

        quantity += amount;
    }

    /**
     * Decreases stock quantity
     * @throws IllegalArgumentException if amount is less than or equal to zero
     * or amount is greater than current stock.
     */ 
    public void decreaseQuantity(int amount){
        if(amount <= 0 || amount > quantity) {
            throw new IllegalArgumentException(
                    "Decrease amount should be greater than zero and less than or equal to the current amount"
                    );
        }

        quantity -= amount;
    }

    /**
     * Updates products price
     * @throws IllegalArgumentException if price is less than or equal to zero
     */
    public void updatePrice(BigDecimal newPrice) {
        if(newPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price should more than zero");
        }

        price = newPrice;
    }

}
