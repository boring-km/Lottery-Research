# Lottery-Research

### 소개
- 한국 로또6/45를 기반으로 로또에 대해 분석한 정보를 제공하는 SpringBoot 서버 개발 프로젝트입니다.
- [한국 로또 동행복권 링크](https://dhlottery.co.kr/common.do?method=main)

### 기능
- 로또 번호 생성
- 이전 로또 번호 기록 조회
- 로또 번호의 패턴 확인
- 로또 번호 예측

### 개발 시 예샹되는 사용 기술 (Back-end)
- 서버 Framework: SpringBoot 2.4.5
- JDK: [openJDK 11+28](https://jdk.java.net/java-se-ri/11)
- Gradle: 6.8.3
- 로또 번호 기록을 조회할 REST API
- 로또 번호를 저장할 MongoDB
- 사용자별 생성한 로또 번호를 기억할 MariaDB - MyBatis(JPA도 좋지만 경험을 위해 사용함)

### 개발 시 예샹되는 사용 기술 (Front-end)
- 서버 Framework: Vue.js 2.6.11
- node 14.16.0

### React 추가 (react-front-end)
- React, TypeScript로 기존의 Vue.js 서버와 동일하게 만들어보고, 완성이 되면 교체하기

### 한국 로또 6/45 데이터 제공
- https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo=회차번호
