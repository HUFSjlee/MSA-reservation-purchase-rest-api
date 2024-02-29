package com.service.stockservice.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

public class StockDto {
    @Getter
    @SuperBuilder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateRequest {
        private Long id;
        @JsonProperty(value = "product_id")
        private Long productId;
        @JsonProperty(value = "stock")
        private int stock;
    }

    @Getter
    @SuperBuilder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateResponse {
        private Long id;
        @JsonProperty(value = "product_id")
        private Long productId;
        @JsonProperty(value = "stock")
        private int stock;
    }
}
