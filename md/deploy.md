# 배포

디렉터리

* 로그: `~/app/myblog/logs`
* DB: `~/app/myblog/db`
* Jenkins: `~/app/jenkins`

## 초기 설정

도커 설치

```bash
sudo snap install docker
```

## Nginx 실행

리버스 프록시

* /blog: 블로그
* /jenkins: Jenkins

## Mysql 실행

```bash
docker run --name mysql-db -v ~/app/myblog/db:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=root -p 3306:3306 -d mysql:8.0.16
```

## Jenkins 컨테이너 실행 및 기초 세팅

```shell
docker run
    --name jenkins \
    --rm \ # 컨테이너가 이미 존재하면 삭제
    -p 8200:8080 \ # Jenkins 컨테이너의 8080 포트와 로컬 8200 포트에 매핑
    -v ~/app/jenkins:/var/jenkins_home \ # Jenkins 데이터 디렉터리를 로컬 디렉터리에 매핑
    -v /var/run/docker.sock:/var/run/docker.sock \
    -v "$HOME":/home \
    -d
    jenkinsci/blueocean
```

`localhost:8200`로 접속, 실행 로그의 비밀번호 입력

`Install Suggested Plugins` 선택

관리자 계정 생성

## 프로젝트 생성

`New item` 선택

프로젝트 이름 입력 후 `Freestyle project` 선택하여 생성

빌드(Shell) 스크립트

```bash
./gradlew clean build

docker build --tag laterality/myblog:latest .
docker stop myblog
docker run --name myblog -p 8080:8080 -d laterality/myblog:latest
```

