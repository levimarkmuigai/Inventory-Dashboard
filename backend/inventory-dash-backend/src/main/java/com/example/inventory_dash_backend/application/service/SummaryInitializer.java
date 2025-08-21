package com.example.inventory_dash_backend.application.service;

import com.example.inventory_dash_backend.domain.port.DailySalesSummaryRepository;
import com.example.inventory_dash_backend.domain.model.DailySalesSummary;

import java.time.LocalDate;

public final class SummaryInitializer {

    private final DailySalesSummaryRepository repo;

    public SummaryInitializer(DailySalesSummaryRepository repo){
        this.repo = repo;
    }

    public DailySalesSummary initializeForDate(LocalDate date) {
        return repo.findByDate(date).orElseGet( () -> {
            DailySalesSummary fresh = DailySalesSummary.createForDate(date);
            repo.saveOrUpdate(fresh);
            return fresh;
        });
    }
}
