package com.service.productservice.presentation.dto;

import java.time.LocalDateTime;

public class MockProductDto {

    public record ProductSummary(
            Long id,
            String title,
            String description,
            Long price,
            boolean isReservationProduct,
            LocalDateTime openAt
    ) {
    }

    public record ProductDetail(
            Long id,
            String title,
            String description,
            Long price,
            boolean isReservationProduct,
            LocalDateTime openAt
    ) {
    }
}

