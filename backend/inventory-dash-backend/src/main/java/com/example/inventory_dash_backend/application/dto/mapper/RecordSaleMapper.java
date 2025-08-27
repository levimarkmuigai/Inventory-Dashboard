package com.example.inventory_dash_backend.application.dto.mapper;

import java.util.Objects;

import com.example.inventory_dash_backend.application.command.RecordSaleCommand;
import com.example.inventory_dash_backend.application.dto.RecordSaleDTO;
import com.example.inventory_dash_backend.domain.model.ProductId;

public final class RecordSaleMapper {

  private RecordSaleMapper() { throw new AssertionError("No instance for utility class");}

  public static RecordSaleCommand toCommand(RecordSaleDTO dto) {
    Objects.requireNonNull(dto, "RecordSaleDTO was expected, but was null");

    return RecordSaleCommand.createWithDate(
      ProductId.fromString(dto.productId()),
      dto.quantity(),
      dto.revenue(),
      dto.date()
    );
  }
}
