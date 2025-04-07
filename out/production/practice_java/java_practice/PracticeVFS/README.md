### 가상 파일 시스템

- #### OS가 데이터를 저장하고 관리하는 방식 
- #### OS는 다양한 파일 시스템을 지원할 수 있도록 설계되는데, 그 핵심 역할을 하는 것이 Virtual File System 임

<br>

### 구현 기능
- 프로그램 시작 시 사용자에게 파일 시스템 초기값을 입력받는다.
- 파일 생성시 디렉토리를 조회하고 현재 디렉토리를 부모로하여 자식을 생성한다.
- 해당 정보는 Directory에 저장한다.
- 파일 생성 시 해당 디렉토리의 위치를 검색하고, 해당 위치에 바이트 배열을 생성하여 크기를 계산함
- File 클래스를 통해 import와 export로 외부에서 파일을 전송 또는 가져오기 가능 (마찬가지로 바이트 배열을 통해 쓰기, 읽기 가능)


<br>

### 명령어
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

# root에 hello 디렉토리를 만들겠다. 젤 처음엔 무조건 root임
```

- 파일 생성
```
my-vfs> create /hello greeting.txt "hello, vfs"

# /hello에 "hello, vfs" 문자열이 적힌 greeting.txt 파일을 추가하겠다.
```
- 파일 조회
```
my-vfs> list /

# root에 있는 파일 목록을 조회하겠다.
```
- 파일 읽기
```
my-vfs> read /hello/greeting.txt

# /hello 위치에 있는 greeting.txt 파일을 읽겠다.
```
- 외부 파일 가져오기
```
my-vfs> import <your path> /hello

# <your path>경로의 파일을 /hello로 가져오겠다.
```
- 외부로 파일 보내기
```
my-vfs> export /hello/greeting.txt <your path>

# /hello에 위치한 greeting.txt를 <your path>로 옮기겠다.
```

### 외부 파일 처리방법
#### 자바의 내장 클래스인 File 사용
#### 그리고 파일은 byte로 쓰기, 읽기 가능

