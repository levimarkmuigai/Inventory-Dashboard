package com.example.inventory_dash_backend.infrastructure.adapter.repository.entity;

import java.math.BigDecimal;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "sale_entry")
public class SaleEntryEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "product_id", nullable = false)
  private String productId;

  @Column(nullable = false)
  private int quantity;

  @Column(nullable = false, precision = 19, scale = 2)
  private BigDecimal revenue;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn( name = "summary_date", nullable = false)
  private DailySalesSummaryEntity summary;

  protected SaleEntryEntity() {}

  public SaleEntryEntity(String productId, int quantity, BigDecimal revenue) {
    this.productId = Objects.requireNonNull(productId, "Product Id must not be null.");
    this.quantity = quantity;
    this.revenue = Objects.requireNonNull(revenue, "Revenue must not be null");
  }

  public Long getId() { return id; }
  public String getProductId() { return productId; }
  public int getQuantity() { return quantity; }
  public BigDecimal getRevenue() { return revenue; }

  public void setSummary(DailySalesSummaryEntity summary) { this.summary = summary; }
}
