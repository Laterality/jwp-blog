# 나만의 블로그 만들기

## 게시글 관련 기능 구현 요구사항

- `/`: 메인 페이지(`index.html`)
    - [x] 게시글 생성 버튼을 누르면 `/writing`으로 GET 요청을 보낸다.
    - [x] 작성된 게시글 목록이 노출된다.
    - [x] 게시글을 클릭하면 게시글 페이지(`/articles/{articleId}`)로 이동한다.

- `/writing`: 게시글 작성 페이지(`article-edit.html`)
    - [x] 저장 버튼을 누르면 `/articles`로 POST 요청을 보낸다.
        - [x] X 버튼을 누르면 메인 페이지로 이동한다.
        - [x] request받은 게시글을 ArticleRespository에 저장한다.

- `/articles/{articleId}`: 게시글 페이지(`article.html`)
    - [x] 수정 버튼을 누르면 게시글 수정 페이지(`/articles/{articleId}/edit`)로 이동
    - [x] 삭제 버튼을 누르면 `articles/{articleId}`로 DELETE 요청을 보낸다.
        - [x] 요청이 처리되면 메인 페이지(`/`)로 리다이렉트한다.

- `/articles/{articleId}/edit`: 게시글 수정 페이지(`article-edit.html`)
    - [x] 수정을 완료하면 `/articles/{articleId}`로 PUT 요청을 보낸다.
    - [x] 요청이 처리되면 게시글 페이지(`/article/{articleId}`)로 리다이렉트한다.

### 2주차 요구사항 - 회원 관리

#### 1단계: 등록/조회

- [x] 기존에 구현한 내용을 Mysql로 옮긴다.
- [x] 실행 쿼리를 볼 수 있도록 설정한다.

- `/signup`: 회원가입 페이지(`signup.html`)
    - [x] '가입하기' 버튼을 누르면 `/users`로 POST 요청을 보낸다.
    - [x] Spring Data JPA를 이용하여 DB에 사용자 정보를 저장한다.
    - [x] 생성된 후에 로그인 화면(`/login`)으로 이동한다.
    - [x] 입력 정보는 다음 규칙을 따르며, 위반 시 사용자에게 알린다.
        - [x] 동일한 email로 중복 가입 불가능.
        - [x] 이름은 2~10글자이며, 숫자와 특수문자가 포함될 수 없다.
        - [x] 비밀번호는 8글자 이상 소문자, 대문자, 숫자, 특수문자 조합이어야 한다.
        - [x] 비밀번호 확인 기능이 기능이 동작해야 한다.
    - [x] 회원 등록 실패 시 에러 메시지를 `Model`에 담아서 페이지에 노출한다 - 부트스트랩의 Alerts를 이용
    - [x] 프론트엔드에서도 유효성을 체크할 수 있도록 한다.
    - [x] HTML5에서 제공하는 form validation 기능을 최대한 활용한다.
    
- `/users`: 회원 목록 페이지(`user-list.html`)
    - [x] DB에 저장된 회원 정보를 노출한다.

#### 2단계: 로그인

- `/login`: 로그인 페이지(`login.html`)
    - [x] 로그인 성공 시 메인 페이지(`/`)로 이동하고 우측 상단에 사용자 이름을 띄운다.
    - [x] 로그인 실패 시 상황에 맞는 메시지를 띄운다.
        - 이메일이 없는 경우
        - 비밀번호가 틀린 경우
    - [x] 로그아웃 시 메인 페이지로 이동한다.
    - [x] 로그인된 사용자가 로그인/회원가입 화면에 접근할 경우 메인 페이지로 이동한다.

- `/mypage`: 마이페이지(사용자 정보 확인, `mypage.html`)
    - [x] 로그인된 사용자 정보가 보여진다.
    - [x] 로그인 여부를 판단하여 다른 사람이 접근하는 경우를 제한한다.
    - [x] 수정 아이콘을 클릭하면 수정 페이지(`/mypage-edit`)로 이동한다.
    - [x] 탈퇴 버튼을 추가한다.

#### 3단계: 회원정보 수정 및 탈퇴

- `/mypage-edit`: 마이페이지(사용자 정보 수정, `mypage-edit.html`)
    - [x] 수정 버튼을 클릭하면 사용자 정보를 새로 수정한다.
    - [x] 로그인 여부를 판단하여 다른 사람이 접근하는 경우를 제한한다.

- `/withdraw`: 사용자 탈퇴(별도 페이지 없음)
    - [x] 사용자 정보를 DB에서 제거한다.
    - [x] 로그인 여부를 판단하여 다른 사람이 접근하는 경우를 제한한다.
