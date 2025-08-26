package com.example.inventory_dash_backend.application.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public final record RecordSaleDTO (

  String productId,
  int quantity,
  BigDecimal revenue,
  LocalDate date
) { }
