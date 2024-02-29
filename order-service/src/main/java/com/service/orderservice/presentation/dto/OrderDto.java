package com.service.orderservice.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.service.orderservice.domain.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

public class OrderDto {
    @Getter
    @SuperBuilder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BaseResponse {
        private Long id;

        @JsonProperty(value = "user_id")
        private Long userId;

        @JsonProperty(value = "product_id")
        private Long productId;

        @JsonProperty(value = "quantity")
        private int quantity;

        @JsonProperty(value = "orderStatus")
        private OrderStatus orderStatus;
    }

    @Getter
    @SuperBuilder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateRequest {
        private Long id;

        @JsonProperty(value = "user_id")
        private Long userId;

        @JsonProperty(value = "product_id")
        private Long productId;

        @JsonProperty(value = "quantity")
        private int quantity;

        @JsonProperty(value = "orderStatus")
        private OrderStatus orderStatus;
    }
    @Getter
    @SuperBuilder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateResponse {
        private Long id;

        @JsonProperty(value = "user_id")
        private Long userId;

        @JsonProperty(value = "product_id")
        private Long productId;

        @JsonProperty(value = "quantity")
        private int quantity;

        @JsonProperty(value = "orderStatus")
        private OrderStatus orderStatus;
    }

    @Getter
    @SuperBuilder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FindRequest {
        private Long id;

        @JsonProperty(value = "user_id")
        private Long userId;

        @JsonProperty(value = "product_id")
        private Long productId;

        @JsonProperty(value = "quantity")
        private int quantity;

        @JsonProperty(value = "orderStatus")
        private OrderStatus orderStatus;
    }
    @Getter
    @SuperBuilder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FindResponse {
        private Long id;

        @JsonProperty(value = "user_id")
        private Long userId;

        @JsonProperty(value = "product_id")
        private Long productId;

        @JsonProperty(value = "quantity")
        private int quantity;

        @JsonProperty(value = "orderStatus")
        private OrderStatus orderStatus;
    }
    @Getter
    @SuperBuilder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DeleteRequest {
        private Long id;

        @JsonProperty(value = "user_id")
        private Long userId;

        @JsonProperty(value = "product_id")
        private Long productId;

        @JsonProperty(value = "quantity")
        private int quantity;

        @JsonProperty(value = "orderStatus")
        private OrderStatus orderStatus;
    }
    @Getter
    @SuperBuilder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DeleteResponse {
        private Long id;

        @JsonProperty(value = "user_id")
        private Long userId;

        @JsonProperty(value = "product_id")
        private Long productId;

        @JsonProperty(value = "quantity")
        private int quantity;

        @JsonProperty(value = "orderStatus")
        private OrderStatus orderStatus;
    }
}
