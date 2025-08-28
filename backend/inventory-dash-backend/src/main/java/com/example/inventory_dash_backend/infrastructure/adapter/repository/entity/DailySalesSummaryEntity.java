package com.example.inventory_dash_backend.infrastructure.adapter.repository.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Table(name = "daily_sales_summary")
@Entity
public class DailySalesSummaryEntity {

  @Id
  @Column(name = "summary_date", nullable = false, unique = true)
  private LocalDate date;

  @OneToMany(
    mappedBy="summary",
    cascade = CascadeType.ALL,
    orphanRemoval = true,
    fetch = FetchType.LAZY
  )
  private List<SaleEntryEntity> entries = new ArrayList<>();

  protected DailySalesSummaryEntity() {}

  public DailySalesSummaryEntity(LocalDate date) {
    this.date = Objects.requireNonNull(date, "Expected a date, but found null");
  }

  public LocalDate getDate() {
    return date;
  }

  public List<SaleEntryEntity> getEntries() {
    return entries;
  }

  public void addEntry(SaleEntryEntity entry) {
    entries.add(entry);
    entry.setSummary(this);
  }

  public void removeEntry(SaleEntryEntity entry) {
    entries.remove(entry);
    entry.setSummary(null);
  }

}
