package com.example.inventory_dash_backend.application.service;

import java.util.List;
import java.util.Objects;

import com.example.inventory_dash_backend.domain.model.DailySalesSummary;
import com.example.inventory_dash_backend.domain.port.DailySalesSummaryRepository;

public final class GetAllSalesSummariesService {

  private final DailySalesSummaryRepository repo;

  public GetAllSalesSummariesService(DailySalesSummaryRepository repo) {
    this.repo = Objects.requireNonNull(repo, "Daily Sales Summary Repository cannot be null");
  }

  public List<DailySalesSummary> getAllSalesSummaries(){
    return repo.findAll();
  }
}
