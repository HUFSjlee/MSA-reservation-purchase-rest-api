package com.service.productservice.domain.entity;

import com.service.productservice.common.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@NoArgsConstructor
@Getter
@Table(name = "product")
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_info")
    private String productInfo;

    @Column(name = "product_price")
    private Long productPrice;

    @Column(name = "product_stock")
    private int productStock;

    public void update(String productName, String productInfo, Long productPrice, int productStock) {
        this.productName = productName;
        this.productInfo = productInfo;
        this.productPrice = productPrice;
        this.productStock = productStock;
    }
}
