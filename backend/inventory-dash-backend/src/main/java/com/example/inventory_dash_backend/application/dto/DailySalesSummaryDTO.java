package com.example.inventory_dash_backend.application.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record DailySalesSummaryDTO (

  BigDecimal totalRevenue,
  int totalQuantity,
  LocalDate date,
  List<SaleItemDTO> items
) {}
