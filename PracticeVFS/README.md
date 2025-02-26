### 가상 파일 시스템

- #### OS가 데이터를 저장하고 관리하는 방식 
- #### OS는 다양한 파일 시스템을 지원할 수 있도록 설계되는데, 그 핵심 역할을 하는 것이 Virtual File System 임

#### 명령어
- 초기

```
저장된 파일 시스템이 없습니다.
파일 시스템의 최대 크기를 입력해 주세요.
my-vfs> 
```
- 사용자 입력: 500m
```
my-vfs> 500M
500 메가 파일 시스템의 초기화를 완료했습니다.
my-vfs>
```

- 디렉토리 생성 명령어
```
my-vfs> makedir / hello
```

- 파일 생성
```
my-vfs> create /hello greeting.txt "hello, vfs"
```
- 파일 조회
```
my-vfs> list /
```
- 파일 읽기
```
my-vfs> read /hello/greeting.txt
```
외부 파일 가져오기
```
my-vfs> import <your path> /users/honux
```
외부로 파일 보내기
```
my-vfs> export /hello/greeting.txt <your path>
```

### 외부 파일 처리방법
#### 자바의 내장 클래스인 File 사용
#### 그리고 파일은 byte로 쓰기, 읽기 가능

