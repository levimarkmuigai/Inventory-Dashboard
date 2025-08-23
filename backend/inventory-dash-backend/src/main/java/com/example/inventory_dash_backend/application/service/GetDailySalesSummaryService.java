package com.example.inventory_dash_backend.application.service;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

import com.example.inventory_dash_backend.domain.port.DailySalesSummaryRepository;
import com.example.inventory_dash_backend.domain.model.DailySalesSummary;

public final class GetDailySalesSummaryService {

  private final DailySalesSummaryRepository repo;

  public GetDailySalesSummaryService(DailySalesSummaryRepository repo) {
    this.repo = Objects.requireNonNull(repo, "Daily Sales Summary Repository cannot be null");
  }

  public Optional<DailySalesSummary> getForDate(LocalDate date) {
    Objects.requireNonNull(date, "Date cannot be null");
    if(date.isAfter(LocalDate.now())) {
      throw new IllegalArgumentException("The date provided " + date + " is invalid");
    }
    return repo.findByDate(date);
  }
}
