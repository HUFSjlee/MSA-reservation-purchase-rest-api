package com.service.orderservice.domain.service;

import com.service.orderservice.common.exception.NotFoundResourceException;
import com.service.orderservice.domain.entity.Order;
import com.service.orderservice.infrastructure.OrderRepository;
import com.service.orderservice.presentation.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final RedisTemplate<Long, Integer> redisTemplate;

    /**
     * 주문 등록
     * */
    @Transactional
    public OrderDto.CreateResponse create(OrderDto.CreateRequest request, Long userId) {

        Order order = Order.builder()
                .userId(userId)
                .productId(request.getProductId())
                .quantity(request.getQuantity())
                .orderStatus(request.getOrderStatus())
                .build();

        var savedOrder = orderRepository.save(order);

        //redis db에 수량 감소
        updateProductStockInRedis(savedOrder.getProductId(), savedOrder.getQuantity());

        return OrderDto.CreateResponse.builder()
                .id(savedOrder.getId())
                .userId(savedOrder.getUserId())
                .productId(savedOrder.getProductId())
                .quantity(savedOrder.getQuantity())
                .orderStatus(savedOrder.getOrderStatus())
                .build();
    }
    public void updateProductStockInRedis(Long productId, Integer quantity) {
        Integer currentStock = redisTemplate.opsForValue().get(productId);
        if(currentStock != null) {
            redisTemplate.opsForValue().set(productId, currentStock-quantity);
        } else {
            throw new NotFoundResourceException("상품 재고를 찾을 수 없습니다. 상품 ID: " + productId);
        }
    }

    /**
     * 주문 전체 조회
     * */
    @Transactional
    public Page<Order> readAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }


    /**
     * 주문별 조회
     * */
    @Transactional
    public OrderDto.FindResponse read(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(()->new NotFoundResourceException("해당 주문 정보가 없습니다. id= " + id));

        return OrderDto.FindResponse.builder()
                .id(order.getId())
                .userId(order.getUserId())
                .productId(order.getProductId())
                .quantity(order.getQuantity())
                .orderStatus(order.getOrderStatus())
                .build();
    }

    @Transactional
    public Page<Order> search(Long orderId, Pageable pageable) {
        return orderRepository.searchByOrderIdContaining(orderId, pageable);
    }

    /**
     * 주문 삭제
     * */
    @Transactional
    public OrderDto.DeleteResponse delete(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new NotFoundResourceException("해당 주문 정보가 없습니다. id= " + id));
        updateProductStockInRedis(order.getProductId(), order.getQuantity());
        orderRepository.deleteById(id);

        return OrderDto.DeleteResponse.builder()
                .id(order.getId())
                .build();
    }
}
