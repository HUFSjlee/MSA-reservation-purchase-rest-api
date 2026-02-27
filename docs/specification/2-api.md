# 쇼핑몰 기능 API 정리

## 상품 API

### `GET /products/mock`
- 목적: 목업 상품 목록 조회
- 응답 필드:
- `id`: 상품 ID
- `title`: 상품명
- `description`: 상품 설명
- `price`: 가격
- `isReservationProduct`: 예약구매 상품 여부
- `openAt`: 예약구매 오픈 시각(일반 상품은 `null`)

### `GET /products/mock/{productId}`
- 목적: 상품 상세 조회
- 응답 필드:
- `id`: 상품 ID
- `title`: 상품명
- `description`: 상세 설명
- `price`: 가격
- `isReservationProduct`: 예약구매 상품 여부
- `openAt`: 오픈 시각

## 재고 API

### `GET /stocks/mock/{productId}/remaining`
- 목적: 남은 수량 조회
- 응답 필드:
- `productId`: 상품 ID
- `remainingStock`: 현재 상품페이지 표기용 남은 수량

## 결제/주문 API

### `POST /orders/mock/payment-entry`
- 목적: 결제 화면 진입 처리
- 요청 필드:
- `userId`: 사용자 ID
- `productId`: 상품 ID
- 응답 필드:
- `paymentSessionId`: 결제 세션 ID
- `userId`: 사용자 ID
- `productId`: 상품 ID
- `remainingStock`: 결제 진입 후 남은 수량

### `POST /orders/mock/payment`
- 목적: 결제 시도 처리 (20% 고객 귀책 실패 시뮬레이션)
- 요청 필드:
- `paymentSessionId`: 결제 세션 ID
- 응답 필드:
- `paymentSessionId`: 결제 세션 ID
- `success`: 결제 성공 여부
- `reason`: 결과 코드 (`COMPLETED` 또는 `CUSTOMER_CANCELLED`)
- `orderId`: 성공 시 생성된 주문 ID
- `userId`: 사용자 ID
- `productId`: 상품 ID
- `remainingStock`: 처리 후 남은 수량

### `GET /orders/mock/{orderId}`
- 목적: 주문 정보 조회
- 응답 필드:
- `orderId`: 주문 ID
- `userId`: 사용자 ID
- `productId`: 상품 ID
- `quantity`: 주문 수량
- `orderStatus`: 주문 상태

