package com.service.productservice.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

public class ProductDTO {
    @Getter
    @SuperBuilder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BaseResponse {
        private Long id;

        @JsonProperty(value = "product_name")
        private String productName;

        @JsonProperty(value = "product_info")
        private String productInfo;

        @JsonProperty(value = "product_price")
        private Long productPrice;

        @JsonProperty(value = "product_stock")
        private int productStock;

        @JsonProperty(value = "user_id")
        private Long userId;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateRequest {
        private Long id;

        @JsonProperty(value = "product_name")
        private String productName;

        @JsonProperty(value = "product_info")
        private String productInfo;

        @JsonProperty(value = "product_price")
        private Long productPrice;

        @JsonProperty(value = "product_stock")
        private int productStock;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateResponse {
        private Long id;
        @JsonProperty(value = "product_name")
        private String productName;

        @JsonProperty(value = "product_info")
        private String productInfo;

        @JsonProperty(value = "product_price")
        private Long productPrice;

        @JsonProperty(value = "product_stock")
        private int productStock;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FindRequest {
        @JsonProperty(value = "product_name")
        private String productName;

        @JsonProperty(value = "product_info")
        private String productInfo;

        @JsonProperty(value = "product_price")
        private Long productPrice;

        @JsonProperty(value = "product_stock")
        private int productStock;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FindResponse {
        private Long id;
        @JsonProperty(value = "product_name")
        private String productName;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateRequest {
        @JsonProperty(value = "product_name")
        private String productName;

        @JsonProperty(value = "product_info")
        private String productInfo;

        @JsonProperty(value = "product_price")
        private Long productPrice;

        @JsonProperty(value = "product_stock")
        private int productStock;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateResponse {

        private Long id;

        @JsonProperty(value = "product_name")
        private String productName;

        @JsonProperty(value = "product_info")
        private String productInfo;

        @JsonProperty(value = "product_price")
        private Long productPrice;

        @JsonProperty(value = "product_stock")
        private int productStock;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DeleteRequest {
        private Long id;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DeleteResponse {
        private Long id;
    }
}
