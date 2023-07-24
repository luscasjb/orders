package com.store.orders.dtos;

import com.store.orders.enums.StatusOrder;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record OrderRecordDto(
        @NotNull BigDecimal valueOrder,
        @NotNull StatusOrder statusOrder) {
}
