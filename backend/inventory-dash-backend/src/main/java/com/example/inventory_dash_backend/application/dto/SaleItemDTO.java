package com.example.inventory_dash_backend.application.dto;

import java.math.BigDecimal;

public record SaleItemDTO(
  String productId,
  int totalQuantity,
  BigDecimal totalRevenue
) {}
