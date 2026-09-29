package com.shalini.bookease.serviceoffering;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record ServiceRequest(
        @NotBlank String name,
        @Min(5) int durationMinutes,
        @NotNull @Positive BigDecimal price
) {
}