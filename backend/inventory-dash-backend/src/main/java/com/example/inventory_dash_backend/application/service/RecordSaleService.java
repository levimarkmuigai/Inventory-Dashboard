package com.example.inventory_dash_backend.application.service;

import com.example.inventory_dash_backend.domain.model.DailySalesSummary;
import com.example.inventory_dash_backend.domain.port.DailySalesSummaryRepository;
import com.example.inventory_dash_backend.application.service.SummaryInitializer;

import java.util.Objects;

import com.example.inventory_dash_backend.application.command.RecordSaleCommand;

public class RecordSaleService {

  private final DailySalesSummaryRepository repo;
  private final SummaryInitializer initializer;

  public RecordSaleService(DailySalesSummaryRepository repo, SummaryInitializer initializer) {
    this.repo = Objects.requireNonNull(repo, "DailySalesSummaryRepository cannot be null");
    this.initializer = initializer;
  }
  public void record(RecordSaleCommand command) {
    DailySalesSummary summary = initializer.initializeForDate(command.getDate());

    summary.recordSale(
      command.getProductId(),
      command.getQuantity(),
      command.getRevenue()
    );

    repo.saveOrUpdate(summary);
  }
}
