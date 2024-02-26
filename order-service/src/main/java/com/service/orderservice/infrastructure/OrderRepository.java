package com.service.orderservice.infrastructure;

import com.service.orderservice.domain.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> searchByOrderIdContaining(Long orderId, Pageable pageable);
}
