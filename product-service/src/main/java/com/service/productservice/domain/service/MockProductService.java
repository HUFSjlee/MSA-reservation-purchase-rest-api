package com.service.productservice.domain.service;

import com.service.productservice.presentation.dto.MockProductDto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MockProductService {

    public List<MockProductDto.ProductSummary> getMockProducts() {
        return List.of(
                new MockProductDto.ProductSummary(
                        1L,
                        "예약구매 상품 A",
                        "오픈 시간 이후에만 구매 가능한 상품",
                        19900L,
                        true,
                        LocalDateTime.now().withHour(14).withMinute(0).withSecond(0).withNano(0)
                ),
                new MockProductDto.ProductSummary(
                        2L,
                        "일반 상품 B",
                        "상시 구매 가능한 일반 상품",
                        9900L,
                        false,
                        null
                )
        );
    }

    public MockProductDto.ProductDetail getMockProductDetail(Long productId) {
        return getMockProducts().stream()
                .filter(product -> product.id().equals(productId))
                .findFirst()
                .map(product -> new MockProductDto.ProductDetail(
                        product.id(),
                        product.title(),
                        product.description(),
                        product.price(),
                        product.isReservationProduct(),
                        product.openAt()
                ))
                .orElseThrow(() -> new IllegalArgumentException("Not found product id: " + productId));
    }
}

