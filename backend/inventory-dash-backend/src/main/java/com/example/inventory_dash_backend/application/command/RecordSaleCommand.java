package com.example.inventory_dash_backend.application.command;

import com.example.inventory_dash_backend.domain.model.ProductId;

import java.time.LocalDate;
import java.math.BigDecimal;
import java.util.Objects;

public final class RecordSaleCommand {
    private final ProductId productId;
    private final int quantity;
    private final BigDecimal revenue;
    private final LocalDate date;

    private RecordSaleCommand(ProductId productId, int quantity, 
            BigDecimal revenue, LocalDate date){
       this.productId = Objects.requireNonNull(productId, "Product id cannot be null");
       
       if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be more than zero." + quantity);
        }
       this.quantity = quantity;
       
       this.revenue =  Objects.requireNonNull(revenue, "Revenue cannot be null.");
       if (revenue.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Revenue should be more than zero" + revenue);
       }

       this.date = Objects.requireNonNull(date, "Date cannot be null.");
       if (date.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Date cannot be in the future.");
        } 
    }

    public static RecordSaleCommand createForToday(ProductId productId, int quantity, 
            BigDecimal revenue) {
        return new RecordSaleCommand(productId, quantity, revenue, LocalDate.now());
    }

    public static RecordSaleCommand createWithDate(ProductId productId, int quantity,
            BigDecimal revenue, LocalDate date) {
        return new RecordSaleCommand(productId, quantity, revenue, date);
    }

    public ProductId getProductId() { return this.productId; }

    public int getQuantity() { return this.quantity; }

    public BigDecimal getRevenue() { return this.revenue; }

    public LocalDate getDate() { return this.date; }

    @Override
    public String toString() {
        return  "{" + "\n"  
            +"productId=" + productId + "," +"\n"
            +"quantity=" + quantity + "," + "\n"
            +"revenue=" + revenue + "," + "\n"
            + "date=" + date + "\n"
            +"}";
                
    }
}
