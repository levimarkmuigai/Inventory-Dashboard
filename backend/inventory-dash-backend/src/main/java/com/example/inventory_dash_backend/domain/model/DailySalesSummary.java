package com.example.inventory_dash_backend.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;
import java.util.Objects;

public class DailySalesSummary {
   
    private final LocalDate date;

    private final Map<ProductId, SaleEntry> saleEntries;

    private DailySalesSummary(LocalDate date,Map<ProductId, SaleEntry> saleEntries){
        
        this.date = Objects.requireNonNull(date, "Date cannot be null");
        this.saleEntries  = new HashMap<>(saleEntries);
    }

    public static DailySalesSummary createForDate(LocalDate date) {
        return new DailySalesSummary(date, new HashMap<>());
    }

    public void recordSale(ProductId productId, int quantity, BigDecimal revenue) {

        if(saleEntries.containsKey(productId)){
            SaleEntry current = saleEntries.get(productId);
            saleEntries.put(productId,current.aggregate(quantity, revenue));
        } else {
            saleEntries.put(productId, SaleEntry.createNew(quantity, revenue));
        }
    }

    public BigDecimal getTotalRevenue() {
       BigDecimal sumOfRevenue = saleEntries.values()
           .stream()
           .map(SaleEntry::getTotalRevenue)
           .reduce(BigDecimal.ZERO, BigDecimal::add);

       return sumOfRevenue;
    }

    public int getQuantitySold() {
        return saleEntries.values()
            .stream()
            .mapToInt(SaleEntry::getQuantitySold)
            .sum();
    }

    public Map<ProductId, SaleEntry> getSaleEntries() {
        return Collections.unmodifiableMap(saleEntries);
    }

    static final class SaleEntry {

        private final int quantitySold;
        private final BigDecimal totalRevenue;

        SaleEntry(int quantitySold, BigDecimal totalRevenue) {

            if(quantitySold <= 0) { 
                throw new IllegalArgumentException("Quantity sold should be more than zero.");
            }
            this.quantitySold = quantitySold;

            if( totalRevenue.compareTo(BigDecimal.ZERO) <= 0 ){
                throw new IllegalArgumentException("Total revenue should be more than zero");
            }
            this.totalRevenue = totalRevenue;
 
        }

        public static SaleEntry createNew(int quantitySold, BigDecimal totalRevenue) {
            return new SaleEntry(quantitySold, totalRevenue);
        }

        public SaleEntry aggregate(int addSoldStock, BigDecimal addRevenue) {
             if(addSoldStock <= 0) { 
                throw new IllegalArgumentException("Stock sold should be more than zero.");
            }

            if( addRevenue.compareTo(BigDecimal.ZERO) <= 0 ){
                throw new IllegalArgumentException("Added revenue should be more than zero");
            }

            return new SaleEntry( 
                    this.quantitySold + addSoldStock, 
                    totalRevenue.add(addRevenue)
                    );
        }
        
        @Override
        public String toString() {
            return "SaleEntry{" +  
                ", quantitySold="  + quantitySold  + 
                ", totalRevenue="  + totalRevenue  +
                '}'; 
        }

        public BigDecimal getTotalRevenue() { return totalRevenue; }

        public int getQuantitySold() {return quantitySold; }

    }
}
