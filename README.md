# Lottery-Research

### 소개
- 한국 로또6/45를 기반으로 로또에 대해 분석한 정보를 제공하는 SpringBoot 서버 개발 프로젝트입니다.
- [한국 로또 동행복권 링크](https://dhlottery.co.kr/common.do?method=main)

### 기능
- 로또 번호 생성
- 이전 로또 번호 기록 조회
- 로또 번호의 패턴 확인
- 로또 번호 예측

### 개발 시 예샹되는 사용 기술
- 서버 Framework: SpringBoot 2.4.5
- JDK: [openJDK 11+28](https://jdk.java.net/java-se-ri/11)
- Gradle: 6.8.3
- 로또 번호 기록을 조회할 REST API
- 로또 번호를 저장할 MongoDB
- 사용자별 생성한 로또 번호를 기억할 MariaDB - JPA
