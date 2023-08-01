<div align="center">

<img alt="MuDuck logo" src="https://user-images.githubusercontent.com/75026933/228852694-520fc7bd-80d3-4b56-9b3d-cd3b71ef579b.png">

**`팀 명` :** MuDuck </br>
**`프로젝트 명` :** MuDuck </br>
**`프로젝트 기간` :** 2023.03.03 ~ 2023.04.03 </br>
**`배포 링크` :** [MuDuck](http://muduckbucket.s3-website.ap-northeast-2.amazonaws.com) </br>
**`서비스 메뉴얼` :** [MuDuck 서비스 메뉴얼](https://dev-taehee.github.io/MuDuckServiceManual/) </br>

</div>

# 목차 </br>
[팀 구성원 소개](#-team-) </br>
[팀원 역할 소개](#-responsibility-) </br>
[기술 스택](#%EF%B8%8F-tech-stack-%EF%B8%8F) </br>
[Git commit 컨벤션](#%EF%B8%8F-commit-message-%EF%B8%8F) </br>
[협업 문서](#협업-문서) </br>

## 👍 Team 👍
<div align="center">

| <a href="https://github.com/TaeheeWoo94" target="_blank">우태희</a> <br>(BE, 팀장) | <a href="https://github.com/kkte02" target="_blank">김관우</a> <br>(BE, 팀원) | <a href="https://github.com/DerekYook" target="_blank">육경득</a> <br>(BE, 팀원) | <a href="https://github.com/Paksubeen" target="_blank">박수빈</a> <br>(FE, 부팀장) | <a href="https://github.com/sleepy-joyy" target="_blank">이승연</a> <br>(FE, 팀원) | <a href="https://github.com/LeeHyoGeun-create" target="_blank">이효근</a> <br>(FE, 팀원) |
| :---: | :---: | :---: | :---: | :---: | :---: |
| <img alt="우태희" src="https://user-images.githubusercontent.com/75026933/229016459-4921b1bd-43ed-4277-8e66-138000e612f2.jpg" height="100" width="100"> | <img alt="김관우" src="https://user-images.githubusercontent.com/75026933/229016226-74537d22-5c6c-4a2a-b79a-7bfd7a95fe78.png" height="100" width="100"> | <img alt="육경득" src="https://user-images.githubusercontent.com/75026933/229016373-00bacb0a-3dd8-4de1-bf68-1a0788dec95b.png" height="100" width="100"> | <img alt="박수빈" src="https://user-images.githubusercontent.com/75026933/229016135-6877be77-15be-4618-bcc7-1be0a9e8c61b.JPG" height="100" width="100"> | <img alt="이승연" src="https://user-images.githubusercontent.com/75026933/229012091-250cbe86-5def-41c1-8ddc-283280ff0c82.JPG" height="100" width="100"> | <img alt="이효근" src="https://user-images.githubusercontent.com/75026933/229016903-296fbf12-26fb-4934-823c-9c4d75c1b1d9.png" height="100" width="100"> |

</div>

## 💻 Responsibility 💻
<details> 

<summary> 
우태희(Back-End, 팀장)
</summary>

* AWS 환경설정
* 게시글 CRUD 구현
* 게시글 목록 GET 요청 구현
* 마이페이지 회원이 작성한 글 및 댓글 가져오기 구현

</details>

<details> 

<summary> 
김관우(Back-End, 팀원)
</summary>

* 주변시설 CRUD 관련 API 구현
* OAuth2를 이용한 카카오 소셜로그인 구현
* SpringSecurity와 Jwt를 이용한 회원 인증/인가 구현
* SpringSecurity를 이용한 Jwt 검증 Filter, JwtException Filter 구현

</details>

<details> 

<summary> 
육경득(Back-End, 팀원)
</summary>

* ERD 설계<br/>
* 작품과 게시판 연동 구현
* 작품과 지도 정보 연동 구현
* 작품 및 배우 조회 관련 기능 구현

</details> 

<details> 

<summary> 
박수빈(Front-End, 부팀장)
</summary>

* 메인페이지
* 뮤지컬 목록 페이지
  * 정렬/상태/장르에 따른 목록 필터링 기능 구현
* 커뮤니티 상세 페이지
  * 게시글 조회/좋아요 및 댓글 등록/삭제 기능 구현
* 마이페이지
  * 사용자가 작성/좋아요한 글과 댓글 조회 기능 구현

</details>

<details>

<summary> 
이승연(Front-End, 팀원)
</summary>

* 헤더 반응형 디자인
  * 모바일 햄버거 메뉴 기능 구현
* 로그인 페이지
  * 카카오 OAuth 로그인 기능 구현
* 회원정보 설정 페이지
  * 신규 회원 프로필 이미지 S3 업로드 기능 구현
* 뮤지컬 상세 페이지
  * 공연 정보 및 배우 정보 상세 더보기 기능 구현

</details> 

<details>

<summary> 
이효근(Front-End, 팀원)
</summary>

* 커뮤니티 페이지
  * 커뮤니티 게시글 작성 및 수정 기능 구현
* 커뮤니티 목록 페이지
  * 정렬/카테고리에 따른 목록 필터링 기능 구현
* 주변시설 페이지
  * Kakao Maps Api를 이용한 지도 기능 구현
  * 지도에서 정보 받아와 리뷰 작성 및 수정 기능 구현

</details>

## 🛠️ Tech Stack 🛠️
<img alt="Tech Stack" src="https://user-images.githubusercontent.com/75026933/229709841-6de541e9-f934-4daa-b1d4-f7d949e1fc07.png">

## ✉️ Commit Message ✉️
| Message | Description |
| :---: | :--- |
| Feat | 새로운 기능 추가 |
| Fix | 버그 수정 |
| Docs | 문서 수정 |
| Style | 코드 포맷팅, 세미콜론 누락, 코드 변경이 없는 경우 |
| Refactor | 코드 리팩토링 |
| Test |테스트 코드, 리팩토링 테스트 코드 추가 |
| Chore | 패키지 매니저 수정, 그 외 기타 수정 |
| Design | CSS 등 사용자 UI 디자인 변경 |
| Rename | 파일 또는 폴더 명을 수정하거나 옮기는 작업만인 경우 |
| Remove | 파일을 삭제하는 작업만 수행한 경우 |

## 협업 문서
협업을 진행하며 작성한 문서에 대해 소개합니다.

### 공통
**[사용자 요구사항 정의서](https://docs.google.com/spreadsheets/d/1bNZKVynTeONAMEfzaQmDYdRN2oVqpS4zSJmVzwl9-hM/edit#gid=7093886)** </br>
MuDuck 프로젝트를 하기 위해 필요한 기능들을 사용자 요구사항 정의서 양식으로 정리하였습니다. </br>

### Frontend
**[화면정의서 피그마](https://www.figma.com/file/oeINMS5daaqlyCYktWa2TY/main17_%ED%99%94%EB%A9%B4%EC%A0%95%EC%9D%98%EC%84%9C_draft?type=design&mode=design)** </br>
화면 구성 요소를 피그마로 만들어 팀원들과 공유하였습니다. </br>

**[화면정의서 문서](https://docs.google.com/document/d/19TsxgloRS1Fx07A5_37lhruSxvCGxQCCnu7UmVZvSRI/edit)** </br>
각 화면의 의도를 문서로 정리하였습니다. </br>

### Backend
**[테이블 명세서](https://docs.google.com/spreadsheets/d/1FUOGrFqIFrTYgi7aaXpFmI2_NmwIp7jAJJkIVlDCscQ/edit#gid=1242420410)** </br>
사용자 요구사항 정의서를 토대로 DB 테이블 계획하였고 해당 내용을 테이블 명세서 형태로 정리하였습니다. </br>

**[ERD](https://www.erdcloud.com/d/s5cxMny22Qe7Cze2r)** </br>
테이블 명세서의 내용을 ERD로 정리하였습니다. </br>

**[API 명세서](https://docs.google.com/spreadsheets/d/1PNIgti4eiDm92cu_uY254mnlviVLQ8GTV1vp6FgrZUc/edit#gid=0)** </br>
API 명세서를 통해 프론트엔드 팀원과의 협업을 매끄럽게 하고자 작성하였습니다. </br>

**[API 문서](http://ec2-15-164-220-43.ap-northeast-2.compute.amazonaws.com:8080/docs/index.html)** </br>
Spring Rest Docs를 활용하여 API 문서를 만들었습니다. 이를 통해 배포된 서비스에서 실제로 사용 가능한 API를 표현함으로 프론트엔드 팀원이 라이브 테스트하기 편리하도록 하고자 만들었습니다. </br>



