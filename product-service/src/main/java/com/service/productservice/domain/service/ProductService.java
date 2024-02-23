package com.service.productservice.domain.service;

import com.service.productservice.common.exception.NotFoundResourceException;
import com.service.productservice.domain.entity.Product;
import com.service.productservice.infrastructure.ProductRepository;
import com.service.productservice.presentation.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final RedisTemplate<Long, Integer> redisTemplate;

    /**
     * 상품 등록
     * */
    @Transactional
    public ProductDTO.CreateResponse create(ProductDTO.CreateRequest request) {
        Product product = Product.builder()
                .productName(request.getProductName())
                .productInfo(request.getProductInfo())
                .productPrice(request.getProductPrice())
                .productStock(request.getProductStock())
                .build();

        var savedProduct  = productRepository.save(product);

        //redis db에 저장
        saveProductToRedis(savedProduct);

        return ProductDTO.CreateResponse.builder()
                .id(savedProduct.getId())
                .productName(savedProduct.getProductName())
                .productInfo(savedProduct.getProductInfo())
                .productPrice(savedProduct.getProductPrice())
                .productStock(savedProduct.getProductStock())
                .build();
    }

    private void saveProductToRedis(Product product) {
        redisTemplate.opsForValue().set(product.getId(), product.getProductStock());
    }

    /**
     * 상품 전체 조회(page)
     * */
    @Transactional
    public Page<Product> readAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    /**
     * 상품별 조회
     * */
    @Transactional
    public ProductDTO.FindResponse read(Long id) {
        Product product = productRepository.findById(id).orElseThrow(()->new NotFoundResourceException("해당 상품의 정보가 없습니다. id= " + id));

        return ProductDTO.FindResponse.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .build();
    }

    /**
     * 키워드로 상품 검색
     * */
    @Transactional
    public Page<Product> search(String keyword, Pageable pageable) {
        return productRepository.searchByProductNameContaining(keyword, pageable);
    }

    /**
     * 상품 업데이트
     * */
    @Transactional
    public ProductDTO.UpdateResponse update(Long id, ProductDTO.UpdateRequest request) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundResourceException("해당 상품의 정보가 없습니다. id= " + id));
        product.update(request.getProductName(), request.getProductInfo(), request.getProductPrice(), request.getProductStock());

        return ProductDTO.UpdateResponse.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .productInfo(product.getProductInfo())
                .productPrice(product.getProductPrice())
                .productStock(product.getProductStock())
                .build();
    }

    /**
     * 상품 삭제
     * */
    @Transactional
    public ProductDTO.DeleteResponse delete(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundResourceException("해당 상품의 정보가 없습니다. id= " + id));
        productRepository.deleteById(id);

        return ProductDTO.DeleteResponse.builder()
                .id(product.getId())
                .build();
    }
}
