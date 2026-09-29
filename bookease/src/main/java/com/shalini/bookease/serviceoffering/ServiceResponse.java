package com.shalini.bookease.serviceoffering;

import java.math.BigDecimal;

public record ServiceResponse(
        Long id,
        String name,
        int durationMinutes,
        BigDecimal price
) {
}