## 한글 시계

### 동작사진
<img src="https://gist.github.com/user-attachments/assets/4a40000d-6bf3-4595-957f-b234069b3726" width="200" height="500"/>

### 사용한 UI
- JAVAFX
  - 버전 : 23.0.2
  - 자바 버전 : 21.0.5
  - File → Project Structure → Libraries → + 버튼 클릭 → Java → <다운받은 폴더 경로\lib> → Apply
  - Run → Edit Configuration → Modify Option → Add VM options → 대괄호 내의 명령어 추가 [ --module-path "yourURL\lib" --add-modules javafx.controls,javafx.fxml ]
  

### 어떻게 했게?

- 우선 전체 여백과 간격을 줌

```java
public ClockUI() {
    girdPane.setStyle("-fx-background-color: #2C2C2C;");
    girdPane.setHgap(20); // 그리드내 가로 간격
    girdPane.setVgap(20); // 그리드내 세로 간격
    girdPane.setPadding(new Insets(20)); // 바깥 패딩
}
```

<br>

- 초기 그리드 생성

```java
private Map<String, List<Text>> textMap = new HashMap<>();

private String[][] grid = {
        {"오", "전", "후", "영"},
        {"열", "한", "두", "세"},
        {"네", "다", "여", "섯"},
        {"일", "곱", "여", "덟"},
        {"아", "홉", "시", "🌙"},
        {"이", "삼", "사", "오"},
        {"십", "일", "이", "삼"},
        {"사", "오", "육", "칠"},
        {"팔", "구", "분", "초"}
};
```

#### textMap에는 이제 각 그리드를 찾아가서 색칠해주는 용도가 들어감

<br>

- ⭐이게 중요⭐

```java
for(int i = 0; i < grid.length; i++) {
        for(int j = 0; j < grid[i].length; j++) {
Text text = new Text(grid[i][j]);
        text.setFont(Font.font(40));
        text.setFill(Color.DARKGRAY);
        girdPane.add(text, j, i);

// putIfAbsent = 만약 gird[i][j]를 키로하는 애가 없으면 ArrayList를 생성
        textMap.putIfAbsent(grid[i][j], new ArrayList<>());
        textMap.get(grid[i][j]).add(text);
    }
            }
```


- 이거 왜 하냐면?  grid에 "오", "이" 등 중복되니까 원하지 않는 자리에 색칠됨
- 그래서 Map에서 String을 키로, List\<Text>를 값으로 저장해줌
    - "오" -> [ Text@1234("오"), Text@asdf2("오"), Text@fei3("오") ] 이런식으로 저장됨
    - get("오").get(0).setFill(RED)이런식으로 하면 Text@1234("오")가 참조되어 해당 위치를 빨간색으로 칠해줌

<br>


- 1초 간격으로 반복
```java
// 1초 간격으로 updateClock() 발생 
// updateClock() = 색칠해주는 함수임
Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateClock()));
timeline.setCycleCount(Timeline.INDEFINITE); // 무한동력
timeline.play();
```

<BR>

- 스크린 띄우기
```java
Scene scene = new Scene(this.getLayout(), 260, 570); // 시작화면 크기 설정
stage.setScene(scene);
stage.setTitle("Clock");
stage.show();

updateClock();
// 얘 왜 또 썼냐면, 위의 로직이 실행하면 1초뒤에 실행됐기 때문임 -> 스크린 생성되자마자 실행
```