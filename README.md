# 📌SNS 기반의 예약 구매 서비스 (with Micro-Service Architecture)

## :one: 기술스택

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

  1. 단일 서비스를 마이크로서비스로 분리하고, 각 마이크로서비스 간의 통신 및 데이터베이스 연동을 구현   
  2. API GATEWAY 도입
<br>
<br>

## :three: 프로젝트 소개
### 목적
xx입니다.
### 기능!
xx입니다.
### 프로젝트 구조

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


## :four: 서비스 아키텍처
![MSA_Architecture](https://github.com/HUFSjlee/MSA-reservation-purchase-rest-api/assets/67497759/42670ada-46f0-46c1-afac-fbc664f33d20)

## :five: ERD
<img src="https://github.com/HUFSjlee/MSA-reservation-purchase-rest-api/assets/67497759/d81a483f-d211-4c4d-b0a7-feedf7a51397" alt="ERD" width="850" height="450">
<hr style="border: 2px solid grey;">

## docker-compose 명령어
> **Docker 컨테이너 올리기**
> 
> docker-compose up

> **Docker 컨테이너 내리기**
> 
> docker-compose down

> **Docker 컨테이너 시작**
> 
> docker-compose start

> **Docker 컨테이너 정지**
> 
> docker-compose stop


