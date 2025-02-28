package java_practice.koreaClock;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.time.LocalTime;
import javafx.scene.shape.Rectangle;
import java.time.Clock;
import java.util.*;

public class ClockUI {
    private GridPane girdPane = new GridPane();
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

    public ClockUI() {
        girdPane.setStyle("-fx-background-color: #2C2C2C;");
        girdPane.setHgap(20);
        girdPane.setVgap(20);
        girdPane.setPadding(new Insets(20));
    }

    public void run(Stage stage){
        createClockUI(stage);
    }

    private void createClockUI(Stage stage) {
        Rectangle phoneBody = new Rectangle(300, 600);
        phoneBody.setArcWidth(40);
        phoneBody.setArcHeight(40);
        phoneBody.setFill(Color.BLACK);

        Scene scene = new Scene(this.getLayout(), 260, 570);
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                Text text = new Text(grid[i][j]);
                text.setFont(Font.font(40));
                text.setFill(Color.DARKGRAY);
                girdPane.add(text, j, i);

                textMap.putIfAbsent(grid[i][j], new ArrayList<>());
                textMap.get(grid[i][j]).add(text);
            }
        }

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateClock()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        stage.setScene(scene);
        stage.setTitle("Clock");
        stage.show();

        updateClock();
    }

    private void updateClock(){
        LocalTime now = LocalTime.now();
        int hour = now.getHour() % 12;
        int minute = now.getMinute();
        int second = now.getSecond();

        textMap.values().forEach(list -> list.forEach(text -> text.setFill(Color.DARKGRAY)));

        textMap.get("오").get(0).setFill(Color.WHITE);
        if (now.getHour() < 12) {
            textMap.get("전").get(0).setFill(Color.WHITE);
            textMap.get("🌙").get(0).setText("☀\uFE0F");
        } else {
            textMap.get("후").get(0).setFill(Color.WHITE);
        }
        textMap.get("🌙").get(0).setFill(Color.YELLOW);

        switch (hour) {
            case 0 -> {
                textMap.get("열").get(0).setFill(Color.WHITE);
                textMap.get("두").get(0).setFill(Color.WHITE);
            }
            case 1 -> textMap.get("한").get(0).setFill(Color.WHITE);
            case 2 -> textMap.get("두").get(0).setFill(Color.WHITE);
            case 3 -> textMap.get("세").get(0).setFill(Color.WHITE);
            case 4 -> textMap.get("네").get(0).setFill(Color.WHITE);
            case 5 -> {
                textMap.get("다").get(0).setFill(Color.WHITE);
                textMap.get("섯").get(0).setFill(Color.WHITE);
            }
            case 6 -> {
                textMap.get("여").get(0).setFill(Color.WHITE);
                textMap.get("섯").get(0).setFill(Color.WHITE);
            }
            case 7 -> {
                textMap.get("일").get(0).setFill(Color.WHITE);
                textMap.get("곱").get(0).setFill(Color.WHITE);
            }
            case 8 -> {
                textMap.get("여").get(0).setFill(Color.WHITE);
                textMap.get("덟").get(0).setFill(Color.WHITE);
            }
            case 9 -> {
                textMap.get("아").get(0).setFill(Color.WHITE);
                textMap.get("홉").get(0).setFill(Color.WHITE);
            }
            case 10 -> textMap.get("열").get(0).setFill(Color.WHITE);
            case 11 -> {
                textMap.get("열").get(0).setFill(Color.WHITE);
                textMap.get("한").get(0).setFill(Color.WHITE);
            }
        }
        textMap.get("시").get(0).setFill(Color.WHITE);

        if (minute >= 10){
            textMap.get("십").get(0).setFill(Color.WHITE);
            switch (minute / 10){
                case 2 -> textMap.get("이").get(0).setFill(Color.WHITE);
                case 3 -> textMap.get("삼").get(0).setFill(Color.WHITE);
                case 4 -> textMap.get("사").get(0).setFill(Color.WHITE);
                case 5 -> textMap.get("오").get(1).setFill(Color.WHITE);
            }
        }
        textMap.get("분").get(0).setFill(Color.WHITE);

        switch (minute % 10){
            case 1 -> textMap.get("일").get(1).setFill(Color.WHITE);
            case 2 -> textMap.get("이").get(1).setFill(Color.WHITE);
            case 3 -> textMap.get("삼").get(1).setFill(Color.WHITE);
            case 4 -> textMap.get("사").get(1).setFill(Color.WHITE);
            case 5 -> textMap.get("오").get(2).setFill(Color.WHITE);
            case 6 -> textMap.get("육").get(0).setFill(Color.WHITE);
            case 7 -> textMap.get("칠").get(0).setFill(Color.WHITE);
            case 8 -> textMap.get("팔").get(0).setFill(Color.WHITE);
            case 9 -> textMap.get("구").get(0).setFill(Color.WHITE);
        }

        String sec = "";
        if (second >= 10){
            switch (second / 10){
                case 1 -> sec += "  십 ";
                case 2 -> sec += "이십";
                case 3 -> sec += "삼십";
                case 4 -> sec += "사십";
                case 5 -> sec += "오십";
            }
        }
        sec += "\n";
        switch (second % 10){
            case 0 -> sec += "  ";
            case 1 -> sec += "일";
            case 2 -> sec += "이";
            case 3 -> sec += "삼";
            case 4 -> sec += "사";
            case 5 -> sec += "오";
            case 6 -> sec += "육";
            case 7 -> sec += "칠";
            case 8 -> sec += "팔";
            case 9 -> sec += "구";
        }
        sec+="초";
        if (second == 0) sec = "정각";

        textMap.get("초").get(0).setText(sec);
        textMap.get("초").get(0).setFont(Font.font(18));
        textMap.get("초").get(0).setFill(Color.WHITE);
    }

    public GridPane getLayout() {
        return girdPane;
    }
}
