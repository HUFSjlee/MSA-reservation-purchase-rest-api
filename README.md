# 📌SNS 기반의 예약 구매 서비스 (with Micro-Service Architecture)

## :one: 기술 스택

   &emsp;<img  src="https://img.shields.io/badge/java 17-007396?style=flat-square&logo=java&logoColor=white"> <img  src="https://img.shields.io/badge/springboot 3.2.2-6DB33F?style=flat-square&logo=springboot&logoColor=white">
   <img  src="https://img.shields.io/badge/Spring Data JPA-20C997?style=flat-square&logo=Spring Data JPA&logoColor=white">
   <img src="https://img.shields.io/badge/Docker-2496ED?style=flat-square&logo=Docker&logoColor=white"/>
   <img src="https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=MySQL&logoColor=white"/>
   <img  src="https://img.shields.io/badge/redis-CC0000?style=flat-square&logo=redis&logoColor=white">
   <img  src="https://img.shields.io/badge/git-F05032?style=flat-square&logo=git&logoColor=white">
   <img  src="https://img.shields.io/badge/swagger-85EA2D?style=flat-square&logo=Swagger&logoColor=black">
   <img  src="https://img.shields.io/badge/Intellij-000000?style=flat-square&logo=Intellij IDEA&logoColor=white">


* ***Back-End*** : Java 17, Spring Boot 3.2.2, Spring Security (with JWT)  
* ***ORM***  : Spring Data JPA  
* ***Database*** : MySQL8.0, Redis  
* ***Infra*** : Docker  
* ***REST API Documentation***  : Swagger  
* ***Tools***: IntelliJ, Git
<br>

## :two: 프로젝트 개요

 * **기간**
   
    | 개발기간 | 2024.01 ~ 2024.02 (4주) |
    |---------|-------------------------|

* **인원**
  
    | 인원 | 1인 (개인) |
    |---------|-------------------------|

* **기획 의도**

  기존에 만들었던 sns 프로젝트에 예약,구매 서비스를에 추가하는 과정에서 모놀리식 구조의 프로젝트를 마이크로서비스 아키텍쳐로 변경하고자 했습니다. 

* **목표**
  
  단일 서비스를 마이크로서비스로 분리하고, 각 마이크로서비스 간의 통신 및 데이터베이스 연동을 구현
  
  API GATEWAY 도입 - 일련의 마이크로서비스에 액세스할 수 있는 통합된 진입점을 제공
<br>
<br>

## :three: 프로젝트 소개
### 구현 요구사항
<br>
  
  **:large_blue_circle: Docker를 통한 로컬 개발 환경 구축**
  
  * ***docker-compose.yml*** 파일을 작성하여 각 마이크로서비스 컴포넌트 및 필수 인프라스트럭처(데이터베이스)를 정의
     
  * ***docker-compose.yml*** 파일을 통해 서비스 간의 통신이나 외부 의존성을 관리

  **:large_blue_circle: 기존 모놀리식 구조의 프로젝트를 마이크로서비스 구조로 분리**

  * 멀티 모듈 구조로 프로젝트를 구성하고, 각 마이크로서비스의 독립성 보장

  **:large_blue_circle: 모노리스 서비스를 마이크로 서비스로 나누기**
  
  **:large_blue_circle: API Gateway의 도입**
  
  **:large_blue_circle: 특정 시간에 구매버튼이 활성화 되는 예약구매 시스템 (결제 방법, 가격, 배송지 입력 등은 크게 신경쓰지 않았습니다)**
     ![(4)결제 프로세스 요구사항](https://github.com/HUFSjlee/MSA-reservation-purchase-rest-api/assets/67497759/84406405-a39b-4bbe-97b9-44a9ac0cfe81)

  **:large_blue_circle: 실시간 재고 관리 서비스를 만들어서 내부 통신을 통해 재고만 반환**
<br>
<br>
<br>
<hr style="border: 2px solid grey;">

### 구현 내용  
<br>

  **:heavy_check_mark: 마이크로서비스 간의 통신 및 데이터베이스 연동 구현**
  
  **:heavy_check_mark: user-service / activities-service / newsfeed-service 각 모듈의 settings.gradle 및 build.gradle을 활용해 멀티 모듈 프로젝트 구조 변경 구현**
  
  **:heavy_check_mark: 3개의 모노리스 서비스를 3개의 마이크로 서비스로 만들기**
     ![image](https://github.com/HUFSjlee/MSA-reservation-purchase-rest-api/assets/67497759/cbf338d8-fc4d-4057-bc6d-679833a00f07)
  
  **:heavy_check_mark: 각 모듈의 역할에 맞는 도메인 코드만 그대로 두고 다른 도메인 관련 코드 제거**
  <br>
  
  **:heavy_check_mark: 3개의 마이크로 서비스가 서로 통신할 수 있도록 구현**
     ![(2)msa 구조 서로 통신](https://github.com/HUFSjlee/MSA-reservation-purchase-rest-api/assets/67497759/f43d7a74-86ad-4b37-8ba9-ad06eab20b54)
  
  **:heavy_check_mark: RestTemplate을 사용해서 API Gateway 만들기**
  * API GATEWAY 서비스를 새로 만들어 실행(port: 8083)
  * 기존의 모노리스 서비스가 가지고 있던 모든 API 들을 API GATEWAY에 노출
  * 해당 API로 들어온 요청을, 내부의 마이크로 서비스로 전달
     ![(3)api-gateway](https://github.com/HUFSjlee/MSA-reservation-purchase-rest-api/assets/67497759/4962b8d5-f3af-4ebc-bfce-6f3ed4de5f69)
  
  **:heavy_check_mark: 쇼핑몰 목업 구현**

  * 상품 목록 API 구현
  * 상품 상세 페이지 API 구현
  * 남은 수량 API 구현  
  * 결제 진입 API 구현
  * 결제 API 구현
<br>

## :four: 프로젝트 구조

   ```
   ├─MSA_ReservationPurchase
   │  ├─ docker  
   │  │  ├─ docker-compose.yml  
   │  ├─ api-gateway  
   │  ├─ eureka-server  
   |  ├─ user-service [module]  
   |  ├─ activities-service [module]  
   |  ├─ newsfeed-service [module]  
   |  ├─ product-service [module]  
   |  ├─ stock-service [module]  
   |  ├─ order-service [module]
   ```


## :five: 서비스 아키텍처
![MSA_Architecture](https://github.com/HUFSjlee/MSA-reservation-purchase-rest-api/assets/67497759/42670ada-46f0-46c1-afac-fbc664f33d20)

## :six: ERD
<img src="https://github.com/HUFSjlee/MSA-reservation-purchase-rest-api/assets/67497759/d81a483f-d211-4c4d-b0a7-feedf7a51397" alt="ERD" width="850" height="450">
<hr style="border: 2px solid grey;">

## docker-compose 명령어
```
> Docker 컨테이너 올리기 
> docker-compose up

> Docker 컨테이너 내리기 
> docker-compose down

> Docker 컨테이너 시작
> docker-compose start

> Docker 컨테이너 종료
> docker-compose stop
```

