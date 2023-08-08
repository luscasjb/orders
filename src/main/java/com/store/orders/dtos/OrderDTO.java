package com.store.orders.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.store.orders.enums.StatusOrder;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class OrderDTO {
    Long id;
    @NotNull
    BigDecimal vrOrder;
    @NotNull
    StatusOrder statusOrder;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    LocalDateTime createdAt;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    LocalDateTime updatedAt;
}
