package com.example.inventory_dash_backend.application.dto.mapper;

import java.util.Objects;

import com.example.inventory_dash_backend.application.dto.DailySalesSummaryDTO;
import com.example.inventory_dash_backend.application.dto.SaleItemDTO;
import com.example.inventory_dash_backend.domain.model.DailySalesSummary;

public final class DailySalesSummaryMapper {
  private DailySalesSummaryMapper() {
    throw new AssertionError("No instance for utility class");
  }

  public static DailySalesSummaryDTO toDto(DailySalesSummary summary) {
    Objects.requireNonNull(summary, "DailySalesSummary was expected, but was null");

    var items = summary.asItemBreakDown()
    .entrySet()
    .stream()
    .map(entry -> new SaleItemDTO(
      entry.getKey().getValue().toString(),
      entry.getValue().getQuantity(),
      entry.getValue().getRevenue()
    )).toList();

    return new DailySalesSummaryDTO( summary.getTotalRevenue(), summary.getQuantitySold(), summary.getDate(), items);
  }
}
