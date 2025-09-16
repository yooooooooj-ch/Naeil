# 🍱 내일 뭐 먹지?

**Java Swing 기반 GUI 음식점 예약 시스템**  
사용자와 사업자 간 **간편한 예약 관리**를 지원하는 데스크탑 애플리케이션입니다.

- 직관적인 화면 UI
- 사용자/관리자 분리된 기능
- 실시간 예약 및 승인 관리

<details>
<summary><strong>📸 전체 화면 미리보기</strong> (클릭하여 보기)</summary>

| 구분 | 화면 | 미리보기 |
|------|------|----------|
| 공통 | 로그인 | <img height="300" alt="image" src="https://github.com/user-attachments/assets/eb962efa-8770-4d1f-9f20-31300461ef0e" /> |
| 관리자 | 관리자 메인 | <img height="300" alt="관리자페이지" src="https://github.com/user-attachments/assets/e9390e21-fc57-40cf-93fc-450fa8b5bc49" /> |
| 관리자 | 가게 관리 | <img height="300" alt="가게관리" src="https://github.com/user-attachments/assets/7d6bd95a-baca-4028-9777-993e424b440c" /> |
| 관리자 | 메뉴 관리 | <img height="300" alt="image" src="https://github.com/user-attachments/assets/398c9fd3-d574-416d-a59c-24f3791d90af" /> |
| 관리자 | 예약 확인 | <img height="300" alt="예약확인" src="https://github.com/user-attachments/assets/060e4a7d-a70d-4855-9f5f-7a599f55feb9" /> |
| 사용자 | 유저 메인 | <img height="300" alt="image" src="https://github.com/user-attachments/assets/0c799022-c865-41a0-8a1c-3cfa72eaf60e" /> |
| 사용자 | 예약 | <img height="300" alt="image" src="https://github.com/user-attachments/assets/aa43a009-59de-40aa-adb5-64e45514bc6d" /> |
| 사용자 | 예약 확인 | <img height="300" alt="image" src="https://github.com/user-attachments/assets/69089844-d290-463c-a331-296d40e89f01" /> |

</details>

---

## 📅 개발 기간

- **2024.04.14 ~ 2024.04.28 (총 15일)**  
- **팀원 수**: 5명  
- **내 역할**: 프로젝트 총괄(팀장) / DB 설계 90% / 관리자 페이지 다수 구현

---

## 💡 기획 배경

맛집 방문 시 긴 대기시간을 줄이고, 사용자가 원하는 시간대에 식사를 예약할 수 있도록 설계한 시스템입니다.  
사업자는 손쉽게 메뉴와 예약을 관리할 수 있으며, **사용자와 사업자 모두의 편의성 향상**을 목표로 기획했습니다.

---

## 🔧 사용 기술 스택

| 구분 | 기술 |
|------|------|
| Language | Java |
| UI | Java Swing |
| DB | Oracle 11g, JDBC |
| 암호화 | SHA-256 |
| 캘린더 UI | JCalendar 라이브러리 |
| IDE | Eclipse |
| 설계 도구 | draw.io (ERD), Figma (UI 설계) |

---

## 🧩 주요 기능

### ✅ 사용자 기능
- 음식점 목록 조회 및 예약 가능 시간대 확인
- 메뉴 조회 및 선택
- 원하는 시간에 음식점 예약
- 비밀번호 암호화된 회원가입 및 로그인

### ✅ 관리자 기능
- 가게/메뉴 등록, 수정, 삭제
- 예약 승인 및 거절 기능
- 전체 예약 현황 확인

---

## 📌 맡은 역할 상세
### 🧭 프로젝트 총괄 (팀장)
- 전체 일정 수립 및 팀원별 기능 분배
- 주간 회의 주도 및 진척 상황 모니터링
- 충돌 해결 및 코드 병합 관리 (Git 수동 병합)
 
### 🗂️ DB 설계 및 백엔드 연동 (기여도 90%)
- 사용자, 가게, 메뉴, 예약 등 주요 테이블 정규화 및 설계
- 예약 상태 흐름 및 승인/거절 처리 로직 설계
- JDBC 기반 DAO/DTO 구조 설계

### 🖥 관리자 페이지 UI 및 기능 개발
- 예약 관리 페이지: 실시간 예약 내역 표시, 상태 변경 처리 (기여도 80%)
- 메뉴 관리 페이지: 가게별 메뉴 조회 및 수정 (기여도 100%)
- 가게 등록 페이지: 신규 등록 및 메뉴 연동 (기여도 70%)

### 👤 사용자 페이지 기능 개발
- 본인 예약 내역 확인 기능 구현 (기여도 80%)

---

## ✨ 프로젝트를 통해 배운 점과 느낀 점

이번 프로젝트는 단순히 기능 구현을 넘어, **팀장의 역할과 설계의 중요성**을 깊이 체감하는 계기가 되었습니다.

초반에는 "2주밖에 안 되는 개발 기간", "1차 프로젝트로 속도가 느릴 것"이라는 판단 아래, **단순한 예약 및 관리자 확인 기능 위주의 설계**를 진행했습니다. 이에 맞춰 ERD와 화면 UI, 기능 흐름을 간단히 구성했으나, 예상 외로 팀원들의 개발 속도가 빨랐고, 2~3일 만에 기본 기능이 완성되었습니다.

이후 예약 취소, 메뉴 추가/삭제, 예약 상태 관리 등 **추가 기능이 요구**되었고, 이에 따라 **DB 구조 수정, 기존 코드 수정 등 기초 설계 부족으로 인한 반복 작업이 발생**했습니다. 결과적으로 후반부 일주일 동안의 작업이 훨씬 더 빡빡하게 진행되었고, 이는 **초기 설계의 탄탄함이 프로젝트 전체의 효율과 직결된다는 점을 실감**하게 만들었습니다.

또한 팀장을 맡으면서, **팀원 간의 개발 속도 파악, 일정 조율, 이슈 대응 등 조율자의 역할이 개발 못지않게 중요**하다는 사실도 체험할 수 있었습니다.
이 경험을 통해 앞으로는 단순 기능 나열이 아닌, **기능 확장 가능성과 구조 유지보수를 고려한 설계를 우선시하겠다는 개발자로서의 태도**를 다지게 되었습니다.

---

## 🔗 관련 자료

- 📽️ [ERD 파일](https://drive.google.com/file/d/16R0uBjChg2aDMdAs_4PqOZrjyrdNen--/view?usp=sharing)
- 📊 [발표 PPT](https://docs.google.com/presentation/d/11NJRVsx3GhBFVpGjqQiJaEui0PqJejEz/edit?usp=sharing&ouid=106436189513240672230&rtpof=true&sd=true)
- ▶️ [시연 영상](https://drive.google.com/file/d/1hWBYnBIuDHO4ibnCwDXhyAO5j8Yuc1Nn/view?usp=sharing)

---
