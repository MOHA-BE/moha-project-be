# :hearts: Moha_MiniProject

---

프로젝트 설명!

## :sunglasses: Team

---

### Frontend(React)

- 박민우(팀장)
- 한상원

### Backend(Spring)

- 최현민
- 권윤주

---

### 개발기간

---

2022년 04월 7일 ~2022년 04월 14일

## :earth_asia: Website

---

사이트바로가기

### :tv: Youtube

---

링크

## :speech_balloon: ****Project Collaboration Process****

---

- ****Back-end 기술 스택 및 개발 환경****
    - java
    - spring boot
    - Mysql
    - AWS EC2
    - JPA
    - JWT
- **API 설계**
    
    
    | 데이터 테이블 | 기능 | METHOD | URL | request | response | 담당 | 비고 |  |
    | --- | --- | --- | --- | --- | --- | --- | --- | --- |
    | 회원 정보 | 회원가입 | POST | /api/signup | {
    userId:
    password:
    nickname:
    
    } | {
    ‘ok’: true,
    message: ‘회원가입 성공’
    }
    OR
    {
    ‘ok’: false,
    message:’회원가입 실패’
    } |  |  |  |
    |  | 로그인 | POST | /api/login
    
    아래와같이 변경
    
    /api/loginView | {
    userId:
    password:
    } | token: 
    
    {
    'ok':true, 
    message:’로그인 성공’
    }
    OR
    {
    ‘ok’: false,
    message:’로그인 실패’
    } |  |  |  |
    |  | 아이디 중복 검사 | POST | /api/idCheck | {
     userId:
    } | {
     return: false
    }
    아이디 중복 되지 않으면 true |  |  |  |
    |  | 로그인 여부 확인 | GET  | /api/idlogin |  | {
     userInfo:{
        userId: userId
     }
    } |  |  |  |
    |  | 로그아웃 | GET | /api/idCheck |  | redirect:/login (replace in react) |  |  |  |
    |  | 로그인 정보 불러오기 | GET | /api/auth |  | {
    'ok': true,
    result: 
    [{
    nickname:
    }]
    } |  |  |  |
    |  |  |  |  |  |  |  |  |  |
    |  |  |  |  |  |  |  |  |  |
    | 메인 페이지 | 게시글 조회
    (메인 페이지 슬라이더 섹션, 게시글 리스트) | GET | /api/posts |  | {
    'ok': true,
    message: 
    result: 
    [{
    postId:
    title:
    desc:
    imageUrl:
    category:
    createdAt: 
    modifiedAt:
    }]
    } | 최현민 |  |  |
    |  | 게시글 작성 | POST | /api/posts | {
    title:
    desc:
    imageUrl:
    category:
    name:
    } | {
    ‘ok’: true,
    message: ‘생성 성공’
    }
    OR
    {
    ‘ok’: false,
    message:’생성 실패’
    } | 최현민 |  |  |
    |  |  |  |  |  |  |  |  |  |
    |   | 게시글 상세 조회 | GET | /api/posts/{id} |  | {
    ‘ok’: true,
    result: 
    [{
    postId:
    title: contents:
    imageUrl:
    category:
    createdAt:
    modifiedAt:
    }]
    } | 최현민 | postId → id 변경 |  |
    |  | 게시글 수정하기
     | PUT | /api/posts/{id} | {
    title:
    desc:
    imgUrl:
    category:
    } | {
    ‘ok’: true,
    message: ‘수정 성공’,
    }
    OR
    {
    ‘ok’: false,
    message:’수정 실패’
    } | 최현민 |  |  |
    |  | 게시글 삭제하기 | DELETE | /api/posts/{id} | {
     postId: 
    } | {
    ‘ok’: true,
    message: ‘삭제 성공’
    }
    OR
    {
    ‘ok’: false,
    message:’삭제 실패’
    } | 최현민 |  |  |
    |  |  |  |  |  |  |  | 
    
     |  |
    |  |  |  |  |  |  |  |  |  |
    | 댓글 | 댓글 작성 | POST | /api/comments | { 
     commentId:
    comment:} | {
    ‘ok’: true,
    message: ‘생성 성공’
    }
    OR
    {
    ‘ok’: false,
    message:’생성 실패’
    } | 최현민 |  |  |
    |  | 댓글 삭제 | DELETE | /api/comments/{id} |  | {
    ‘ok’: true,
    message: ‘삭제 성공’
    }
    OR
    {
    ‘ok’: false,
    message:’삭제 실패’
    } | 최현민 |  |  |
    |  |  |  |  |  |  |  |  |  |
- **Digram**
    
    
    ![Untitled](https://user-images.githubusercontent.com/61370487/163331912-e9758246-9535-406b-a369-328d3b9619d8.png)
    
- **Troubleshooting**
    - JWT
- **개인 회고**
