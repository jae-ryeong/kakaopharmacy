# KakaoPharmacy
KakaoPharmacy는 Kakao OpenAPI를 사용하여 근처 약국 위치 조회 서비스를 지원하는 웹 애플리케이션입니다.
사용자는 주소를 입력하면 Harversine formula 공식을 적용하여 근처 10km 이내 약국 중 가장 가까운 3곳을 조회해줍니다.
그 후 약국의 정확한 위치 확인을 위한 로드뷰 링크 또한 제공합니다.

## E-R 다이어그램

## 기술 스택

### 프론트엔드
- Thymeleaf

### 백엔드
- Spring Boot
- Redis
- MariaDB

### DevOps
- Docker (Redis, MariaDB 컨테이너화)

## 주요 기능
- **사용자 인증**: 회원가입 및 로그인 (JWT 기반 인증)
- **약국 검색**: Kakao API를 활용한 약국 위치 조회
