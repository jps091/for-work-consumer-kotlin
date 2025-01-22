# 📑 for-work - RabbitMQ Consumer 서버 (Kotlin_Ver)
for-work 서버의 메일 전송을 담당하는 CONSUMER 서버 입니다. 

프로젝트 관련 회고 및 트러블 슈팅은 [여기](https://github.com/jps091/for-work/wiki/%EC%84%9C%EB%B9%84%EC%8A%A4%EC%9D%98-%ED%95%B5%EC%8B%AC-%EA%B8%B0%EB%8A%A5%EC%9D%B8-%EB%A9%94%EC%9D%BC-%EB%B0%9C%EC%86%A1-%EA%B8%B0%EB%8A%A5-%EB%B9%84%EB%8F%99%EA%B8%B0-%EA%B4%80%EB%A0%A8-%EC%95%84%ED%82%A4%ED%85%8D%EC%B2%98-%EA%B3%A0%EB%AF%BC)를 클릭 해주세요. 

---

## 📧 주요 매일 기능

### forwork 서비스에서 메일 전송을 이용하는 서비스는 아래와 같습니다.

1. 회원 가입 이메일 인증 코드 발송
2. 회원 가입 시 공지사항 및 사이트 이용방법
3. 이력서 판매 요청 결과 발송
4. 고객센터 문의
5. 구매자가 이력서 구매 시 해당 이력서 메일로 전송

---

## 🏇 Exchange - Queue 구성
<img width="800" height="400" alt="image" src="https://github.com/user-attachments/assets/e2539dd1-3ebe-455d-9487-77c4ece1ad29" />

---

## 🚴 최종 동작 흐름 
<img width="1000" height="450" src="https://github.com/user-attachments/assets/e454b6d0-0356-45fa-9e89-5bbb3a227216">


