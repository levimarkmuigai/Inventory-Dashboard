package com.example.inventory_dash_backend.domain.port;

import com.example.inventory_dash_backend.domain.model.DailySalesSummary;

import java.util.Optional;
import java.util.List;
import java.time.LocalDate;

public interface DailySalesSummaryRepository {
    Optional<DailySalesSummary> findByDate(LocalDate date);

    void saveOrUpdate(DailySalesSummary dailySalesSummary);

    List<DailySalesSummary> findAll();
}
