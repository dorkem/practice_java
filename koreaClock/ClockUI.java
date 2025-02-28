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
import java.time.Clock;
import java.util.*;

public class ClockUI {
    private GridPane girdPane = new GridPane();
    private Map<String, Text> textMap = new HashMap<>();
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
        Scene scene = new Scene(this.getLayout(), 400, 600);

        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                Text text = new Text(grid[i][j]);
                text.setFont(Font.font(40));
                text.setFill(Color.DARKGRAY);
                girdPane.add(text, j, i);
                textMap.put(grid[i][j], text);
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

        if (now.getHour() < 12) {
            textMap.get("오").setFill(Color.WHITE);
            textMap.get("전").setFill(Color.WHITE);
        } else {
            textMap.get("오").setFill(Color.WHITE);
            textMap.get("후").setFill(Color.WHITE);
        }

        switch (hour) {
            case 0 -> {
                textMap.get("열").setFill(Color.WHITE);
                textMap.get("두").setFill(Color.WHITE);
            }
            case 1 -> textMap.get("한").setFill(Color.WHITE);
            case 2 -> textMap.get("두").setFill(Color.WHITE);
            case 3 -> textMap.get("세").setFill(Color.WHITE);
            case 4 -> textMap.get("네").setFill(Color.WHITE);
            case 5 -> {
                textMap.get("다").setFill(Color.WHITE);
                textMap.get("섯").setFill(Color.WHITE);
            }
            case 6 -> {
                textMap.get("여").setFill(Color.WHITE);
                textMap.get("섯").setFill(Color.WHITE);
            }
            case 7 -> {
                textMap.get("일").setFill(Color.WHITE);
                textMap.get("곱").setFill(Color.WHITE);
            }
            case 8 -> {
                textMap.get("여").setFill(Color.WHITE);
                textMap.get("덟").setFill(Color.WHITE);
            }
            case 9 -> {
                textMap.get("아").setFill(Color.WHITE);
                textMap.get("홉").setFill(Color.WHITE);
            }
            case 10 -> textMap.get("열").setFill(Color.WHITE);
            case 11 -> {
                textMap.get("열").setFill(Color.WHITE);
                textMap.get("한").setFill(Color.WHITE);
            }
        }
        textMap.get("시").setFill(Color.WHITE);


    }

    public GridPane getLayout() {
        return girdPane;
    }
}
